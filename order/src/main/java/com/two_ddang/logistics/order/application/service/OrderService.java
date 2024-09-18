package com.two_ddang.logistics.order.application.service;

import com.two_ddang.logistics.core.entity.OrderStatus;
import com.two_ddang.logistics.core.entity.UserType;
import com.two_ddang.logistics.core.exception.ErrorCode;
import com.two_ddang.logistics.core.util.Passport;
import com.two_ddang.logistics.order.application.dtos.response.*;
import com.two_ddang.logistics.order.application.exception.BusinessException;
import com.two_ddang.logistics.order.domain.model.Order;
import com.two_ddang.logistics.order.domain.model.OrderProduct;
import com.two_ddang.logistics.order.domain.repository.OrderRepository;
import com.two_ddang.logistics.order.infrastructure.CompanyService;
import com.two_ddang.logistics.order.infrastructure.HubService;
import com.two_ddang.logistics.order.infrastructure.dtos.CompanyDetailResponse;
import com.two_ddang.logistics.order.infrastructure.dtos.CompanyProductResponse;
import com.two_ddang.logistics.order.infrastructure.dtos.HubProductOutboundRequest;
import com.two_ddang.logistics.order.infrastructure.fallback.DeliveryServiceFallbackHandler;
import com.two_ddang.logistics.order.presentation.dtos.CreateOrderRequest;
import com.two_ddang.logistics.order.presentation.dtos.UpdateOrderStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.two_ddang.logistics.order.presentation.dtos.CreateOrderRequest.CreateOrderProductRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final HubService hubService;
    private final OrderRepository orderRepository;
    private final CompanyService companyService;
    private final DeliveryServiceFallbackHandler deliveryServiceFallbackHandler;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        UUID reqCompanyId = createOrderRequest.getReqCompanyId();
        CompanyDetailResponse reqCompany = companyService.getCompany(reqCompanyId).getData();
        UUID resCompanyId = createOrderRequest.getResCompanyId();
        CompanyDetailResponse resCompany = companyService.getCompany(resCompanyId).getData();

        List<OrderProduct> orderProducts = validAndCreateOrderProduct(reqCompanyId, createOrderRequest);

        Order order = orderRepository.save(Order.create(reqCompany, resCompany, orderProducts));

        order.updateStatus(OrderStatus.PENDING);

        UUID deliveryId = deliveryServiceFallbackHandler
                .createDelivery(order, reqCompanyId, resCompany);

        if (deliveryId != null) {
            order.addDeliveryId(deliveryId);
        }

        return CreateOrderResponse.of(order);
    }


    public Page<OrderResponse> getOrders(Pageable pageable, String keyword, Passport passport) {
        UserType userType = passport.getUserType();
        if (userType == UserType.DELIVERY) {
            throw new BusinessException(ErrorCode.CAN_NOT_ACTION_ROLE);
        }
        if (userType == UserType.COMPANY) {
            return orderRepository.findAllByCreatedBy(pageable, passport.getUserId()).map(OrderResponse::of);
        }

        if (userType == UserType.HUB) {
            UUID hubId = hubService.getHubIdByUserId(passport.getUserId());
            return orderRepository.findAllByReqHubIdOrResHubId(pageable, hubId, hubId).map(OrderResponse::of);
        }

        return orderRepository.findAll(pageable).map(OrderResponse::of);

    }


    public OrderDetailResponse getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        String reqCompanyName = companyService.getCompany(order.getReqCompanyId()).getData().getCompanyName();
        String resCompanyName = companyService.getCompany(order.getResCompanyId()).getData().getCompanyName();

        return OrderDetailResponse.of(order, reqCompanyName, resCompanyName);
    }


    @Transactional
    public UpdateOrderStatusResponse updateOrderStatus(UUID orderId, UpdateOrderStatusRequest updateOrderStatusRequestDto, Passport passport) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        checkRole(passport, order);

        order.updateStatus(updateOrderStatusRequestDto.getOrderStatus());
        return UpdateOrderStatusResponse.of(order);
    }

    @Transactional
    public CancelOrderResponse cancelOrder(UUID orderId, Passport passport) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        checkRole(passport, order);
        order.cancel();
        return CancelOrderResponse.of(order);
    }


    @Transactional
    public void deleteOrder(UUID orderId, Passport passport) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        checkRole(passport, order);

        order.delete(passport.getUserId());
    }


    private List<OrderProduct> validAndCreateOrderProduct(UUID hubId, CreateOrderRequest createOrderRequest) {
        List<CreateOrderProductRequest> orderProductsReq = createOrderRequest.getOrderProducts();
        List<CompanyProductResponse> companyProducts =
                companyService.getCompanyProducts(createOrderRequest.getReqCompanyId()).getData();

        List<OrderProduct> orderProducts = new ArrayList<>();

        for (CreateOrderProductRequest orderProduct : orderProductsReq) {

            companyProducts.stream()
                    .filter(prod -> prod.getProductId().equals(orderProduct.getProductId()))
                    .findFirst()
                    .ifPresentOrElse(
                            product -> {
                                orderProducts.add(OrderProduct.create(product, orderProduct.getQuantity()));
                                hubService.outbound(hubId, HubProductOutboundRequest.of(
                                        product.getProductId(),
                                        createOrderRequest.getReqCompanyId(),
                                        orderProduct.getQuantity()
                                ));
                            },
                            () -> {
                                throw new BusinessException(ErrorCode.COMPANY_PRODUCT_NOT_FOUND);
                            }
                    );
        }

        return orderProducts;
    }

    private void checkRole(Passport passport, Order order) {
        UserType userType = passport.getUserType();
        Integer userId = passport.getUserId();

        if (userType == UserType.DELIVERY) {
            throw new BusinessException(ErrorCode.CAN_NOT_ACTION_ROLE);
        }

        if (userType == UserType.COMPANY) {
            if (!order.getCreatedBy().equals(userId)) {
                throw new BusinessException(ErrorCode.CAN_NOT_ACTION_ROLE);
            }
        }

        if (userType == UserType.HUB) {
            UUID hubId = hubService.getHubIdByUserId(userId);
            if (!order.getReqHubId().equals(hubId) || !order.getResHubId().equals(hubId)) {
                throw new BusinessException(ErrorCode.CAN_NOT_ACTION_ROLE);
            }
        }
    }
}

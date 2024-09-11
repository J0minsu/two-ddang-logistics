package com.two_ddang.logistics.order.application.service;

import com.two_ddang.logistics.core.exception.ApplicationException;
import com.two_ddang.logistics.core.exception.ErrorCode;
import com.two_ddang.logistics.order.application.dtos.response.*;
import com.two_ddang.logistics.order.domain.model.Order;
import com.two_ddang.logistics.order.domain.model.OrderProduct;
import com.two_ddang.logistics.order.domain.repository.OrderRepository;
import com.two_ddang.logistics.order.infrastructure.CompanyService;
import com.two_ddang.logistics.order.infrastructure.DeliveryService;
import com.two_ddang.logistics.order.infrastructure.ProductService;
import com.two_ddang.logistics.order.infrastructure.dtos.CompanyDetailResponse;
import com.two_ddang.logistics.order.infrastructure.dtos.DeliveryCreateRequest;
import com.two_ddang.logistics.order.presentation.dtos.CreateOrderRequest;
import com.two_ddang.logistics.order.presentation.dtos.UpdateOrderStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.two_ddang.logistics.order.presentation.dtos.CreateOrderRequest.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final CompanyService companyService;
    private final ProductService productService;
    private final DeliveryService deliveryService;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        UUID reqCompanyId = createOrderRequest.getReqCompanyId();
        UUID resCompanyId = createOrderRequest.getResCompanyId();
        CompanyDetailResponse resCompany = companyService.getCompany(resCompanyId).getData();


        List<OrderProduct> orderProducts = validAndCreateOrderProduct(createOrderRequest);


        Order order = orderRepository.save(Order.create(createOrderRequest, orderProducts));

        // delivery 생성 호출
        UUID deliveryId = deliveryService
                .create(DeliveryCreateRequest.of(order.getId(), reqCompanyId, resCompany))
                .getData()
                .getDeliveryId();


        order.addDeliveryId(deliveryId);

        return CreateOrderResponse.of(order);
    }


    public Page<OrderResponse> getOrders(Pageable pageable, String keyword) {
        return orderRepository.findAll(pageable).map(OrderResponse::of);
    }

    public OrderDetailResponse getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));

        String reqCompanyName = companyService.getCompany(order.getReqCompanyId()).getData().getCompanyName();
        String resCompanyName = companyService.getCompany(order.getResCompanyId()).getData().getCompanyName();

        return OrderDetailResponse.of(order, reqCompanyName, resCompanyName);
    }


    @Transactional
    public UpdateOrderStatusResponse updateOrderStatus(UUID orderId, UpdateOrderStatusRequest updateOrderStatusRequestDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));
        order.updateStatus(updateOrderStatusRequestDto.getOrderStatus());
        return UpdateOrderStatusResponse.of(order);
    }

    @Transactional
    public CancelOrderResponse cancelOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));
        order.cancel();
        return CancelOrderResponse.of(order);
    }


    @Transactional
    public void deleteOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND));
        //유저 정보 받아 오는 방법 ??
//        order.delete();
    }




    private List<OrderProduct> validAndCreateOrderProduct(CreateOrderRequest createOrderRequest) {
        List<CreateOrderProductRequest> orderProducts = createOrderRequest.getOrderProducts();
        // 상품 존재 여부 검증 매서드 공급업체 상품 조회 해서 검증
        // 허브에서 검증해야하는데 hub_product_id 를 모른다.
        //존재 하는 상품 가져오고 재고 차감  -> orderProduct 반환
        return new ArrayList<>();
    }
}

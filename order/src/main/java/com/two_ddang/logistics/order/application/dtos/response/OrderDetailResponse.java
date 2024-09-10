package com.two_ddang.logistics.order.application.dtos.response;

import com.two_ddang.logistics.order.domain.model.Order;
import com.two_ddang.logistics.order.domain.model.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OrderDetailResponse {

    private UUID orderId;
    private UUID reqCompanyId;
    private UUID resCompanyId;
    private Integer requestedBy;
    private List<OrderProductDetailResponse> orderProducts;
    private Long totalPrice;
    private OrderStatus orderStatus;

    public static OrderDetailResponse of(Order order) {
        return OrderDetailResponse.builder()
                .orderId(order.getId())
                .reqCompanyId(order.getReqCompanyId())
                .resCompanyId(order.getResCompanyId())
                .requestedBy(order.getCreatedBy())
                .orderProducts(order.getOrderProducts().stream()
                        .map(OrderProductDetailResponse::of).toList())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getOrderStatus())
                .build();
    }
}

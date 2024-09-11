package com.two_ddang.logistics.order.application.dtos.response;

import com.two_ddang.logistics.order.domain.model.Order;
import com.two_ddang.logistics.order.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelOrderResponse {

    private UUID orderId;
    private OrderStatus orderStatus;

    public static CancelOrderResponse of(Order order) {
        return CancelOrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .build();
    }
}

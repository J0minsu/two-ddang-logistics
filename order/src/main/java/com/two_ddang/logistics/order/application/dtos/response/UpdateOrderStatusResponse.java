package com.two_ddang.logistics.order.application.dtos.response;

import com.two_ddang.logistics.core.entity.OrderStatus;
import com.two_ddang.logistics.order.domain.model.Order;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateOrderStatusResponse {

    private UUID orderId;
    private OrderStatus orderStatus;

    public static UpdateOrderStatusResponse of(Order order) {
        return UpdateOrderStatusResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .build();
    }

}

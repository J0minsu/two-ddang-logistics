package com.two_ddang.logistics.order.presentation.response;

import com.two_ddang.logistics.order.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelOrderResponseDto {

    private UUID orderId;
    private OrderStatus orderStatus;
}

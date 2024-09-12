package com.two_ddang.logistics.order.presentation.dtos;

import com.two_ddang.logistics.core.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusRequest {

    private OrderStatus orderStatus;
}

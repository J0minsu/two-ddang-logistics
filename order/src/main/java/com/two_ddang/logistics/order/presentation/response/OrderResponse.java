package com.two_ddang.logistics.order.presentation.response;

import com.two_ddang.logistics.order.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private UUID orderId;
    private OrderStatus orderStatus;
    private UUID reqCompanyId;
    private UUID resCompanyId;
    private String productName;
    private Integer quantity;
    private Long totalPrice;
    private LocalDateTime orderDate;
}

package com.two_ddang.logistics.order.presentation.response;

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
public class OrderDetailResponseDto {

    private UUID orderId;
    private UUID reqCompanyId;
    private String reqCompanyName;
    private UUID resCompanyId;
    private String resCompanyName;
    private String requestedBy;
    private List<OrderProductResponseDto> orderProducts;
    private Long totalPrice;
    private OrderStatus orderStatus;
}

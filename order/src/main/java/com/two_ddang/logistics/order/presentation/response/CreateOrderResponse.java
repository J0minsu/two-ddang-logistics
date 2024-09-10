package com.two_ddang.logistics.order.presentation.response;

import com.two_ddang.logistics.order.domain.model.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CreateOrderResponse {

    private UUID orderId;
    private UUID reqCompanyId;
    private String reqCompanyName;
    private UUID resCompanyId;
    private String resCompanyName;
    private String requestBy;
    private List<OrderProductResponse> orderProducts;
    private Long totalPrice;
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;


}

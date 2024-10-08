package com.two_ddang.logistics.order.application.dtos.response;

import com.two_ddang.logistics.core.entity.OrderStatus;
import com.two_ddang.logistics.order.domain.model.Order;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OrderDetailResponse {

    private UUID orderId;
    private UUID reqCompanyId;
    private String reqCompanyName;
    private UUID resCompanyId;
    private String resCompanyName;
    private UUID deliveryId;
    private Integer requestedBy;
    private List<OrderProductDetailResponse> orderProducts;
    private Long totalPrice;
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;

    public static OrderDetailResponse of(Order order, String reqName, String resName) {
        return OrderDetailResponse.builder()
                .orderId(order.getId())
                .reqCompanyId(order.getReqCompanyId())
                .reqCompanyName(reqName)
                .resCompanyId(order.getResCompanyId())
                .resCompanyName(resName)
                .deliveryId(order.getDeliveryId())
                .requestedBy(order.getCreatedBy())
                .orderProducts(order.getOrderProducts().stream()
                        .map(OrderProductDetailResponse::of).toList())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getOrderStatus())
                .orderDate(order.getCreatedAt())
                .build();
    }
}

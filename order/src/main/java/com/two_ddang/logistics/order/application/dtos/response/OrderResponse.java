package com.two_ddang.logistics.order.application.dtos.response;

import com.two_ddang.logistics.order.domain.model.Order;
import com.two_ddang.logistics.order.domain.model.OrderProduct;
import com.two_ddang.logistics.order.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private UUID orderId;
    private OrderStatus orderStatus;
    private Integer requestBy;
    private String productName;
    private Long totalPrice;
    private LocalDateTime orderDate;

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .requestBy(order.getCreatedBy())
                .productName(mainProductName(order.getOrderProducts()))
                .totalPrice(order.getTotalPrice())
                .orderDate(order.getCreatedAt())
                .build();
    }


    private static String mainProductName(List<OrderProduct> orderProducts) {
        if (orderProducts == null || orderProducts.isEmpty()) {
            return "상품 없음";
        }

        return orderProducts.size()> 1 ?
                orderProducts.get(0).getProductName() + " 외 " + (orderProducts.size() -1) + "개":
                orderProducts.get(0).getProductName();
    }
}

package com.two_ddang.logistics.order.application.dtos.response;

import com.two_ddang.logistics.core.entity.OrderStatus;
import com.two_ddang.logistics.order.domain.model.Order;
import com.two_ddang.logistics.order.domain.model.OrderProduct;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class CreateOrderResponse {

    private UUID orderId;
    private UUID reqCompanyId;
    private UUID resCompanyId;
    private Integer requestBy;
    private List<CreateOrderProductResponse> orderProducts;
    private Long totalPrice;
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;



    public static CreateOrderResponse of(Order order) {
        return CreateOrderResponse.builder()
                .orderId(order.getId())
                .reqCompanyId(order.getReqCompanyId())
                .resCompanyId(order.getResCompanyId())
                .requestBy(order.getCreatedBy())
                .orderProducts(order.getOrderProducts().stream().map(CreateOrderProductResponse::of).toList())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getOrderStatus())
                .orderDate(order.getCreatedAt())
                .build();
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PRIVATE)
    public static class CreateOrderProductResponse {
        private UUID productId;
        private Integer quantity;

        public static CreateOrderProductResponse of(OrderProduct orderProduct) {
            return CreateOrderProductResponse.builder()
                    .productId(orderProduct.getProductId())
                    .quantity(orderProduct.getQuantity())
                    .build();
        }
    }
}

package com.two_ddang.logistics.order.application.dtos.response;

import com.two_ddang.logistics.order.domain.model.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductDetailResponse {

    private UUID productId;
    private String productName;
    private Integer quantity;
    private Long price;

    public static OrderProductDetailResponse of(OrderProduct orderProduct) {
        return OrderProductDetailResponse.builder()
                .productId(orderProduct.getProductId())
                .productName(orderProduct.getProductName())
                .quantity(orderProduct.getQuantity())
                .price(orderProduct.getPrice())
                .build();
    }
}

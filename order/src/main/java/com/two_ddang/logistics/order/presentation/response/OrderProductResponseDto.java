package com.two_ddang.logistics.order.presentation.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class OrderProductResponseDto {

    private UUID productId;
    private String productName;
    private Integer quantity;
    private Long price;
}

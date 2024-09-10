package com.two_ddang.logistics.order.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    private UUID reqCompanyId;
    private UUID resCompanyId;
    private List<CreateOrderProductRequestDto> orderProducts;


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateOrderProductRequestDto {
        private UUID productId;
        private String productName;
        private Integer quantity;
        private Long price;
    }
}


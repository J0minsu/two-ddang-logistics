package com.two_ddang.logistics.order.presentation.dtos;

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
    private List<CreateOrderProductRequest> orderProducts;


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateOrderProductRequest {
        private UUID productId;
        private Integer quantity;
    }


}


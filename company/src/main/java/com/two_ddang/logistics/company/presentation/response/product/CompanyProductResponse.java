package com.two_ddang.logistics.company.presentation.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyProductResponse {

    private UUID productId;
    private String productName;
    private Long price;
    private Integer quantity;
    private Boolean isSoldOut;
}

package com.two_ddang.logistics.company.application.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyProductResponse {

    private UUID productId;
    private UUID hubId;
    private String productName;
    private Long price;
    private Integer stock;
    private Boolean isSoldOut;
}

package com.two_ddang.logistics.company.application.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductResponse {

    private UUID productId;
    private String productName;
    private String description;
    private Long price;
    private Integer quantity;
    private UUID companyId;
    private UUID hubId;
}

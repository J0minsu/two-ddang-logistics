package com.two_ddang.logistics.company.application.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailResponse {

    private UUID productId;
    private String productName;
    private String description;
    private UUID companyId;
    private String companyName;
    private UUID hubId;
    private String hubName;
    private Long price;
    private Integer stock;
    private Boolean isSoldOut;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

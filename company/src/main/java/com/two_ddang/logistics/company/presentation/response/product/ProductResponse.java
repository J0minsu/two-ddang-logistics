package com.two_ddang.logistics.company.presentation.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private UUID productId;
    private String productName;
    private UUID companyId;
    private String companyName;
    private UUID hubId;
    private String hubName;
    private Long price;
    private Integer stock;
    private Boolean isSoldOut;
    private LocalDateTime updatedAt;
}

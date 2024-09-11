package com.two_ddang.logistics.company.application.dtos.product;

import com.two_ddang.logistics.company.domain.model.product.Product;
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
    private UUID hubId;
    private Long price;
    private Boolean isSoldOut;
    private LocalDateTime updatedAt;

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .companyId(product.getCompanyId())
                .hubId(product.getHubId())
                .price(product.getPrice())
                .isSoldOut(product.getIsSoldOut())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}

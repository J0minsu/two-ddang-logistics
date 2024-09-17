package com.two_ddang.logistics.company.application.dtos.product;

import com.two_ddang.logistics.company.domain.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ProductDetailResponse {

    private UUID productId;
    private String productName;
    private String description;
    private UUID companyId;
    private String companyName;
    private UUID hubId;
    private String hubName;
    private Long price;
    private Boolean isSoldOut;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductDetailResponse of(Product product, String hubName, String companyName) {

        return ProductDetailResponse.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .companyId(product.getCompanyId())
                .companyName(companyName)
                .hubId(product.getHubId())
                .hubName(hubName)
                .price(product.getPrice())
                .isSoldOut(product.getIsSoldOut())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}

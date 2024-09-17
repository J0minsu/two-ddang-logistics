package com.two_ddang.logistics.company.application.dtos.product;

import com.two_ddang.logistics.company.domain.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CreateProductResponse {

    private UUID productId;
    private String productName;
    private String description;
    private Long price;
    private UUID companyId;
    private UUID hubId;

    public static CreateProductResponse of(Product product) {
        return CreateProductResponse.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .companyId(product.getCompanyId())
                .hubId(product.getHubId())
                .build();
    }
}

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
public class CompanyProductResponse {

    private UUID productId;
    private UUID hubId;
    private String productName;
    private Long price;
    private Boolean isSoldOut;

    public static CompanyProductResponse of(Product product) {
        return CompanyProductResponse.builder()
                .productId(product.getId())
                .hubId(product.getHubId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .isSoldOut(product.getIsSoldOut())
                .build();
    }
}

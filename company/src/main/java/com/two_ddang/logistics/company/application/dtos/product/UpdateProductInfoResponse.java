package com.two_ddang.logistics.company.application.dtos.product;

import com.two_ddang.logistics.company.domain.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductInfoResponse {

    private UUID productId;
    private String productName;
    private String description;
    private Long price;
    private Boolean isSoldOut;

    public static UpdateProductInfoResponse of(Product product) {
        return UpdateProductInfoResponse.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .isSoldOut(product.getIsSoldOut())
                .build();
    }
}

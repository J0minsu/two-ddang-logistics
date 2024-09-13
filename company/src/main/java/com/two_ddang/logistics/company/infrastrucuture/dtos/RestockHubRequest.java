package com.two_ddang.logistics.company.infrastrucuture.dtos;

import com.two_ddang.logistics.company.domain.model.product.Product;
import com.two_ddang.logistics.company.presentation.dtos.company.RestockRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestockHubRequest {

    private UUID productId;
    private UUID companyId;
    private String productName;
    private Integer quantity;


    public static RestockHubRequest create(Product product, RestockRequest restockRequest) {
        return RestockHubRequest.builder()
                .productId(product.getCompanyId())
                .companyId(product.getCompanyId())
                .productName(product.getProductName())
                .quantity(restockRequest.getQuantity())
                .build();
    }
}

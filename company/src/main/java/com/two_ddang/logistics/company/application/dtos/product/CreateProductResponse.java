package com.two_ddang.logistics.company.application.dtos.product;

import com.two_ddang.logistics.company.domain.model.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Schema(title = "상품 생성 응답 DTO", description = "상품 생성 후 반환되는 응답 객체")
public class CreateProductResponse {

    @Schema(title = "상품 ID", example = "b0b1a1b6-2a4d-44b1-a1b6-2a4d44b1a1b6")
    private UUID productId;

    @Schema(title = "상품명", example = "New Product")
    private String productName;

    @Schema(title = "상품 설명", example = "This is a new product.")
    private String description;

    @Schema(title = "가격", example = "20000")
    private Long price;

    @Schema(title = "업체 ID", example = "d2d3d4d5-6e7f-8g9h-0a1b-2c3d4e5f6g7h")
    private UUID companyId;

    @Schema(title = "허브 ID", example = "e2cfd12e-1e47-4d2b-9a1c-57b144bca8a9")
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

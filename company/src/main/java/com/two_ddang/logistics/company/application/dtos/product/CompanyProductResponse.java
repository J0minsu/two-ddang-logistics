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
@Schema(title = "업체 상품 응답 DTO", description = "업체의 상품 정보를 포함한 응답 객체")
public class CompanyProductResponse {

    @Schema(title = "상품 ID", example = "b0b1a1b6-2a4d-44b1-a1b6-2a4d44b1a1b6")
    private UUID productId;

    @Schema(title = "허브 ID", example = "e2cfd12e-1e47-4d2b-9a1c-57b144bca8a9")
    private UUID hubId;

    @Schema(title = "상품명", example = "Example Product")
    private String productName;

    @Schema(title = "가격", example = "15000")
    private Long price;

    @Schema(title = "품절 여부", example = "false", description = "상품이 품절 상태인지 여부")
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

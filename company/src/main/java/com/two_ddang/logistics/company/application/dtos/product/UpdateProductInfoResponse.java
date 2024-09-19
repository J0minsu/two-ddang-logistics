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
@Schema(title = "상품 정보 수정 응답 DTO", description = "상품 정보 수정 후 반환되는 응답 객체")
public class UpdateProductInfoResponse {

    @Schema(title = "상품 ID", example = "b0b1a1b6-2a4d-44b1-a1b6-2a4d44b1a1b6")
    private UUID productId;

    @Schema(title = "상품명", example = "Updated Product Name")
    private String productName;

    @Schema(title = "상품 설명", example = "Updated product description.")
    private String description;

    @Schema(title = "가격", example = "17000")
    private Long price;

    @Schema(title = "품절 여부", example = "true", description = "상품이 품절 상태인지 여부")
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

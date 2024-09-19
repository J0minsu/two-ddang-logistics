package com.two_ddang.logistics.company.application.dtos.product;

import com.two_ddang.logistics.company.domain.model.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(title = "상품 응답 DTO", description = "상품 정보를 포함한 응답 객체")
public class ProductResponse {

    @Schema(title = "상품 ID", example = "b0b1a1b6-2a4d-44b1-a1b6-2a4d44b1a1b6")
    private UUID productId;

    @Schema(title = "상품명", example = "Product Name")
    private String productName;

    @Schema(title = "업체 ID", example = "d2d3d4d5-6e7f-8g9h-0a1b-2c3d4e5f6g7h")
    private UUID companyId;

    @Schema(title = "허브 ID", example = "e2cfd12e-1e47-4d2b-9a1c-57b144bca8a9")
    private UUID hubId;

    @Schema(title = "가격", example = "15000")
    private Long price;

    @Schema(title = "품절 여부", example = "false", description = "상품이 품절 상태인지 여부")
    private Boolean isSoldOut;

    @Schema(title = "수정일", example = "2024-09-18T10:00:00")
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

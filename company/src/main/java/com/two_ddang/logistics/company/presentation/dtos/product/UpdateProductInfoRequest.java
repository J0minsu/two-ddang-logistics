package com.two_ddang.logistics.company.presentation.dtos.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(title = "상품 정보 수정 요청 DTO", description = "상품 정보 수정을 위한 데이터 전송 객체")
public class UpdateProductInfoRequest {

    @Schema(title = "상품명", example = "Updated Product Name")
    private String productName;

    @Schema(title = "상품 설명", example = "Updated product description.")
    private String description;

    @Schema(title = "가격", example = "15000")
    private Long price;

    @Schema(title = "품절 여부", example = "false", description = "상품이 품절되었는지 여부")
    private Boolean isSoldOut;
}
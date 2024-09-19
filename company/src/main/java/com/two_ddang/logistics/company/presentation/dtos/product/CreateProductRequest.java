package com.two_ddang.logistics.company.presentation.dtos.product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(title = "상품 생성 요청 DTO", description = "새로운 상품 생성을 위한 데이터 전송 객체")
public class CreateProductRequest {

    @Schema(title = "상품명", example = "Example Product")
    private String productName;

    @Schema(title = "상품 설명", example = "This is an example product.")
    private String description = "";

    @Schema(title = "가격", example = "10000")
    private Long price = 0L;

    @Schema(title = "업체 ID", example = "a0b1a2b3-4c5d-6e7f-8g9h-0a1b2c3d4e5f")
    private UUID companyId;

    @Schema(title = "허브 ID", example = "e1f2g3h4-i5j6-7k8l-9m0n-1o2p3q4r5s6t")
    private UUID hubId;
}
package com.two_ddang.logistics.company.presentation.dtos.company;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(title = "재고 요청 DTO", description = "상품 재고 요청을 위한 데이터 전송 객체")
public class RestockRequest {

    @Schema(title = "상품 ID", example = "d1f0c3e9-8a1b-45d9-bc3b-2d7f9c0b789e")
    private UUID productId;

    @Schema(title = "수량", example = "100", description = "재고 요청 수량")
    private Integer quantity;
}

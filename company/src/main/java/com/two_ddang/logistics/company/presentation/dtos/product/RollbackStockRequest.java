package com.two_ddang.logistics.company.presentation.dtos.product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(title = "재고 롤백 요청 DTO", description = "재고 롤백 요청을 위한 데이터 전송 객체")
public class RollbackStockRequest {

    @Schema(title = "롤백할 수량", example = "10", description = "재고에서 롤백할 수량")
    private Integer rollbackQuantity;
}
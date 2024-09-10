package com.two_ddang.logistics.company.presentation.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RollbackStockRequest {

    private Integer rollbackQuantity;
}

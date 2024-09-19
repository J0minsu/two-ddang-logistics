package com.two_ddang.logistics.order.presentation.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Create order product request")
public class CreateOrderProductRequest {

    @Schema(description = "Product ID")
    private UUID productId;

    @Schema(description = "Quantity of the product")
    private Integer quantity;
}

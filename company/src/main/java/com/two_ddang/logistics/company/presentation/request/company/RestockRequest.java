package com.two_ddang.logistics.company.presentation.request.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RestockRequest {

    private UUID productId;
    private Integer quantity;
}

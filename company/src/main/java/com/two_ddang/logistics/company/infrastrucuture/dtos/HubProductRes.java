package com.two_ddang.logistics.company.infrastrucuture.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class HubProductRes {

    private UUID hubId;
    private UUID productId;
    private UUID companyId;
    private String productName;
    private int quantity;



}

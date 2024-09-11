package com.two_ddang.logistics.order.infrastructure.dtos;


import com.two_ddang.logistics.core.entity.CompanyType;
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
public class CompanyDetailResponse {

    private UUID companyId;
    private String companyName;
    private CompanyType companyType;
    private UUID hubId;
    private String hubName;
    private String address;
    private String companyManager;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CompanyDetailResponse(String companyName, String address, String phone) {
    }
}

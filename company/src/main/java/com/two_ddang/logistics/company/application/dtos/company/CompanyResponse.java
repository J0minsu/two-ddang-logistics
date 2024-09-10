package com.two_ddang.logistics.company.application.dtos.company;


import com.two_ddang.logistics.company.domain.model.company.Company;
import com.two_ddang.logistics.company.domain.model.company.CompanyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponse {

    private UUID companyId;
    private String companyName;
    private CompanyType companyType;
    private UUID hubId;
    private String hubName;
    private Integer companyManager;
    private LocalDateTime createdAt;

    public static CompanyResponse of(Company company) {
        return CompanyResponse.builder()
                .companyId(company.getId())
                .companyName(company.getCompanyName())
                .companyType(company.getCompanyType())
                .hubId(company.getHubId())
                .companyManager(company.getCompanyManager())
                .createdAt(company.getCreatedAt())
                .build();
    }
}

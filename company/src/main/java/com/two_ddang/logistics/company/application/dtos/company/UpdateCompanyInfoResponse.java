package com.two_ddang.logistics.company.application.dtos.company;

import com.two_ddang.logistics.company.domain.model.company.Company;
import com.two_ddang.logistics.core.entity.CompanyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCompanyInfoResponse {

    private UUID companyId;
    private String companyName;
    private CompanyType companyType;
    private UUID hubId;
    private String hubName;
    private String address;
    private Integer companyManager;
    private LocalDateTime updatedAt;

    public static UpdateCompanyInfoResponse of(Company company, String hubName) {
        return UpdateCompanyInfoResponse.builder()
                .companyId(company.getId())
                .companyName(company.getCompanyName())
                .companyType(company.getCompanyType())
                .hubId(company.getHubId())
                .hubName(hubName)
                .address(company.getAddress())
                .companyManager(company.getCompanyManager())
                .updatedAt(company.getUpdatedAt())
                .build();
    }
}

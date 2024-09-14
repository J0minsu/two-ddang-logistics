package com.two_ddang.logistics.company.application.dtos.company;

import com.two_ddang.logistics.company.domain.model.company.Company;
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
public class CreateCompanyResponse {

    private UUID companyId;
    private String companyName;
    private CompanyType companyType;
    private UUID hubId;
    private String address;
    private String companyManager;
    private LocalDateTime createdAt;

    public static CreateCompanyResponse of(Company saveCompany) {
        return CreateCompanyResponse.builder()
                .companyId(saveCompany.getId())
                .companyName(saveCompany.getCompanyName())
                .companyType(saveCompany.getCompanyType())
                .hubId(saveCompany.getHubId())
                .address(saveCompany.getAddress())
                .companyManager(saveCompany.getCompanyName())
                .createdAt(saveCompany.getCreatedAt())
                .build();
    }
}

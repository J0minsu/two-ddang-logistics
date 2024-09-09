package com.two_ddang.logistics.company.presentation.response.company;

import com.two_ddang.logistics.company.domain.model.company.CompanyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCompanyResponse {

    private UUID companyId;
    private String companyName;
    private CompanyType companyType;
    private UUID hubId;
    private String hubName;
    private String address;
    private String companyManager;
    private LocalDateTime createdAt;
}

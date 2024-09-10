package com.two_ddang.logistics.company.presentation.dtos.company;

import com.two_ddang.logistics.company.domain.model.company.CompanyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateCompanyInfoRequest {

    private String companyName;
    private CompanyType companyType;
    private UUID hubId;
    private String address;
    private Integer companyManager;
}

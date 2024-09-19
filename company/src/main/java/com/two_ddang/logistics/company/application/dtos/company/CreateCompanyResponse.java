package com.two_ddang.logistics.company.application.dtos.company;

import com.two_ddang.logistics.company.domain.model.company.Company;
import com.two_ddang.logistics.core.entity.CompanyType;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(title = "업체 생성 응답 DTO", description = "업체 생성 후 반환되는 정보")
public class CreateCompanyResponse {

    @Schema(title = "업체 ID", example = "b0b1a1b6-2a4d-44b1-a1b6-2a4d44b1a1b6")
    private UUID companyId;

    @Schema(title = "업체명", example = "TwoDdang Logistics")
    private String companyName;

    @Schema(title = "업체 타입", example = "PRODUCER", description = "업체 유형 (PRODUCER, RECEIVER)")
    private CompanyType companyType;

    @Schema(title = "허브 ID", example = "e2cfd12e-1e47-4d2b-9a1c-57b144bca8a9")
    private UUID hubId;

    @Schema(title = "업체 주소", example = "123 Logistics Ave, Seoul, Korea")
    private String address;

    @Schema(title = "업체 관리자", example = "301")
    private Integer companyManager;

    @Schema(title = "생성일", example = "2024-09-17T10:15:30")
    private LocalDateTime createdAt;

    public static CreateCompanyResponse of(Company saveCompany) {
        return CreateCompanyResponse.builder()
                .companyId(saveCompany.getId())
                .companyName(saveCompany.getCompanyName())
                .companyType(saveCompany.getCompanyType())
                .hubId(saveCompany.getHubId())
                .address(saveCompany.getAddress())
                .companyManager(saveCompany.getCompanyManager())
                .createdAt(saveCompany.getCreatedAt())
                .build();
    }
}

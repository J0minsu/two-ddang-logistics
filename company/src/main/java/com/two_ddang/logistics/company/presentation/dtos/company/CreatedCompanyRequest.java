package com.two_ddang.logistics.company.presentation.dtos.company;

import com.two_ddang.logistics.core.entity.CompanyType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(title = "업체 생성 요청 DTO", description = "업체 생성 시 필요한 요청 데이터")
public class CreatedCompanyRequest {

    @Schema(title = "업체명", example = "TwoDdang Logistics")
    private String companyName;

    @Schema(title = "업체 타입", example = "PRODUCER", description = "업체 유형 (PRODUCER, RECEIVER)")
    private CompanyType companyType;

    @Schema(title = "허브 ID", example = "b0b1a1b6-2a4d-44b1-a1b6-2a4d44b1a1b6")
    private UUID hubId;

    @Schema(title = "업체 주소", example = "123 Logistics Ave, Seoul, Korea")
    private String address;

    @Schema(title = "업체 관리자 ID", example = "1001")
    private Integer companyManager;
}
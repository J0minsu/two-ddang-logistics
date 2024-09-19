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
@Schema(title = "업체 정보 수정 요청 DTO", description = "업체 정보를 수정하기 위한 요청 데이터 전송 객체")
public class UpdateCompanyInfoRequest {

    @Schema(title = "업체명", example = "TwoDdang Logistics")
    private String companyName;

    @Schema(title = "업체 유형", example = "PRODUCER", description = "업체 유형 (PRODUCER, RECEIVER)")
    private CompanyType companyType;

    @Schema(title = "허브 ID", example = "e2cfd12e-1e47-4d2b-9a1c-57b144bca8a9")
    private UUID hubId;

    @Schema(title = "업체 주소", example = "123 Logistics Ave, Seoul, Korea")
    private String address;

    @Schema(title = "업체 관리자 ID", example = "1001")
    private Integer companyManager;
}

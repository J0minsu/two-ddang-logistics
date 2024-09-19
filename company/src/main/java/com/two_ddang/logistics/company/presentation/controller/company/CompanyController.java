package com.two_ddang.logistics.company.presentation.controller.company;

import com.two_ddang.logistics.company.application.dtos.company.CompanyDetailResponse;
import com.two_ddang.logistics.company.application.dtos.company.CompanyResponse;
import com.two_ddang.logistics.company.application.dtos.company.CreateCompanyResponse;
import com.two_ddang.logistics.company.application.dtos.company.UpdateCompanyInfoResponse;
import com.two_ddang.logistics.company.application.service.company.CompanyService;
import com.two_ddang.logistics.company.presentation.dtos.company.CreatedCompanyRequest;
import com.two_ddang.logistics.company.presentation.dtos.company.RestockRequest;
import com.two_ddang.logistics.company.presentation.dtos.company.UpdateCompanyInfoRequest;
import com.two_ddang.logistics.core.util.CommonApiResponses;
import com.two_ddang.logistics.core.util.CurrentPassport;
import com.two_ddang.logistics.core.util.Passport;
import com.two_ddang.logistics.core.util.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SecurityRequirement(name = "Bearer Authentication")
@SecurityScheme( name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "Bearer")
@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
@Tag(name = "업체 API")
public class CompanyController {

    private final CompanyService companyService;


    @PostMapping
    @Operation(summary = "업체 생성", description = "업체 생성 API")
    public ResponseEntity<ResponseDTO<CreateCompanyResponse>> createCompany(
            @RequestBody CreatedCompanyRequest createdCompanyRequestDto,
            @Parameter(hidden = true) @CurrentPassport Passport passport)
    {
        return ResponseEntity.ok(ResponseDTO.okWithData(companyService.createCompany(createdCompanyRequestDto, passport)));
    }

    @GetMapping
    @Operation(summary = "업체 리스트 조회", description = "업체 리스트 조회 API")
    public ResponseEntity<ResponseDTO<Page<CompanyResponse>>> getCompanies(
            @PageableDefault(page = 0,
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam("keyword") String keyword)
    {
        return ResponseEntity.ok(ResponseDTO.okWithData(companyService.getCompanies(pageable, keyword)));
    }

    @GetMapping("/{companyId}")
    @Operation(summary = "업체 단건 조회", description = "업체 단건 조회 API")
    public ResponseEntity<ResponseDTO<CompanyDetailResponse>> getCompany(@PathVariable UUID companyId) {
        return ResponseEntity.ok(ResponseDTO.okWithData(companyService.getCompany(companyId)));
    }

    @PatchMapping("/{companyId}")
    @Operation(summary = "업체 정보 수정", description = "업체 정보 수정 API")
    public ResponseEntity<ResponseDTO<UpdateCompanyInfoResponse>> updateCompanyInfo(
            @PathVariable UUID companyId,
            @RequestBody UpdateCompanyInfoRequest updateCompanyRequestDto,
            @Parameter(hidden = true) @CurrentPassport Passport passport)
    {
        return ResponseEntity.ok(ResponseDTO.okWithData(
                companyService.updateCompanyInfo(companyId, updateCompanyRequestDto, passport)));
    }

    @DeleteMapping("/{companyId}")
    @Operation(summary = "업체 삭제", description = "업체 삭제 API")
    public ResponseEntity<ResponseDTO<Void>> deleteCompany(@PathVariable UUID companyId,
                                                           @Parameter(hidden = true) @CurrentPassport Passport passport) {
        companyService.deleteCompany(companyId, passport);
        return ResponseEntity.ok(ResponseDTO.ok());
    }

    @PostMapping("/{companyId}/restock")
    @Operation(summary = "업체 입고 요청", description = "업체 입고 요청 API")
    public ResponseEntity<ResponseDTO<Void>> restock(@PathVariable UUID companyId,
                                                     @RequestBody RestockRequest restockRequest) {
        companyService.restock(companyId, restockRequest);
        return ResponseEntity.ok(ResponseDTO.ok());
    }
}

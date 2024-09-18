package com.two_ddang.logistics.company.presentation.controller.company;

import com.two_ddang.logistics.company.application.dtos.company.CompanyDetailResponse;
import com.two_ddang.logistics.company.application.dtos.company.CompanyResponse;
import com.two_ddang.logistics.company.application.dtos.company.CreateCompanyResponse;
import com.two_ddang.logistics.company.application.dtos.company.UpdateCompanyInfoResponse;
import com.two_ddang.logistics.company.application.service.company.CompanyService;
import com.two_ddang.logistics.company.presentation.dtos.company.CreatedCompanyRequest;
import com.two_ddang.logistics.company.presentation.dtos.company.RestockRequest;
import com.two_ddang.logistics.company.presentation.dtos.company.UpdateCompanyInfoRequest;
import com.two_ddang.logistics.core.util.CurrentPassport;
import com.two_ddang.logistics.core.util.Passport;
import com.two_ddang.logistics.core.util.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;


    @PostMapping
    public ResponseEntity<ResponseDTO<CreateCompanyResponse>> createCompany(
            @RequestBody CreatedCompanyRequest createdCompanyRequestDto,
            @CurrentPassport Passport passport)
    {
        return ResponseEntity.ok(ResponseDTO.okWithData(companyService.createCompany(createdCompanyRequestDto, passport)));
    }

    @GetMapping
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
    public ResponseEntity<ResponseDTO<CompanyDetailResponse>> getCompany(@PathVariable UUID companyId) {
        return ResponseEntity.ok(ResponseDTO.okWithData(companyService.getCompany(companyId)));
    }

    @PatchMapping("/{companyId}")
    public ResponseEntity<ResponseDTO<UpdateCompanyInfoResponse>> updateCompanyInfo(
            @PathVariable UUID companyId,
            @RequestBody UpdateCompanyInfoRequest updateCompanyRequestDto,
            @CurrentPassport Passport passport)
    {
        return ResponseEntity.ok(ResponseDTO.okWithData(
                companyService.updateCompanyInfo(companyId, updateCompanyRequestDto, passport)));
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<ResponseDTO<Void>> deleteCompany(@PathVariable UUID companyId,
                                                           @CurrentPassport Passport passport) {
        companyService.deleteCompany(companyId, passport);
        return ResponseEntity.ok(ResponseDTO.ok());
    }

    @PostMapping("/{companyId}/restock")
    public ResponseEntity<ResponseDTO<Void>> restock(@PathVariable UUID companyId,
                                                     @RequestBody RestockRequest restockRequest) {
        companyService.restock(companyId, restockRequest);
        return ResponseEntity.ok(ResponseDTO.ok());
    }
}

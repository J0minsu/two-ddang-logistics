package com.two_ddang.logistics.company.presentation.controller.company;

import com.two_ddang.logistics.company.presentation.request.company.CreatedCompanyRequest;
import com.two_ddang.logistics.company.presentation.request.company.RestockRequest;
import com.two_ddang.logistics.company.presentation.request.company.UpdateCompanyInfoRequest;
import com.two_ddang.logistics.company.presentation.response.company.CompanyDetailResponse;
import com.two_ddang.logistics.company.presentation.response.company.CompanyResponse;
import com.two_ddang.logistics.company.presentation.response.company.CreateCompanyResponse;
import com.two_ddang.logistics.company.presentation.response.company.UpdateCompanyInfoResponse;
import com.two_ddang.logistics.core.util.ResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody CreatedCompanyRequest createdCompanyRequestDto) {
        return ResponseEntity.ok(ResponseDTO.okWithData(CreateCompanyResponse.builder().build()));
    }

    @GetMapping
    public ResponseEntity<?> getCompanies(@PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                          @RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(ResponseDTO.okWithData(new ArrayList<CompanyResponse>()));
    }


    @GetMapping("/{companyId}")
    public ResponseEntity<?> getCompany(@PathVariable UUID companyId) {
        return ResponseEntity.ok(ResponseDTO.okWithData(CompanyDetailResponse.builder().build()));
    }


    @PatchMapping("/{companyId}")
    public ResponseEntity<?> updateCompanyInfo(@PathVariable UUID companyId, @RequestBody UpdateCompanyInfoRequest updateCompanyRequestDto) {
        return ResponseEntity.ok(ResponseDTO.okWithData(UpdateCompanyInfoResponse.builder().build()));
    }


    @DeleteMapping("/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable UUID companyId) {
        return ResponseEntity.ok(ResponseDTO.ok());
    }


    @PostMapping("/{companyId}/restock")
    public ResponseEntity<?> restock(@PathVariable UUID companyId, @RequestBody RestockRequest restockRequest) {
        return ResponseEntity.ok(ResponseDTO.ok());
    }
}

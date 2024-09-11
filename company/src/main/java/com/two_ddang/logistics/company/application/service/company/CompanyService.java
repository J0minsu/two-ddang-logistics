package com.two_ddang.logistics.company.application.service.company;

import com.two_ddang.logistics.company.application.dtos.company.CompanyDetailResponse;
import com.two_ddang.logistics.company.application.dtos.company.CompanyResponse;
import com.two_ddang.logistics.company.application.dtos.company.CreateCompanyResponse;
import com.two_ddang.logistics.company.application.dtos.company.UpdateCompanyInfoResponse;
import com.two_ddang.logistics.company.application.exception.BusinessException;
import com.two_ddang.logistics.company.application.service.product.ProductService;
import com.two_ddang.logistics.company.domain.model.company.Company;
import com.two_ddang.logistics.company.domain.model.product.Product;
import com.two_ddang.logistics.company.domain.repository.company.CompanyRepository;
import com.two_ddang.logistics.company.domain.repository.product.ProductRepository;
import com.two_ddang.logistics.company.infrastrucuture.HubService;
import com.two_ddang.logistics.company.infrastrucuture.dtos.RestockHubRequest;
import com.two_ddang.logistics.company.presentation.dtos.company.CreatedCompanyRequest;
import com.two_ddang.logistics.company.presentation.dtos.company.RestockRequest;
import com.two_ddang.logistics.company.presentation.dtos.company.UpdateCompanyInfoRequest;
import com.two_ddang.logistics.core.exception.ErrorCode;
import com.two_ddang.logistics.core.util.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final HubService hubService;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Transactional
    public CreateCompanyResponse createCompany(CreatedCompanyRequest createdCompanyRequestDto) {
        Company company = Company.create(createdCompanyRequestDto);
        companyRepository.save(company);
        return CreateCompanyResponse.of(company);
    }

    public Page<CompanyResponse> getCompanies(Pageable pageable, String keyword) {

        return companyRepository.findAll(pageable).map(CompanyResponse::of);
    }


    public CompanyDetailResponse getCompany(UUID companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        String hubName = hubService.getHubInfo(company.getHubId()).getData().getName();
        return CompanyDetailResponse.of(company, hubName);
    }

    @Transactional
    public UpdateCompanyInfoResponse updateCompanyInfo(UUID companyId, UpdateCompanyInfoRequest updateCompanyRequestDto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
        company.updateInfo(updateCompanyRequestDto);

        String hubName = hubService.getHubInfo(company.getHubId()).getData().getName();
        return UpdateCompanyInfoResponse.of(company, hubName);
    }

    @Transactional
    public void deleteCompany(UUID companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
//        company.delete();
    }


    public void restock(UUID companyId, RestockRequest restockRequest) {
        // 재입고 요청 오면 허브 물품 입고 api 호출하게 개발
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
        Product product = productRepository.findById(restockRequest.getProductId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        RestockHubRequest request = RestockHubRequest.create(product, restockRequest);

        ResponseDTO<Void> restock = hubService.restock(request);
    }
}

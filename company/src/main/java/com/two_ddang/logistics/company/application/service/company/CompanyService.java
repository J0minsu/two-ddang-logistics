package com.two_ddang.logistics.company.application.service.company;

import com.two_ddang.logistics.company.application.dtos.company.CompanyDetailResponse;
import com.two_ddang.logistics.company.application.dtos.company.CompanyResponse;
import com.two_ddang.logistics.company.application.dtos.company.CreateCompanyResponse;
import com.two_ddang.logistics.company.application.dtos.company.UpdateCompanyInfoResponse;
import com.two_ddang.logistics.company.application.exception.BusinessException;
import com.two_ddang.logistics.company.domain.model.company.Company;
import com.two_ddang.logistics.company.domain.model.product.Product;
import com.two_ddang.logistics.company.domain.repository.company.CompanyRepository;
import com.two_ddang.logistics.company.domain.repository.product.ProductRepository;
import com.two_ddang.logistics.company.infrastrucuture.HubService;
import com.two_ddang.logistics.company.infrastrucuture.dtos.RestockHubRequest;
import com.two_ddang.logistics.company.presentation.dtos.company.CreatedCompanyRequest;
import com.two_ddang.logistics.company.presentation.dtos.company.RestockRequest;
import com.two_ddang.logistics.company.presentation.dtos.company.UpdateCompanyInfoRequest;
import com.two_ddang.logistics.core.entity.UserType;
import com.two_ddang.logistics.core.exception.ErrorCode;
import com.two_ddang.logistics.core.util.Passport;
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

    @Transactional
    public CreateCompanyResponse createCompany(CreatedCompanyRequest createdCompanyRequestDto, Passport passport) {

        validateCreateCompanyRole(createdCompanyRequestDto, passport);

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
    public UpdateCompanyInfoResponse updateCompanyInfo(UUID companyId, UpdateCompanyInfoRequest updateCompanyRequestDto, Passport passport) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        checkRole(passport, company);

        company.updateInfo(updateCompanyRequestDto);

        String hubName = hubService.getHubInfo(company.getHubId()).getData().getName();
        return UpdateCompanyInfoResponse.of(company, hubName);
    }


    @Transactional
    public void deleteCompany(UUID companyId, Passport passport) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
        checkRole(passport, company);
        company.delete(passport.getUserId());
    }


    public void restock(UUID companyId, RestockRequest restockRequest) {
        // 재입고 요청 오면 허브 물품 입고 api 호출하게 개발
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
        Product product = productRepository.findById(restockRequest.getProductId())
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));

        RestockHubRequest request = RestockHubRequest.create(product, restockRequest);

        hubService.inboundProduct(product.getHubId(), request);
    }

    private void checkRole(Passport passport, Company company) {
        UserType userType = passport.getUserType();
        Integer userId = passport.getUserId();

        if (userType == UserType.DELIVERY) {
            throw new BusinessException(ErrorCode.CAN_NOT_ACTION_ROLE);
        }

        if (userType == UserType.COMPANY) {
            if (!company.getCompanyManager().equals(userId)) {
                throw new BusinessException(ErrorCode.CAN_NOT_ACTION_ROLE);
            }
        }

        if (userType == UserType.HUB) {
            UUID hubId = hubService.getHubIdByUserId(userId);
            if (!company.getHubId().equals(hubId)) {
                throw new BusinessException(ErrorCode.CAN_NOT_ACTION_ROLE);
            }
        }
    }

    private void validateCreateCompanyRole(CreatedCompanyRequest createdCompanyRequestDto, Passport passport) {
        UserType userType = passport.getUserType();
        if (userType == UserType.COMPANY || userType == UserType.DELIVERY) {
            throw new BusinessException(ErrorCode.CAN_NOT_ACTION_ROLE);
        }

        if (userType == UserType.HUB) {
            UUID hubId = hubService.getHubIdByUserId(passport.getUserId());
            if (!hubId.equals(createdCompanyRequestDto.getHubId())) {
                throw new BusinessException(ErrorCode.CAN_NOT_ACTION_ROLE);
            }
        }
    }
}

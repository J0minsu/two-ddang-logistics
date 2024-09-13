package com.two_ddang.logistics.order.infrastructure;

import com.two_ddang.logistics.core.exception.ErrorCode;
import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.application.exception.BusinessException;
import com.two_ddang.logistics.order.infrastructure.dtos.CompanyDetailResponse;
import com.two_ddang.logistics.order.infrastructure.dtos.CompanyProductResponse;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "company")
public interface CompanyFeignClient extends CompanyService {

    Logger log = LoggerFactory.getLogger(CompanyFeignClient.class);

    @CircuitBreaker(name = "companyService", fallbackMethod = "companyFallback")
    @GetMapping("/api/v1/companies/{companyId}")
    ResponseDTO<CompanyDetailResponse> getCompany(@PathVariable UUID companyId);

    @CircuitBreaker(name = "companyService", fallbackMethod = "productFallback")
    @GetMapping("/api/v1/products/companies/{companyId}")
    ResponseDTO<List<CompanyProductResponse>> getCompanyProducts(@PathVariable UUID companyId);


    default ResponseDTO<CompanyDetailResponse> companyFallback(UUID companyId, Throwable e) {
        if (e instanceof FeignException.NotFound) {
            log.error(e.getMessage());
            throw new BusinessException(ErrorCode.COMPANY_NOT_FOUND);
        }
        log.error(e.getMessage());
        throw new BusinessException(ErrorCode.SERVER_ERROR);
    }

    default ResponseDTO<CompanyProductResponse> productFallback(Throwable e) {
        if (e instanceof FeignException.NotFound) {
            log.error(e.getMessage());
            throw new BusinessException(ErrorCode.COMPANY_PRODUCT_NOT_FOUND);
        }
        log.error(e.getMessage());
        throw new BusinessException(ErrorCode.SERVER_ERROR);
    }
}

package com.two_ddang.logistics.hub.application.service.feign.company.fallback;

import com.two_ddang.logistics.hub.application.service.feign.company.CompanyFeignClient;
import com.two_ddang.logistics.hub.application.service.feign.company.dto.req.RestockRequest;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class CompanyFallback implements CompanyFeignClient {

    private final Throwable cause;

    public CompanyFallback(Throwable cause) {
        this.cause = cause;
    }


    @Override
    public void restock(UUID companyId, RestockRequest request) {

        if(cause instanceof FeignException.NotFound) {
            log.error("re-stock error!");
        }
        else {
            log.error("fallback error!");
        }

    }
}

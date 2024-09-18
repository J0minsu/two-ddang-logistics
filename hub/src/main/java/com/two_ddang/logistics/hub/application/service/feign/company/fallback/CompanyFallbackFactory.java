package com.two_ddang.logistics.hub.application.service.feign.company.fallback;

import com.two_ddang.logistics.hub.application.service.feign.transit.fallback.TransitFallback;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CompanyFallbackFactory implements FallbackFactory<CompanyFallback> {

    @Override
    public CompanyFallback create(Throwable cause) {

        if(cause instanceof FeignException) {
            log.error("catched exception", cause);
        }

        return null;
    }
}

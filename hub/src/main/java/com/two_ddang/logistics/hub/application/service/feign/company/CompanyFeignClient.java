package com.two_ddang.logistics.hub.application.service.feign.company;

import com.two_ddang.logistics.hub.application.service.feign.company.dto.req.RestockRequest;
import com.two_ddang.logistics.hub.application.service.feign.company.fallback.CompanyFallbackFactory;
import com.two_ddang.logistics.hub.infrastructrure.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "companies",
configuration = FeignClientConfig.class,
fallbackFactory = CompanyFallbackFactory.class)
public interface CompanyFeignClient extends CompanyService {

    @Override
    @PostMapping("/api/v1/companies/{companyId}/restock")
    void restock(@PathVariable UUID companyId, RestockRequest request);

}

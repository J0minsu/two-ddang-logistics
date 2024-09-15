package com.two_ddang.logistics.hub.application.service.feign.company;

import com.two_ddang.logistics.hub.application.service.feign.company.dto.req.RestockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface CompanyService {

    void restock(UUID companyId, RestockRequest request);

}

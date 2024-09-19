package com.two_ddang.logistics.company.infrastrucuture;

import com.two_ddang.logistics.company.application.exception.BusinessException;
import com.two_ddang.logistics.company.infrastrucuture.dtos.HubRes;
import com.two_ddang.logistics.company.infrastrucuture.dtos.RestockHubRequest;
import com.two_ddang.logistics.core.exception.ErrorCode;
import com.two_ddang.logistics.core.util.ResponseDTO;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(name = "hub")
public interface HubFeignClient extends HubService {

    Logger log = LoggerFactory.getLogger(HubFeignClient.class);

    @GetMapping("/api/v1/hubs/{hubId}")
    @CircuitBreaker(name = "hubService", fallbackMethod = "getHubInfoFallback")
    ResponseDTO<HubRes> getHubInfo(@PathVariable UUID hubId);

    @PostMapping("/api/v1/hubs/{hubId}/products/inbound")
    @CircuitBreaker(name = "hubService", fallbackMethod = "inboundProductFallback")
    void inboundProduct(@PathVariable UUID hubId, RestockHubRequest request);

    @GetMapping("/api/v1/hubs/users/{userId}")
    ResponseDTO<HubRes> findHubByMangerUserId(@PathVariable Integer userId);


    default ResponseDTO<HubRes> getHubInfoFallback(UUID hubId, Throwable e)  {
        log.error(e.getMessage());
        if (e instanceof FeignException.NotFound) {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }
        throw new BusinessException(ErrorCode.SERVER_ERROR);
    }

    default ResponseDTO<HubRes> inboundProductFallback(UUID hubId, RestockHubRequest request, Throwable e)  {
        log.error(e.getMessage());
        if (e instanceof FeignException.NotFound) {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }
        throw new BusinessException(ErrorCode.SERVER_ERROR);
    }

}

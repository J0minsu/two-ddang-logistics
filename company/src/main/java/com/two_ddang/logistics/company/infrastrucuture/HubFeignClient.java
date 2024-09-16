package com.two_ddang.logistics.company.infrastrucuture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.two_ddang.logistics.company.application.exception.BusinessException;
import com.two_ddang.logistics.company.infrastrucuture.dtos.HubInfo;
import com.two_ddang.logistics.company.infrastrucuture.dtos.HubProductRes;
import com.two_ddang.logistics.company.infrastrucuture.dtos.RestockHubRequest;
import com.two_ddang.logistics.core.exception.ErrorCode;
import com.two_ddang.logistics.core.util.ResponseDTO;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.UUID;

@FeignClient(name = "transit")
public interface HubFeignClient extends HubService {

    Logger log = LoggerFactory.getLogger(HubFeignClient.class);

    @PostMapping("/api/v1/hubs/{hubId}/products/inbound")
    @CircuitBreaker(name = "hubService", fallbackMethod = "hubFallback")
    ResponseDTO<HubProductRes> inboundProduct(@PathVariable UUID hubId, RestockHubRequest request);

    @GetMapping("/api/v1/hubs/{hubId}")
    ResponseDTO<HubInfo> getHubInfo(@PathVariable UUID hubId);

    default ResponseDTO<HubInfo> hubFallback(UUID hubId, Throwable e) throws JsonProcessingException {
        log.error(e.getMessage());
        if (e instanceof FeignException.NotFound) {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }
        throw new BusinessException(ErrorCode.SERVER_ERROR);
    }

}

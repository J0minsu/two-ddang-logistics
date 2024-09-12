package com.two_ddang.logistics.order.infrastructure;

import com.two_ddang.logistics.core.exception.ErrorCode;
import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.application.exception.BusinessException;
import com.two_ddang.logistics.order.infrastructure.dtos.HubProductOutboundRequest;
import com.two_ddang.logistics.order.infrastructure.dtos.HubProductRes;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(name = "hub")
public interface HubFeignClient extends HubService {

    Logger log = LoggerFactory.getLogger(CompanyFeignClient.class);

    @Override
    @PostMapping("/api/v1/hubs/{hubId}/products/outbound")
    @CircuitBreaker(name = "hubService", fallbackMethod = "hubFallback")
    ResponseEntity<ResponseDTO<HubProductRes>> order(@PathVariable UUID hubId, HubProductOutboundRequest request);


    default ResponseEntity<ResponseDTO<HubProductRes>> hubFallback(UUID hubId, HubProductOutboundRequest request, Throwable e) {
        log.error(e.getMessage());
        if (e instanceof FeignException.BadRequest) {
            throw new BusinessException(ErrorCode.NOT_ENOUGH_STOCK);
        }
        if (e instanceof FeignException.NotFound) {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        }
        throw new BusinessException(ErrorCode.SERVER_ERROR);
    }
}

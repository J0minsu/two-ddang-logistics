package com.two_ddang.logistics.order.infrastructure;

import com.two_ddang.logistics.core.exception.ErrorCode;
import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.application.exception.BusinessException;
import com.two_ddang.logistics.order.infrastructure.dtos.HubProductOutboundRequest;
import com.two_ddang.logistics.order.infrastructure.dtos.HubRes;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "transit")
public interface HubFeignClient extends HubService {

    Logger log = LoggerFactory.getLogger(CompanyFeignClient.class);

    @Override
    @PostMapping("/api/v1/hubs/{hubId}/products/outbound")
    @CircuitBreaker(name = "hubService", fallbackMethod = "outboundFallback")
    void outbound(@PathVariable UUID hubId, @RequestBody HubProductOutboundRequest request);

    @Override
    @GetMapping("/api/v1/hubs/users/{userId}")
    ResponseDTO<HubRes> findHubByMangerUserId(@PathVariable Integer userId);

    default void outboundFallback(UUID hubId, HubProductOutboundRequest request, Throwable e) {
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

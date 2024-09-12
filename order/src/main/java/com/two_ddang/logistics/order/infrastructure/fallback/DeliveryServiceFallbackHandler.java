package com.two_ddang.logistics.order.infrastructure.fallback;

import com.two_ddang.logistics.core.entity.OrderStatus;
import com.two_ddang.logistics.core.exception.ErrorCode;
import com.two_ddang.logistics.order.application.exception.BusinessException;
import com.two_ddang.logistics.order.domain.model.Order;
import com.two_ddang.logistics.order.infrastructure.DeliveryService;
import com.two_ddang.logistics.order.infrastructure.dtos.CompanyDetailResponse;
import com.two_ddang.logistics.order.infrastructure.dtos.DeliveryCreateRequest;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class DeliveryServiceFallbackHandler {

    private final DeliveryService deliveryService;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @CircuitBreaker(name = "deliveryService", fallbackMethod = "circuitbreakerFallback")
    @Retry(name = "deliveryServiceRetry", fallbackMethod = "retryFallback")
    public UUID createDelivery(Order order, UUID reqCompanyId, CompanyDetailResponse resCompany) {
        return deliveryService
                .create(DeliveryCreateRequest.of(order.getId(), reqCompanyId, resCompany))
                .getData()
                .getDeliveryId();
    }

    public UUID retryFallback(Order order, UUID reqCompanyId, CompanyDetailResponse resCompany, Throwable e) {
        order.updateStatus(OrderStatus.FAILED);
        io.github.resilience4j.circuitbreaker.CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("deliveryService");
        circuitBreaker.onError(0, TimeUnit.SECONDS,e);
        return null;
    }

    public UUID circuitbreakerFallback(Order order, UUID reqCompanyId, CompanyDetailResponse resCompany, Throwable e) {
        throw new BusinessException(ErrorCode.FAILED_CREATE_ORDER);
    }

}

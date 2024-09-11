package com.two_ddang.logistics.order.infrastructure.circuitbreaker;

import com.two_ddang.logistics.core.entity.OrderStatus;
import com.two_ddang.logistics.order.domain.model.Order;
import com.two_ddang.logistics.order.infrastructure.DeliveryService;
import com.two_ddang.logistics.order.infrastructure.dtos.CompanyDetailResponse;
import com.two_ddang.logistics.order.infrastructure.dtos.DeliveryCreateRequest;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeliveryServiceCircuitBreaker {

    private final DeliveryService deliveryService;

    @Retry(name = "deliveryService", fallbackMethod = "deliveryFallback")
    public UUID createDelivery(Order order, UUID reqCompanyId, CompanyDetailResponse resCompany) {
        return deliveryService
                .create(DeliveryCreateRequest.of(order.getId(), reqCompanyId, resCompany))
                .getData()
                .getDeliveryId();
    }

    public UUID deliveryFallback(Order order, UUID reqCompanyId, CompanyDetailResponse resCompany, Throwable e) {
        order.updateStatus(OrderStatus.FAILED);
        return null;
    }

}

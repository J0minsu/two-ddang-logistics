package com.two_ddang.logistics.ai.application.service.feign.delivery.fallback;

import com.two_ddang.logistics.ai.application.service.feign.delivery.DeliveryFeignClient;
import com.two_ddang.logistics.ai.application.service.feign.delivery.dto.DeliveryRes;
import com.two_ddang.logistics.ai.application.service.feign.delivery.dto.TransitRes;
import com.two_ddang.logistics.core.entity.DeliveryStatus;
import com.two_ddang.logistics.core.entity.DriverAgentType;
import com.two_ddang.logistics.core.util.ResponseDTO;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class DeliveryFallback implements DeliveryFeignClient {

    private final Throwable cause;

    public DeliveryFallback(Throwable cause) {
        this.cause = cause;
    }

//    @Override
//    public List<DeliveryRes> getTransitAddressAndSlackId(DriverAgentType agentType, DeliveryStatus status) {
//
//        if(cause instanceof FeignException.NotFound) {
//            log.info("getTransitAddressAndSlackId 메서드 호출 중 Not found 오류 발생");
//        }
//        return null;
//    }

    @Override
    public ResponseDTO<List<TransitRes>> hubTransitSchedule() {
        if(cause instanceof FeignException.NotFound) {
            log.info("hubTransitSchedule 메서드 호출 중 Not found 오류 발생");
        }
        return null;
    }

}

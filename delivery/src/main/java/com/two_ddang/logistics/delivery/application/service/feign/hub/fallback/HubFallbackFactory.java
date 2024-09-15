package com.two_ddang.logistics.delivery.application.service.feign.hub.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HubFallbackFactory implements FallbackFactory<HubFallback> {

    @Override
    public HubFallback create(Throwable cause) {
        log.info(cause.toString(), "Auth에서 User를 호출할 때 오류 발생했습니다.");
        return new HubFallback(cause);
    }

}

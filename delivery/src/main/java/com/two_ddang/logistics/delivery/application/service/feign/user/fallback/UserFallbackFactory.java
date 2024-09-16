package com.two_ddang.logistics.delivery.application.service.feign.user.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserFallbackFactory implements FallbackFactory<UserFallback> {

    @Override
    public UserFallback create(Throwable cause) {
        log.info(cause.toString(), "Auth에서 User를 호출할 때 오류 발생했습니다.");
        return new UserFallback(cause);
    }

}

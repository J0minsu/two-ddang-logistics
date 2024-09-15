package com.two_ddang.logistics.delivery.application.service.feign.user.fallback;

import com.two_ddang.logistics.delivery.application.service.feign.user.UserFeignClient;
import com.two_ddang.logistics.delivery.application.service.feign.user.dto.res.UserRes;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserFallback implements UserFeignClient {

    private final Throwable cause;

    public UserFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public UserRes findUserById(int userId) {

        if(cause instanceof FeignException.NotFound) {
            log.info("HubFallback ");
        }

        return null;
    }
}

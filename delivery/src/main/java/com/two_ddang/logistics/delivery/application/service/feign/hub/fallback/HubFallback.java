package com.two_ddang.logistics.delivery.application.service.feign.hub.fallback;

import com.two_ddang.logistics.delivery.application.service.feign.hub.HubFeignClient;
import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.req.HubRouteModifyRequest;
import com.two_ddang.logistics.delivery.application.service.feign.user.dto.res.UserRes;
import feign.Feign;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class HubFallback implements HubFeignClient {

    private final Throwable cause;

    public HubFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public void modifyRoutes(List<HubRouteModifyRequest> routes) {

        if(cause instanceof FeignException.NotFound) {

        }

    }
}

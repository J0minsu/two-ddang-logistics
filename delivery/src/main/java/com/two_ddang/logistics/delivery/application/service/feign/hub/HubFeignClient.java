package com.two_ddang.logistics.delivery.application.service.feign.hub;

import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.req.HubRouteModifyRequest;
import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.res.CollectionHolder;
import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.res.HubRes;
import com.two_ddang.logistics.delivery.application.service.feign.hub.fallback.HubFallbackFactory;
import com.two_ddang.logistics.delivery.infrastructrure.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(
        name = "hub",
configuration = FeignClientConfig.class,
fallbackFactory = HubFallbackFactory.class)
public interface HubFeignClient extends HubService {

    @Override
    @PostMapping("/api/v1/hubs/routes")
    void modifyRoutes(List<HubRouteModifyRequest> routes);

    @Override
    @GetMapping(value = "/api/v1/hubs", produces = "application/json")
    ResponseDTO<CollectionHolder<HubRes>> findAllHubs(Pageable pageable);
}

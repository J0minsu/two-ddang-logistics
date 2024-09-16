package com.two_ddang.logistics.delivery.application.service.feign.hub;

import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.req.HubRouteModifyRequest;
import com.two_ddang.logistics.delivery.application.service.feign.user.dto.res.UserRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HubService {

    void modifyRoutes(List<HubRouteModifyRequest> routes);

}

package com.two_ddang.logistics.delivery.application.service.feign.ai;

import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.req.HubRouteModifyRequest;

import java.util.List;

public interface AIService {

    void requestRoutes(List<HubRouteModifyRequest> routes);

}

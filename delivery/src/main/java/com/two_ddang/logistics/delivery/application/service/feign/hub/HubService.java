package com.two_ddang.logistics.delivery.application.service.feign.hub;

import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.req.HubRouteModifyRequest;
import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.res.CollectionHolder;
import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.res.HubRes;
import com.two_ddang.logistics.delivery.application.service.feign.user.dto.res.UserRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HubService {

    void modifyRoutes(List<HubRouteModifyRequest> routes);
    ResponseDTO<CollectionHolder<HubRes>> findAllHubs(Pageable pageable);

}

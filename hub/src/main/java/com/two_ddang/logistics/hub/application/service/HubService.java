package com.two_ddang.logistics.hub.application.service;

import com.two_ddang.logistics.core.entity.UserType;
import com.two_ddang.logistics.hub.application.service.feign.company.CompanyService;
import com.two_ddang.logistics.hub.application.service.feign.company.dto.req.RestockRequest;
import com.two_ddang.logistics.hub.domain.model.*;
import com.two_ddang.logistics.hub.domain.repository.HubAgentRepository;
import com.two_ddang.logistics.hub.domain.repository.HubRepository;
import com.two_ddang.logistics.hub.domain.repository.UserRepository;
import com.two_ddang.logistics.hub.domain.vo.HubProductVO;
import com.two_ddang.logistics.hub.domain.vo.HubRouteVO;
import com.two_ddang.logistics.hub.domain.vo.HubVO;
import com.two_ddang.logistics.hub.infrastructrure.exception.NoSuchElementApplicationException;
import com.two_ddang.logistics.hub.infrastructrure.exception.PermissionDeniedApplicationException;
import com.two_ddang.logistics.hub.presentation.request.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class HubService {

    private final HubRepository hubRepository;
    private final UserRepository userRepository;
    private final HubAgentRepository hubAgentRepository;
    private final CompanyService companyService;

    @Transactional
    @CachePut(cacheNames = "hubVO", key = "#result.id")
    public HubVO create(Integer userId, HubCreateRequest request) {

        User manager = userRepository.findByIdAndIsDeletedIsFalse(request.getManagerId())
                .orElseThrow(NoSuchElementApplicationException::new);

        if(manager.getRole() != UserType.HUB) {
            throw new PermissionDeniedApplicationException();
        }

        Hub hub = Hub.of(request.getName(), request.getAddress(), request.getLatitude(), request.getLongitude(), manager);

        hubRepository.save(hub);

        return HubVO.fromEntity(hub);

    }

    @Transactional
    public HubProductVO inboundProduct(UUID hubId, HubProductInboundRequest request) {

        Hub hub = hubRepository.findByIdAndIsDeletedIsFalse(hubId)
                .orElseThrow(NoSuchElementApplicationException::new);

        HubProduct hubProduct = hub.inbound(request.getProductId(), request.getCompanyId(), request.getProductName(), request.getQuantity());

        return HubProductVO.fromEntity(hubProduct);

    }

    @Transactional
    public HubProductVO outboundProduct(UUID hubId, HubProductOutboundRequest request) {

        Hub hub = hubRepository.findByIdAndIsDeletedIsFalse(hubId)
                        .orElseThrow(NoSuchElementApplicationException::new);

        HubProduct hubProduct = hub.findHubProductById(request.getProductId());

        if(hubProduct.isEnoughStock(request.getQuantity())) {
            companyService.restock(
                    hubProduct.getCompanyId(), new RestockRequest(hubProduct.getProductId(), request.getQuantity())
            );
        }
        HubProduct afterHubProduct = hub.outbound(request.getProductId(), request.getCompanyId(), request.getQuantity());

        return HubProductVO.fromEntity(afterHubProduct);

    }

    public HubRouteVO findRouteBetweenRoutes(UUID departHubId, UUID arriveHubId) {

        Hub departmentHub = hubRepository.findByIdAndIsDeletedIsFalse(departHubId)
                .orElseThrow(NoSuchElementApplicationException::new);

        HubRoute route = departmentHub.findRouteToHub(arriveHubId)
                    .orElseThrow(NoSuchElementApplicationException::new);

        return HubRouteVO.fromEntity(route);

    }

    public Page<HubVO> searchHub(int pageNumber, int size, String keyword, HubSortStandard sort) {

        Page<Hub> hubs = hubRepository.findByNameStartingWith(keyword, PageRequest.of(pageNumber, size, sort.getSort()));

        Page<HubVO> result = hubs.map(HubVO::fromEntity);

        return result;
    }

    @Cacheable(cacheNames = "hubVO", key = "args[0]")
    public HubVO findById(UUID hubId) {

        Hub hub = hubRepository.findById(hubId)
                .orElseThrow(NoSuchElementApplicationException::new);

        return HubVO.fromEntity(hub);
    }

    @Transactional
    @Cacheable(cacheNames = "hubVO", key = "args[0]")
    public HubVO modifyHub(UUID hubId, HubModifyRequest request) {

        Hub hub = hubRepository.findById(hubId)
                .orElseThrow(NoSuchElementApplicationException::new);

        hub.changeName(request.getName());

        return HubVO.fromEntity(hub);

    }

    @Transactional
    public void updateRouteBetweenRoutes(List<HubRouteModifyRequest> request) {

        request.forEach(o -> {
            Hub departmentHub = hubRepository.findByIdAndIsDeletedIsFalse(o.getDepartmentHubId())
                            .orElseThrow(NoSuchElementApplicationException::new);

            Hub arriveHub = hubRepository.findByIdAndIsDeletedIsFalse(o.getArriveHubId())
                            .orElseThrow(NoSuchElementApplicationException::new);

            HubRoute route = departmentHub.updateRouteBetweenRoutes(
                    arriveHub, o.getRoute(), o.getTakeTime());
        });

    }

    @Transactional
    public void softDelete(UUID hubId) {

        Hub arriveHub = hubRepository.findByIdAndIsDeletedIsFalse(hubId)
                                .orElseThrow(NoSuchElementApplicationException::new);

        /**
         * @TODO Auditing
         */
        arriveHub.delete(1);

    }

    public HubVO findHubByMangerUserId(Integer userId) {

        HubAgent hubAgent = hubAgentRepository.findByUserIdAndIsDeletedIsFalse(userId)
                .orElseThrow(NoSuchElementApplicationException::new);

        return HubVO.fromEntity(hubAgent.getHub());

    }
}

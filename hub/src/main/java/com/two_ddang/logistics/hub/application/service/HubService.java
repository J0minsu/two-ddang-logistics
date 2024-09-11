package com.two_ddang.logistics.hub.application.service;

import com.two_ddang.logistics.core.entity.UserType;
import com.two_ddang.logistics.hub.domain.model.Hub;
import com.two_ddang.logistics.hub.domain.model.HubProduct;
import com.two_ddang.logistics.hub.domain.model.HubRoute;
import com.two_ddang.logistics.hub.domain.model.User;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class HubService {

    private final HubRepository hubRepository;
    private final UserRepository userRepository;

    @Transactional
    public HubVO create(HubCreateRequest request) {

        User manager = userRepository.findByIdAndIsDeletedIsFalse(request.getManagerId())
                .orElseThrow(NoSuchElementApplicationException::new);

        if(manager.getRole() != UserType.HUB) {
            throw new PermissionDeniedApplicationException();
        }

        Hub hub = Hub.of(request.getName(), request.getAddress(), request.getLatitude(), request.getLongitude(), manager);

        return hub.toVO();

    }

    @Transactional
    public HubProductVO inboundProduct(UUID hubId, HubProductInboundRequest request) {

        Hub hub = hubRepository.findByIdAndIsDeletedIsFalse(hubId)
                .orElseThrow(NoSuchElementApplicationException::new);

        HubProduct hubProduct = hub.inbound(request.getProductId(), request.getCompanyId(), request.getProductName(), request.getQuantity());

        return hubProduct.toVO();

    }

    @Transactional
    public HubProductVO outboundProduct(UUID hubId, HubProductOutboundRequest request) {

        Hub hub = hubRepository.findByIdAndIsDeletedIsFalse(hubId)
                        .orElseThrow(NoSuchElementApplicationException::new);

        HubProduct hubProduct = hub.findHubProductById(request.getProductId());

        if(hubProduct.isEnoughStock(request.getQuantity())) {
            /**
             * TODO request to company-service, inbound
             */
        }
        HubProduct afterHubProduct = hub.outbound(request.getProductId(), request.getCompanyId(), request.getQuantity());

        return afterHubProduct.toVO();

    }

    public HubRouteVO findRouteBetweenRoutes(UUID departHubId, UUID arriveHubId) {

        Hub departmentHub = hubRepository.findByIdAndIsDeletedIsFalse(departHubId)
                .orElseThrow(NoSuchElementApplicationException::new);

        HubRoute route = departmentHub.findRouteToHub(arriveHubId)
                    .orElseThrow(NoSuchElementApplicationException::new);

        return route.toVO();

    }

    public Page<HubVO> searchHub(int pageNumber, int size, String keyword, HubSortStandard sort) {

        Page<Hub> hubs = hubRepository.findByNameStartingWith(keyword, PageRequest.of(pageNumber, size, sort.getSort()));

        Page<HubVO> result = hubs.map(Hub::toVO);

        return result;
    }

    public HubVO findById(UUID hubId) {

        Hub hub = hubRepository.findById(hubId)
                .orElseThrow(NoSuchElementApplicationException::new);

        return hub.toVO();
    }

    @Transactional
    public HubVO modifyHub(UUID hubId, HubModifyRequest request) {

        Hub hub = hubRepository.findById(hubId)
                .orElseThrow(NoSuchElementApplicationException::new);

        hub.changeName(request.getName());

        return hub.toVO();

    }

    @Transactional
    public HubRouteVO updateRouteBetweenRoutes(HubRouteModifyRequest request) {

        Hub departmentHub = hubRepository.findByIdAndIsDeletedIsFalse(request.getDepartmentHubId())
                        .orElseThrow(NoSuchElementApplicationException::new);

        Hub arriveHub = hubRepository.findByIdAndIsDeletedIsFalse(request.getArriveHubId())
                        .orElseThrow(NoSuchElementApplicationException::new);



        HubRoute route = departmentHub.updateRouteBetweenRoutes(
                arriveHub, request.getRoute(), request.getTakeTime()
        );

        return route.toVO();
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
}

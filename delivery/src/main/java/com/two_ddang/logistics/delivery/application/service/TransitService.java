package com.two_ddang.logistics.delivery.application.service;

import com.two_ddang.logistics.core.entity.DeliveryStatus;
import com.two_ddang.logistics.core.entity.TransitStatus;
import com.two_ddang.logistics.delivery.domain.model.Delivery;
import com.two_ddang.logistics.delivery.domain.model.DeliveryAgent;
import com.two_ddang.logistics.delivery.domain.model.Transit;
import com.two_ddang.logistics.delivery.domain.model.TransitRoute;
import com.two_ddang.logistics.delivery.domain.repository.DeliveryAgentRepository;
import com.two_ddang.logistics.delivery.domain.repository.DeliveryRepository;
import com.two_ddang.logistics.delivery.domain.repository.TransitRepository;
import com.two_ddang.logistics.delivery.domain.vo.TransitRouteVO;
import com.two_ddang.logistics.delivery.domain.vo.TransitVO;
import com.two_ddang.logistics.delivery.infrastructrure.exception.NoSuchElementApplicationException;
import com.two_ddang.logistics.delivery.presentation.request.TransitRouteArriveRequest;
import com.two_ddang.logistics.delivery.presentation.request.TransitSortStandard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class TransitService {

    private final TransitRepository transitRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryAgentRepository deliveryAgentRepository;

    @Transactional
    public TransitVO create(Integer userId, UUID hubId) {

        PageRequest pageRequest = PageRequest.of(0, 10, TransitSortStandard.CREATED_ASC.getSort());

        List<Delivery> deliveries = deliveryRepository.findByDepartmentHubIdAndDeliveryStatus(hubId, DeliveryStatus.BEFORE_TRANSIT, pageRequest);

        Set<UUID> arriveSet = deliveries.stream()
                .map(Delivery::getArriveHubId).collect(Collectors.toSet());

        DeliveryAgent transitAgent = deliveryAgentRepository.findByUserIdAndIsDeletedIsFalse(userId)
                .orElseThrow(NoSuchElementApplicationException::new);

        /**
         * AI Service Route 조회
         */

        /**
         * Hub Service 경로 업데이트 post
         */

        /**
         * Route 이동거리 700km 까지만 수행
         */

        /**
         * TODO sequence
         */
        Transit transit = Transit.of(transitAgent, arriveSet.size(), TransitStatus.WAIT, deliveries);

        return TransitVO.fromEntity(transit);

    }


    public TransitVO findById(UUID transitId) {

        Transit transit = transitRepository.findByIdAndIsDeletedIsFalse(transitId)
                .orElseThrow(NoSuchElementApplicationException::new);

        return TransitVO.fromEntity(transit);

    }

    public TransitRouteVO findRouteByTransitAndRouteId(UUID transitId, UUID routeId) {

        Transit transit = transitRepository.findByIdAndIsDeletedIsFalse(transitId)
                .orElseThrow(NoSuchElementApplicationException::new);

        TransitRoute transitRoute = transit.getSpecificRoute(routeId)
                .orElseThrow(NoSuchElementApplicationException::new);

        return TransitRouteVO.fromEntity(transitRoute);

    }

    public Page<TransitVO> search(TransitStatus status, PageRequest page) {

        Page<Transit> transit = transitRepository.findByTransitStatus(status, page);

        Page<TransitVO> result = transit.map(TransitVO::fromEntity);

        return result;
    }

    @Transactional
    public TransitVO startTransitRoute(UUID transitId, UUID routeId) {

        Transit transit = transitRepository.findByIdAndIsDeletedIsFalse(transitId)
                .orElseThrow(NoSuchElementApplicationException::new);

        transit.startTransitRoute(routeId);

        return TransitVO.fromEntity(transit);

    }

    @Transactional
    public TransitVO arriveTransitRoute(UUID transitId, UUID routeId, TransitRouteArriveRequest request) {

        Transit transit = transitRepository.findByIdAndIsDeletedIsFalse(transitId)
                .orElseThrow(NoSuchElementApplicationException::new);

        TransitRoute transitRoute = transit.getSpecificRoute(routeId)
                .orElseThrow(NoSuchElementApplicationException::new);

        /**
         * hub-service 에 경로 update
         */

        transit.arriveHub(routeId);

        return TransitVO.fromEntity(transit);

    }

    @Transactional
    public void softDelete(UUID transitId) {

        Transit transit = transitRepository.findByIdAndIsDeletedIsFalse(transitId)
                .orElseThrow(NoSuchElementApplicationException::new);

        int userId = 1;

        transit.delete(userId);

    }
}

package com.two_ddang.logistics.delivery.application.service;

import com.two_ddang.logistics.core.entity.DeliveryStatus;
import com.two_ddang.logistics.core.entity.DriveStatus;
import com.two_ddang.logistics.core.entity.DriverAgentType;
import com.two_ddang.logistics.core.entity.TransitStatus;
import com.two_ddang.logistics.delivery.application.service.feign.ai.AIService;
import com.two_ddang.logistics.delivery.application.service.feign.ai.dto.req.RecommendTransitRouteRequest;
import com.two_ddang.logistics.delivery.application.service.feign.ai.dto.req.TransitRouteRequest;
import com.two_ddang.logistics.delivery.application.service.feign.ai.dto.res.RecommendTransitRouteResponse;
import com.two_ddang.logistics.delivery.application.service.feign.hub.HubService;
import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.req.HubRouteModifyRequest;
import com.two_ddang.logistics.delivery.application.service.feign.hub.dto.res.HubRes;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class TransitService {

    private final TransitRepository transitRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryAgentRepository deliveryAgentRepository;

    private final HubService hubService;
    private final AIService aiService;

    private final LocalDate STANDARD = LocalDate.of(2022, 1, 1);

    @Transactional
    public List<TransitVO> createTransitSchedule() {

        LocalDate NOW = LocalDate.now();

        int page = (int) ChronoUnit.DAYS.between(STANDARD, NOW);

        Queue<DeliveryAgent> agents = new LinkedList<>(deliveryAgentRepository.findByTypeAndDriveStatusAndIsDeletedIsFalse(DriverAgentType.TRANSIT, DriveStatus.WAITING));

        PageRequest pageRequest = PageRequest.of(0, 20);
        List<HubRes> allHubs = hubService.findAllHubs(pageRequest).getData().getContent();
        Map<UUID, HubRes> hubMap = allHubs.stream().collect(Collectors.toMap(HubRes::getHubId, hub -> hub));

        List<Transit> result = new ArrayList<>();

        IntStream.range(1, allHubs.size() + 1)
                .boxed()
                .filter(i -> i % 2 == page)
                .forEach(i -> {

                    //운송 생성 기준 허브
                    HubRes thisHub = allHubs.get(i-1);

                    //해당 허브에 운송 전 건수 조회
                    List<Delivery> deliveries = deliveryRepository.findByDepartmentHubIdAndDeliveryStatus(allHubs.get(i).getHubId(), DeliveryStatus.BEFORE_TRANSIT);

                    //대기 중 운송 요원 조회
                    DeliveryAgent agent = agents.poll();
                    if(agent == null) {
                        return;
                    }

                    //각 도착지 허브 추출
                    Set<UUID> arriveSet = deliveries.stream()
                            .map(Delivery::getArriveHubId).collect(Collectors.toSet());

                    //AI 요청
                    RecommendTransitRouteResponse aiResult = aiService.recommendRoute(
                            new RecommendTransitRouteRequest(
                                    arriveSet.stream()
                                            .map(arrive ->
                                                    new TransitRouteRequest(thisHub.getHubId(), thisHub.getAddress(), arrive, hubMap.get(arrive).getAddress())
                                            ).toList()
                            )
                    ).getData();

                    //허브 서비스에 경로 업데이트
                    List<HubRouteModifyRequest> modifyRouteRequest = aiResult.getRoutes()
                            .values()
                            .stream()
                            .map(m -> new HubRouteModifyRequest(
                                    m.getDepartmentHubId(), m.getArriveHubId(), m.getEstimateTime(), m.getRoute(), agent.getId()
                    )).toList();

                    //비동기 호출
                    CompletableFuture.runAsync(() -> hubService.modifyRoutes(modifyRouteRequest));

                    //운송 생성
                    Transit transit = Transit.of(agent, arriveSet.size(), TransitStatus.WAIT, deliveries, aiResult.getRoutes());

                    result.add(transit);

        });

        return result.stream().map(TransitVO::fromEntity).toList();
    }


    /*@Transactional
    public TransitVO create(Integer userId, UUID hubId) {

        PageRequest pageRequest = PageRequest.of(0, 10, TransitSortStandard.CREATED_ASC.getSort());

        List<Delivery> deliveries = deliveryRepository.findByDepartmentHubIdAndDeliveryStatus(hubId, DeliveryStatus.BEFORE_TRANSIT);

        Set<UUID> arriveSet = deliveries.stream()
                .map(Delivery::getArriveHubId).collect(Collectors.toSet());

        DeliveryAgent transitAgent = deliveryAgentRepository.findByUserIdAndIsDeletedIsFalse(userId)
                .orElseThrow(NoSuchElementApplicationException::new);

        *//**
         * AI Service Route 조회
         *//*



        List<HubRouteModifyRequest> request = deliveries.stream()
                .map(i -> new HubRouteModifyRequest(
                        i.getDepartmentHubId(), i.getArriveHubId(),
                        1, "route",
                        transitAgent.getId())).toList();

        CompletableFuture.runAsync(() -> hubService.modifyRoutes(request));

        *//**
         * TODO sequence from passport
         *//*
        Transit transit = Transit.of(transitAgent, arriveSet.size(), TransitStatus.WAIT, deliveries);

        return TransitVO.fromEntity(transit);

    }*/


    public TransitVO findById(UUID transitId) {

        Transit transit = transitRepository.findByIdAndIsDeletedIsFalse(transitId)
                .orElseThrow(NoSuchElementApplicationException::new);

        return TransitVO.fromEntity(transit);

    }

    public TransitRouteVO findRouteByTransitAndRouteId(UUID transitId, UUID routeId) {

        Transit transit = transitRepository.findByIdAndIsDeletedIsFalse(transitId)
                .orElseThrow(NoSuchElementApplicationException::new);

        TransitRoute transitRoute = transit.getSpecificRouteId(routeId)
                .orElseThrow(NoSuchElementApplicationException::new);

        return TransitRouteVO.fromEntity(transitRoute);

    }

    public Page<TransitVO> search(TransitStatus status, PageRequest page) {

        Page<Transit> transit = transitRepository.findByTransitStatus(status, page);

        Page<TransitVO> result = transit.map(TransitVO::fromEntity);

        return result;
    }

    @Transactional
    public TransitVO startTransitRoute(UUID transitId, int sequence) {

        Transit transit = transitRepository.findByIdAndIsDeletedIsFalse(transitId)
                .orElseThrow(NoSuchElementApplicationException::new);

        transit.startTransitSequence(sequence);

        return TransitVO.fromEntity(transit);

    }

    @Transactional
    public TransitVO arriveTransitRoute(UUID transitId, int sequence, TransitRouteArriveRequest request) {

        Transit transit = transitRepository.findByIdAndIsDeletedIsFalse(transitId)
                .orElseThrow(NoSuchElementApplicationException::new);

        List<TransitRoute> transitRoutes = transit.getSpecificSequence(sequence);

        transitRoutes.forEach(i -> i.arriveTransit(request.getActualDistance(), request.getActualTime()));

        transit.arriveHub(transitRoutes.get(0).getArriveHubId());

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

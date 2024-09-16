package com.two_ddang.logistics.hub.presentation.controller;

import com.two_ddang.logistics.core.util.CommonApiResponses;
import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.hub.application.dto.HubProductRes;
import com.two_ddang.logistics.hub.application.dto.HubRes;
import com.two_ddang.logistics.hub.application.dto.HubRouteRes;
import com.two_ddang.logistics.hub.application.service.HubService;
import com.two_ddang.logistics.hub.domain.vo.HubProductVO;
import com.two_ddang.logistics.hub.domain.vo.HubRouteVO;
import com.two_ddang.logistics.hub.domain.vo.HubVO;
import com.two_ddang.logistics.hub.presentation.request.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@SecurityRequirement(name = "Bearer Authentication")
@SecurityScheme( name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "Bearer")
@RestController
@RequestMapping("/api/v1/hubs")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
@Tag(name = "허브 관리 API")
@CommonApiResponses
public class HubController {

    private final HubService hubService;

    @PostMapping
    @Operation(summary = "허브 생성", description = "허브 생성 API")
    public ResponseEntity<ResponseDTO<HubRes>> craete(@RequestBody HubCreateRequest request) {

        HubVO hubVO = hubService.create(request);

        HubRes result = HubRes.fromVO(hubVO);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @PostMapping("/{hubId}/products/inbound")
    @Operation(summary = "허브 물품 입고", description = "허브 물품 입고 API")
    public ResponseEntity<ResponseDTO<HubProductRes>> inboundProduct(
            @PathVariable UUID hubId, @RequestBody HubProductInboundRequest request) {

        HubProductVO hubProduct = hubService.inboundProduct(hubId, request);

        HubProductRes result = HubProductRes.fromVO(hubProduct);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @PatchMapping("/{hubId}/products/outbound")
    @Operation(summary = "허브 물품 출고", description = "허브 물품 출고 API")
    public ResponseEntity<ResponseDTO<HubProductRes>> order(
            @PathVariable UUID hubId, @RequestBody HubProductOutboundRequest request) {

        HubProductVO hubProduct = hubService.outboundProduct(hubId, request);

        HubProductRes result = HubProductRes.fromVO(hubProduct);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @GetMapping("/routes/{departHubId}/to/{arriveHubId}")
    @Operation(summary = "허브 간 이동 경로 조회", description = "허브 간 이동 경로 조회 API")
    public ResponseEntity<ResponseDTO<HubRouteRes>> findRouteBetweenRoutes(
            @PathVariable UUID departHubId, @PathVariable UUID arriveHubId) {

        HubRouteVO hubRoute = hubService.findRouteBetweenRoutes(departHubId, arriveHubId);

        HubRouteRes result = HubRouteRes.fromVO(hubRoute);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @GetMapping
    @Operation(summary = "허브 검색", description = "허브 검색 API")
    public ResponseEntity<ResponseDTO<Page<HubRes>>> search(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "CREATED_DESC") HubSortStandard sort
    ) {

        size = switch (size) {
            case 30 -> 30;
            case 50 -> 50;
            default -> 10;
        };

        Page<HubVO> hubs = hubService.searchHub(pageNumber, size, keyword, sort);

        Page<HubRes> result = hubs.map(HubRes::fromVO);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }


    @GetMapping("/{hubId}")
    @Operation(summary = "허브 상세 조회", description = "허브 상세 조회 API")
    public ResponseEntity<ResponseDTO<HubRes>> findById(@PathVariable UUID hubId) {

        HubVO hub = hubService.findById(hubId);

        HubRes result = HubRes.fromVO(hub);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @PostMapping("/routes")
    @Operation(summary = "허브 간 이동 경로 갱신", description = "허브 간 이동 경로 갱신 API")
    public ResponseEntity<ResponseDTO<Void>> updateRouteBetweenRoutes(
            @RequestBody List<HubRouteModifyRequest> request) {

        hubService.updateRouteBetweenRoutes(request);

//        HubRouteRes result = HubRouteRes.fromVO(route);

        return ResponseEntity.ok(ResponseDTO.ok());

    }

    @PatchMapping("/{hubId}")
    @Operation(summary = "허브 수정", description = "허브 수정 API")
    public ResponseEntity<ResponseDTO<HubRes>> modifyHub(
            @PathVariable UUID hubId, @RequestBody HubModifyRequest request) {

        HubVO hub = hubService.modifyHub(hubId, request);

        HubRes result = HubRes.fromVO(hub);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }



    @DeleteMapping("/{hubId}")
    @Operation(summary = "허브 논리적 삭제", description = "하브 논리적 삭제 API")
    public ResponseEntity<ResponseDTO<Void>> softDelete(@PathVariable UUID hubId) {

        hubService.softDelete(hubId);

        return ResponseEntity.ok(ResponseDTO.okWithData(null));

    }

}

package com.two_ddang.logistics.hub.presentation.controller;

import com.two_ddang.logistics.core.util.CommonApiResponses;
import com.two_ddang.logistics.hub.application.dto.HubProductRes;
import com.two_ddang.logistics.hub.application.dto.HubRes;
import com.two_ddang.logistics.hub.application.dto.HubRouteRes;
import com.two_ddang.logistics.hub.holder.Result;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

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

    @PostMapping
    @Operation(summary = "허브 생성", description = "허브 생성 API")
    public ResponseEntity<Result<HubRes>> craete(@RequestBody HubCreateRequest request) {

        HubRes result = HubRes.example();

        return ResponseEntity.ok(Result.success(result));

    }

    @PostMapping("/{hubId}/products/inbound")
    @Operation(summary = "허브 물품 입고", description = "허브 물품 입고 API")
    public ResponseEntity<Result<HubProductRes>> inboundProduct(
            @PathVariable UUID hubId, @RequestBody HubProductInboundRequest request) {

        int remainQuantity = 5000;

        HubProductRes result = HubProductRes.fromVO(
                hubId, request.getProductId(), request.getCompanyId(),
                request.getProductName(), request.getQuantity() + remainQuantity);

        return ResponseEntity.ok(Result.success(result));

    }

    @PatchMapping("/{hubId}/products/outbound")
    @Operation(summary = "허브 물품 출고", description = "허브 수정 API")
    public ResponseEntity<Result<HubRes>> order(
            @PathVariable UUID hubId, @RequestBody HubProductOutboundRequest request) {

        HubRes result = HubRes.example();

        return ResponseEntity.ok(Result.success(result));

    }

    @GetMapping("/routes/{departHubId}/to/{arriveHunId}")
    @Operation(summary = "허브 간 이동 경로 조회", description = "허브 간 이동 경로 조회 API")
    public ResponseEntity<Result<HubRouteRes>> findRouteBetweenRoutes(
            @PathVariable UUID departHubId, @PathVariable UUID arriveHunId) {

        HubRouteRes result = HubRouteRes.example();

        return ResponseEntity.ok(Result.success(result));

    }

    @GetMapping
    @Operation(summary = "허브 검색", description = "허브 검색 API")
    public ResponseEntity<Result<Page<HubRes>>> search(
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

        List<HubRes> list = IntStream.range(1, size + 1).mapToObj(i -> HubRes.example()).toList();

        PageImpl result = new PageImpl(list, PageRequest.of(pageNumber + 1, size), 100);

        return ResponseEntity.ok(Result.success(result));

    }


    @GetMapping("/{hubId}")
    @Operation(summary = "허브 상세 조회", description = "허브 상세 조회 API")
    public ResponseEntity<Result<HubRes>> findById(@PathVariable UUID hubId) {

        HubRes result = HubRes.example();

        return ResponseEntity.ok(Result.success(result));

    }

    @PatchMapping("/{hubId}")
    @Operation(summary = "허브 수정", description = "허브 수정 API")
    public ResponseEntity<Result<HubRes>> modifyHub(
            @PathVariable UUID hubId, @RequestBody HubModifyRequest request) {

        HubRes result = HubRes.example();

        return ResponseEntity.ok(Result.success(result));

    }

    @PatchMapping("/routes")
    @Operation(summary = "허브 간 이동 경로 업데이트", description = "허브 간 이동 경로 업데이트 API")
    public ResponseEntity<Result<HubRouteRes>> modifyRouteBetweenRoutes(
            @RequestBody HubRouteModifyRequest request) {

        HubRouteRes result = HubRouteRes.example();

        return ResponseEntity.ok(Result.success(result));

    }


    @DeleteMapping("/{hubId}")
    @Operation(summary = "허브 논리적 삭제", description = "하브 논리적 삭제 API")
    public ResponseEntity<Result<Void>> softDelete(@PathVariable UUID hubId) {

//        DeliveryRes result = DeliveryRes.example(false);

        return ResponseEntity.ok(Result.success(null));

    }

}

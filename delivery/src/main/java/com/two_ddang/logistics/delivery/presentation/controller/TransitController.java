package com.two_ddang.logistics.delivery.presentation.controller;


import com.two_ddang.logistics.core.entity.TransitStatus;
import com.two_ddang.logistics.core.util.CommonApiResponses;
import com.two_ddang.logistics.core.util.CurrentPassport;
import com.two_ddang.logistics.core.util.Passport;
import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.delivery.application.dto.TransitRes;
import com.two_ddang.logistics.delivery.application.dto.TransitRouteRes;
import com.two_ddang.logistics.delivery.application.service.TransitService;
import com.two_ddang.logistics.delivery.domain.model.Transit;
import com.two_ddang.logistics.delivery.domain.vo.TransitRouteVO;
import com.two_ddang.logistics.delivery.domain.vo.TransitVO;
import com.two_ddang.logistics.delivery.presentation.request.TransitRouteArriveRequest;
import com.two_ddang.logistics.delivery.presentation.request.TransitSortStandard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.two_ddang.logistics.core.util.ValidationUtils.*;

//@SecurityRequirement(name = "Bearer Authentication")
//@SecurityScheme( name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "Bearer")
@RestController
@RequestMapping("/api/v1/transits")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "운송 API")
@CommonApiResponses
public class TransitController {

    private final TransitService transitService;

    @PostMapping("/schedules")
    @Operation(summary = "운송 스캐줄러로 생성", description = "운송 스캐줄러로 생성 API")
    public ResponseEntity<ResponseDTO<List<TransitRes>>> schedule() {

        List<TransitVO> transits = transitService.createTransitSchedule();

        List<TransitRes> result = transits.stream().map(TransitRes::fromEntity).toList();

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

//    @PostMapping("/hubs/{hubId}")
//    @Operation(summary = "허브에서 운송 생성", description = "허브에서 운송 생성 API")
//    public ResponseEntity<ResponseDTO<TransitRes>> craete(@PathVariable UUID hubId,
//                @CurrentPassport Passport passport) {
//
//        TransitVO transit = transitService.create(passport.getUserId(), hubId);
//
//        TransitRes result = TransitRes.fromEntity(transit);
//
//        return ResponseEntity.ok(ResponseDTO.okWithData(result));
//
//    }

    @GetMapping("/{transitId}")
    @Operation(summary = "운송 상세 조회", description = "운송 상세 조회 API")
    public ResponseEntity<ResponseDTO<TransitRes>> findById(@PathVariable UUID transitId) {

        TransitVO transit = transitService.findById(transitId);

        TransitRes result = TransitRes.fromEntity(transit);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }


    @GetMapping("/{transitId}/routes/{routeId}")
    @Operation(summary = "운송 경로 단건 조회", description = "운송 경로 단건 조회 API")
    public ResponseEntity<ResponseDTO<TransitRouteRes>> findRouteById(
            @PathVariable UUID transitId, @PathVariable UUID routeId) {

        TransitRouteVO transitRoute = transitService.findRouteByTransitAndRouteId(transitId, routeId);

        TransitRouteRes result = TransitRouteRes.fromEntity(transitRoute);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }


    @GetMapping
    @Operation(summary = "운송 검색", description = "운송 검색 API")
    public ResponseEntity<ResponseDTO<Page<TransitRes>>> search(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") TransitStatus status,
            @RequestParam(defaultValue = "CREATED_DESC") TransitSortStandard sort
    ) {

        size = switch (size) {
            case 30 -> 30;
            case 50 -> 50;
            default -> 10;
        };

        Page<TransitVO> transits = transitService.search(status, PageRequest.of(pageNumber, size, sort.getSort()));

        Page<TransitRes> result = transits.map(TransitRes::fromEntity);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }


    @PatchMapping("/{transitId}/routes/{routeId}/transit")
    @Operation(summary = "운송 시작", description = "운송 시작 API")
    public ResponseEntity<ResponseDTO<TransitRes>> startTransitRoute(
            @PathVariable UUID transitId, @PathVariable UUID routeId) {

        TransitVO transit = transitService.startTransitRoute(transitId, routeId);

        TransitRes result = TransitRes.fromEntity(transit);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @PatchMapping("/{transitId}/routes/{routeId}/arrive")
    @Operation(summary = "경유 허브 도착", description = "경유 허브 도착 API")
    public ResponseEntity<ResponseDTO<TransitRes>> arriveTransitRoute(
            @PathVariable UUID transitId, @PathVariable UUID routeId,
            @RequestBody TransitRouteArriveRequest request) {

        TransitVO transitVO = transitService.arriveTransitRoute(transitId, routeId, request);

        TransitRes result = TransitRes.fromEntity(transitVO);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @DeleteMapping("/{transitId}")
    @Operation(summary = "운송 논리적 삭제", description = "운송 논리적 삭제 API")
    public ResponseEntity<ResponseDTO<Void>> softDelete(@PathVariable UUID transitId) {

        transitService.softDelete(transitId);
//        DeliveryRes result = DeliveryRes.example(false);

        return ResponseEntity.ok(ResponseDTO.okWithData(null));

    }

}

package com.two_ddang.logistics.delivery.presentation.controller;


import com.two_ddang.logistics.core.util.CommonApiResponses;
import com.two_ddang.logistics.delivery.application.dto.DeliveryRes;
import com.two_ddang.logistics.delivery.application.dto.TransitRes;
import com.two_ddang.logistics.delivery.domain.model.TransitStatus;
import com.two_ddang.logistics.delivery.holder.Result;
import com.two_ddang.logistics.delivery.presentation.request.DeliverySortStandard;
import com.two_ddang.logistics.delivery.presentation.request.TransitSortStandard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;


@SecurityRequirement(name = "Bearer Authentication")
@SecurityScheme( name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "Bearer")
@RestController
@RequestMapping("/api/v1/transits")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "운송 API")
@CommonApiResponses
public class TransitController {

    @PostMapping("/hubs/{hubId}")
    @Operation(summary = "허브에서 운송 요청", description = "허브에서 운송 요청 API")
    public ResponseEntity<Result<TransitRes>> craete(@PathVariable UUID hubId) {

        TransitRes result = TransitRes.example(10);

        return ResponseEntity.ok(Result.success(result));

    }

    @GetMapping("/{transitId}")
    @Operation(summary = "허브에서 운송 요청", description = "허브에서 운송 요청 API")
    public ResponseEntity<Result<TransitRes>> findById(@PathVariable UUID transitId) {

        TransitRes result = TransitRes.example(10);

        return ResponseEntity.ok(Result.success(result));

    }


    @GetMapping("/routes/{routeId}")
    @Operation(summary = "운송 경로 단건 조회", description = "운송 경로 단건 조회 API")
    public ResponseEntity<Result<TransitRes>> findRouteById(@PathVariable UUID routeId) {

        TransitRes result = TransitRes.example(10);

        return ResponseEntity.ok(Result.success(result));

    }


    @GetMapping
    @Operation(summary = "운송 검색", description = "운송 검색 API")
    public ResponseEntity<Result<Page<TransitRes>>> search(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "CREATED_DESC") TransitSortStandard sort
    ) {

        size = switch (size) {
            case 30 -> 30;
            case 50 -> 50;
            default -> 10;
        };

        List<TransitRes> list = IntStream.range(1, size + 1).mapToObj(i -> TransitRes.example(7)).toList();

        PageImpl result = new PageImpl(list, PageRequest.of(pageNumber + 1, size), 100);

        return ResponseEntity.ok(Result.success(result));

    }


    @PatchMapping("/routes/{routeId}/transit")
    @Operation(summary = "운송 시작", description = "운송 시작 API")
    public ResponseEntity<Result<TransitRes>> startTransit() {

        TransitRes result = TransitRes.example(7);

        return ResponseEntity.ok(Result.success(result));

    }

    @PatchMapping("/routes/{routeId}/arrive")
    @Operation(summary = "경유 허브 도착", description = "경유 허브 도착 API")
    public ResponseEntity<Result<TransitRes>> arriveTransit(@PathVariable UUID routeId) {

        TransitRes result = TransitRes.example(7);

        return ResponseEntity.ok(Result.success(result));

    }

    @DeleteMapping("/{transitId}")
    @Operation(summary = "운송 논리적 삭제", description = "운송 논리적 삭제 API")
    public ResponseEntity<Result<Void>> softDelete(@PathVariable UUID transitId) {

//        DeliveryRes result = DeliveryRes.example(false);

        return ResponseEntity.ok(Result.success(null));

    }

}

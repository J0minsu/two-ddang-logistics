package com.two_ddang.logistics.delivery.presentation.controller;


import com.two_ddang.logistics.core.util.CommonApiResponses;
import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.delivery.application.dto.DeliveryRes;
import com.two_ddang.logistics.delivery.presentation.request.DeliveryCreateRequest;
import com.two_ddang.logistics.delivery.presentation.request.DeliverySortStandard;
import com.two_ddang.logistics.delivery.presentation.request.DeliveryStartRequest;
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


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@SecurityRequirement(name = "Bearer Authentication")
@SecurityScheme( name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "Bearer")
@RestController
@RequestMapping("/api/v1/deliveries")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "배송 API")
@CommonApiResponses
public class DeliveryController {

    @PostMapping
    @Operation(summary = "배송 생성", description = "배송건 생성 API")
    public ResponseEntity<ResponseDTO<DeliveryRes>> craete(@RequestBody DeliveryCreateRequest request) {

        DeliveryRes result = DeliveryRes.fromVO(
                UUID.randomUUID(),
                null,
                request.getOrderId(),
                request.getDepartHubId(),
                request.getArrivedHubId(),
                request.getDeliveryAddress(),
                request.getReceiveCompanyId(),
                request.getDeliveryStatus(),
                LocalDateTime.now()
        );

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @GetMapping("/{deliveryId}")
    @Operation(summary = "배송 단건 조회", description = "배송 단건 조회 API")
    public ResponseEntity<ResponseDTO<DeliveryRes>> findById(@PathVariable UUID deliveryId) {

        DeliveryRes result = DeliveryRes.example(false);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }


    @GetMapping
    @Operation(summary = "배송 검색", description = "배송 검색 API")
    public ResponseEntity<ResponseDTO<Page<DeliveryRes>>> search(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "CREATED_DESC") DeliverySortStandard sort
    ) {

        size = switch (size) {
            case 30 -> 30;
            case 50 -> 50;
            default -> 10;
        };

        List<DeliveryRes> list = IntStream.range(1, size + 1).mapToObj(i -> DeliveryRes.example(true)).toList();

        PageImpl result = new PageImpl(list, PageRequest.of(pageNumber + 1, size), 100);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }


    @PatchMapping("/{deliveryId}/delivery")
    @Operation(summary = "배송 시작", description = "배송건 시작 API")
    public ResponseEntity<ResponseDTO<DeliveryRes>> startDelivery() {

        DeliveryRes result = DeliveryRes.example(false);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @DeleteMapping("/{deliveryId}")
    @Operation(summary = "배송 논리적 삭제", description = "배송 논리적 삭제 API")
    public ResponseEntity<ResponseDTO<Void>> softDelete(@PathVariable UUID deliveryId) {

//        DeliveryRes result = DeliveryRes.example(false);

        return ResponseEntity.ok(ResponseDTO.ok());

    }

}

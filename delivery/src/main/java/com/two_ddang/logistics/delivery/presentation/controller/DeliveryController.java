package com.two_ddang.logistics.delivery.presentation.controller;


import com.two_ddang.logistics.core.entity.DeliveryStatus;
import com.two_ddang.logistics.core.entity.DriverAgentType;
import com.two_ddang.logistics.core.util.CommonApiResponses;
import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.delivery.application.dto.DeliveryAgentRes;
import com.two_ddang.logistics.delivery.application.dto.DeliveryRes;
import com.two_ddang.logistics.delivery.application.service.DeliveryService;
import com.two_ddang.logistics.delivery.domain.vo.DeliveryAgentVO;
import com.two_ddang.logistics.delivery.domain.vo.DeliveryVO;
import com.two_ddang.logistics.delivery.infrastructrure.exception.PermissionDeniedApplicationException;
import com.two_ddang.logistics.delivery.presentation.request.DeliveryAgentEntryRequest;
import com.two_ddang.logistics.delivery.presentation.request.DeliveryCreateRequest;
import com.two_ddang.logistics.delivery.presentation.request.DeliverySortStandard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.two_ddang.logistics.core.util.ValidationUtils.*;


//@SecurityRequirement(name = "Bearer Authentication")
//@SecurityScheme( name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "Bearer")
@RestController
@RequestMapping("/api/v1/deliveries")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "배송 API")
@CommonApiResponses
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/users")
    @Operation(summary = "배송 기사 등록", description = "배송 기사 등록 API")
    public ResponseEntity<ResponseDTO<DeliveryAgentRes>> entryDriverAgent(DeliveryAgentEntryRequest request) {

        if(request.getType() != DriverAgentType.DELIVERY || request.getHubId() == null) {
            throw new PermissionDeniedApplicationException();
        }

        DeliveryAgentVO agent = deliveryService.entryDeliveryAgent(request);

        DeliveryAgentRes result = DeliveryAgentRes.fromVO(agent);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @PostMapping
    @Operation(summary = "배송 생성", description = "배송건 생성 API")
    public ResponseEntity<ResponseDTO<DeliveryRes>> craete(@RequestBody DeliveryCreateRequest request) {



        DeliveryVO delivery = deliveryService.createDelivery(request);

        System.out.println("delivery = " + delivery);

        DeliveryRes result = DeliveryRes.fromVO(delivery);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @GetMapping("/{deliveryId}")
    @Operation(summary = "배송 단건 조회", description = "배송 단건 조회 API")
    public ResponseEntity<ResponseDTO<DeliveryRes>> findById(@PathVariable UUID deliveryId) {

        DeliveryVO delivery = deliveryService.findById(deliveryId);

        DeliveryRes result = DeliveryRes.fromVO(delivery);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }


    @GetMapping
    @Operation(summary = "배송 검색", description = "배송 검색 API")
    public ResponseEntity<ResponseDTO<Page<DeliveryRes>>> search(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") DeliveryStatus status,
            @RequestParam(defaultValue = "CREATED_DESC") DeliverySortStandard sort
    ) {

        size = switch (size) {
            case 30 -> 30;
            case 50 -> 50;
            default -> 10;
        };

        Page<DeliveryVO> deliveries = deliveryService.search(status, PageRequest.of(pageNumber, size, sort.getSort()));

        Page<DeliveryRes> result = deliveries.map(DeliveryRes::fromVO);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }


    @PatchMapping("/{deliveryId}/delivery")
    @Operation(summary = "배송 시작", description = "배송건 시작 API")
    public ResponseEntity<ResponseDTO<DeliveryRes>> startDelivery(@PathVariable UUID deliveryId) {

        /**
         * TODO Passport 에서 사용자 정보
         */
        Integer userId = 1;

        DeliveryVO delivery = deliveryService.startDelivery(userId, deliveryId);

        DeliveryRes result = DeliveryRes.fromVO(delivery);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @PatchMapping("/{deliveryId}/finish")
    @Operation(summary = "배송 종료", description = "배송건 종료 API")
    public ResponseEntity<ResponseDTO<DeliveryRes>> finishDelivery(@PathVariable UUID deliveryId) {

        /**
         * TODO Passport 에서 사용자 정보
         */
        Integer userId = 1;

        DeliveryVO delivery = deliveryService.finishDelivery(deliveryId);

        DeliveryRes result = DeliveryRes.fromVO(delivery);

        return ResponseEntity.ok(ResponseDTO.okWithData(result));

    }

    @DeleteMapping("/{deliveryId}")
    @Operation(summary = "배송 논리적 삭제", description = "배송 논리적 삭제 API")
    public ResponseEntity<ResponseDTO<Void>> softDelete(@PathVariable UUID deliveryId) {

//        DeliveryRes result = DeliveryRes.example(false);

        deliveryService.softDelete(deliveryId);

        return ResponseEntity.ok(ResponseDTO.ok());

    }

}

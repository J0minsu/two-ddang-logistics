package com.two_ddang.logistics.order.presentation.controller;

import com.two_ddang.logistics.core.util.CurrentPassport;
import com.two_ddang.logistics.core.util.Passport;
import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.application.dtos.response.*;
import com.two_ddang.logistics.order.application.service.OrderService;
import com.two_ddang.logistics.order.infrastructure.swagger.SwaggerOrderController;
import com.two_ddang.logistics.order.presentation.dtos.CreateOrderRequest;
import com.two_ddang.logistics.order.presentation.dtos.UpdateOrderStatusRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "주문 API")
public class OrderController implements SwaggerOrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "주문 생성", description = "주문 생성 API")
    public ResponseEntity<ResponseDTO<CreateOrderResponse>> createOrder(@RequestBody CreateOrderRequest createOrderRequestDto) {
        return ResponseEntity.ok(ResponseDTO.okWithData(orderService.createOrder(createOrderRequestDto)));
    }


    @GetMapping
    @Operation(summary = "주문 리스트 조회", description = "주문 리스트 조회 API")
    public ResponseEntity<ResponseDTO<Page<OrderResponse>>> getOrders(
            @PageableDefault(page = 0,
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @CurrentPassport Passport passport)
    {
        return ResponseEntity.ok(ResponseDTO.okWithData(orderService.getOrders(pageable, keyword, passport)));
    }


    @GetMapping("/{orderId}")
    @Operation(summary = "주문 단건 조회", description = "주문 단건 조회 API")
    public ResponseEntity<ResponseDTO<OrderDetailResponse>> getOrder(@PathVariable("orderId") UUID orderId,
                                                                     @CurrentPassport Passport passport) {
        return ResponseEntity.ok(ResponseDTO.okWithData(orderService.getOrder(orderId, passport)));
    }


    @PatchMapping("/{orderId}")
    @Operation(summary = "주문 상태 변경", description = "주문 상태 변경 API")
    public ResponseEntity<ResponseDTO<UpdateOrderStatusResponse>> updateOrderStatus(
            @PathVariable("orderId") UUID orderId,
            @RequestBody UpdateOrderStatusRequest updateOrderStatusRequestDto,
            @CurrentPassport Passport passport) {

        return ResponseEntity.ok(ResponseDTO.okWithData(orderService.updateOrderStatus(orderId, updateOrderStatusRequestDto, passport)));
    }


    @PostMapping("/{orderId}/cancel")
    @Operation(summary = "주문 취소", description = "주문 취소 API")
    public ResponseEntity<ResponseDTO<CancelOrderResponse>> cancelOrder(@PathVariable("orderId") UUID orderId,
                                                                        @CurrentPassport Passport passport)
    {
        return ResponseEntity.ok(ResponseDTO.okWithData(orderService.cancelOrder(orderId, passport)));
    }


    @DeleteMapping("/{orderId}")
    @Operation(summary = "주문 삭제", description = "주문 삭제 API")
    public ResponseEntity<ResponseDTO<Void>> deleteOrder(@PathVariable("orderId") UUID orderId,
                                                         @CurrentPassport Passport passport) {
        orderService.deleteOrder(orderId, passport);
        return ResponseEntity.ok(ResponseDTO.ok());
    }

}

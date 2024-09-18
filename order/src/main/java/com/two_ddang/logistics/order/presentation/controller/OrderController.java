package com.two_ddang.logistics.order.presentation.controller;

import com.two_ddang.logistics.core.entity.UserType;
import com.two_ddang.logistics.core.util.CurrentPassport;
import com.two_ddang.logistics.core.util.Passport;
import com.two_ddang.logistics.core.util.PassportRoleCheck;
import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.application.dtos.response.*;
import com.two_ddang.logistics.order.application.service.OrderService;
import com.two_ddang.logistics.order.presentation.dtos.CreateOrderRequest;
import com.two_ddang.logistics.order.presentation.dtos.UpdateOrderStatusRequest;
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
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ResponseDTO<CreateOrderResponse>> createOrder(@RequestBody CreateOrderRequest createOrderRequestDto) {
        return ResponseEntity.ok(ResponseDTO.okWithData(orderService.createOrder(createOrderRequestDto)));
    }


    @GetMapping
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
    public ResponseEntity<ResponseDTO<OrderDetailResponse>> getOrder(@PathVariable("orderId") UUID orderId,
                                                                     @CurrentPassport Passport passport) {
        return ResponseEntity.ok(ResponseDTO.okWithData(orderService.getOrder(orderId, passport)));
    }


    @PatchMapping("/{orderId}")
    public ResponseEntity<ResponseDTO<UpdateOrderStatusResponse>> updateOrderStatus(
            @PathVariable("orderId") UUID orderId,
            @RequestBody UpdateOrderStatusRequest updateOrderStatusRequestDto,
            @CurrentPassport Passport passport) {

        return ResponseEntity.ok(ResponseDTO.okWithData(orderService.updateOrderStatus(orderId, updateOrderStatusRequestDto, passport)));
    }


    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<ResponseDTO<CancelOrderResponse>> cancelOrder(@PathVariable("orderId") UUID orderId,
                                                                        @CurrentPassport Passport passport)
    {
        return ResponseEntity.ok(ResponseDTO.okWithData(orderService.cancelOrder(orderId, passport)));
    }


    @DeleteMapping("/{orderId}")
    public ResponseEntity<ResponseDTO<Void>> deleteOrder(@PathVariable("orderId") UUID orderId,
                                                         @CurrentPassport Passport passport) {
        orderService.deleteOrder(orderId, passport);
        return ResponseEntity.ok(ResponseDTO.ok());
    }

}

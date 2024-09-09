package com.two_ddang.logistics.order.presentation.controller;

import com.two_ddang.logistics.order.common.utils.ResponseDTO;
import com.two_ddang.logistics.order.presentation.request.CreateOrderRequestDto;
import com.two_ddang.logistics.order.presentation.request.UpdateOrderStatusRequestDto;
import com.two_ddang.logistics.order.presentation.response.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @PostMapping
    public ResponseEntity<ResponseDTO<CreateOrderResponseDto>> createOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto) {
        return ResponseEntity.ok(ResponseDTO.okWithData(CreateOrderResponseDto.builder().build()));
    }


    @GetMapping
    public ResponseEntity<ResponseDTO<List<OrderResponseDto>>> getOrders(@PageableDefault(page = 0, size = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                                         @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return ResponseEntity.ok(ResponseDTO.okWithData(new ArrayList<OrderResponseDto>()));
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDTO<OrderDetailResponseDto>> getOrder(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(ResponseDTO.okWithData(OrderDetailResponseDto.builder().build()));
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<ResponseDTO<UpdateOrderStatusResponseDto>> updateOrderStatus(@PathVariable("orderId") UUID orderId,
                                                                       @RequestBody UpdateOrderStatusRequestDto updateOrderStatusRequestDto) {
        return ResponseEntity.ok(ResponseDTO.okWithData(UpdateOrderStatusResponseDto.builder().build()));
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<ResponseDTO<CancelOrderResponseDto>> cancelOrder(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(ResponseDTO.okWithData(CancelOrderResponseDto.builder().build()));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ResponseDTO<Void>> deleteOrder(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(ResponseDTO.ok());
    }

}

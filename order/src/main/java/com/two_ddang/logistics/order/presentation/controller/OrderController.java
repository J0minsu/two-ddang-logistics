package com.two_ddang.logistics.order.presentation.controller;

import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.presentation.request.CreateOrderRequest;
import com.two_ddang.logistics.order.presentation.request.UpdateOrderStatusRequest;
import com.two_ddang.logistics.order.presentation.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public ResponseEntity<ResponseDTO<CreateOrderResponse>> createOrder(@RequestBody CreateOrderRequest createOrderRequestDto) {
        return ResponseEntity.ok(ResponseDTO.okWithData(CreateOrderResponse.builder().build()));
    }


    @GetMapping
    public ResponseEntity<ResponseDTO<Page<OrderResponse>>> getOrders(@PageableDefault(page = 0, size = 0, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                                                                      @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        return ResponseEntity.ok(ResponseDTO.okWithData(new PageImpl<OrderResponse>(orderResponses)));
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDTO<OrderDetailResponse>> getOrder(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(ResponseDTO.okWithData(OrderDetailResponse.builder().build()));
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<ResponseDTO<UpdateOrderStatusResponse>> updateOrderStatus(@PathVariable("orderId") UUID orderId,
                                                                                    @RequestBody UpdateOrderStatusRequest updateOrderStatusRequestDto) {
        return ResponseEntity.ok(ResponseDTO.okWithData(UpdateOrderStatusResponse.builder().build()));
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<ResponseDTO<CancelOrderResponse>> cancelOrder(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(ResponseDTO.okWithData(CancelOrderResponse.builder().build()));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ResponseDTO<Void>> deleteOrder(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(ResponseDTO.ok());
    }

}

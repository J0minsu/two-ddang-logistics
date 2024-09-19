package com.two_ddang.logistics.order.infrastructure.swagger;

import com.two_ddang.logistics.core.util.Passport;
import com.two_ddang.logistics.core.util.ResponseDTO;
import com.two_ddang.logistics.order.application.dtos.response.*;
import com.two_ddang.logistics.order.presentation.dtos.CreateOrderRequest;
import com.two_ddang.logistics.order.presentation.dtos.UpdateOrderStatusRequest;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface SwaggerOrderController {

    @ApiResponse(responseCode = "200", description = "Successful response",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
        {
            "code": 200,
            "data": {
                "orderId": "UUID",
                "reqCompanyId": "UUID",
                "reqCompanyName": "업체A",
                "resCompanyId": "UUID",
                "resCompanyName": "업체A",
                "deliveryId": "UUID",
                "requestedBy": "요청자",
                "orderProducts": [
                    {
                        "productId": "UUID",
                        "productName": "상품B",
                        "quantity": 10,
                        "price": 10000
                    },
                    {
                        "productId": "UUID",
                        "productName": "상품B",
                        "quantity": 20,
                        "price": 20000
                    }
                ],
                "totalPrice": 100000,
                "orderStatus": "COMPLETED",
                "orderDate": "2024-09-04 12:12:12"
            },
            "message": "SUCCESS",
            "responseAt": "2024-09-04 12:12:12"
        }
        """
                    )))
    ResponseEntity<ResponseDTO<OrderDetailResponse>> getOrder(UUID orderId,
                                                              @Parameter(hidden = true)Passport passport);



    ResponseEntity<ResponseDTO<CreateOrderResponse>> createOrder(CreateOrderRequest createOrderRequestDto);

    ResponseEntity<ResponseDTO<Page<OrderResponse>>> getOrders(Pageable pageable, String keyword, @Parameter(hidden = true) Passport passport);


    ResponseEntity<ResponseDTO<UpdateOrderStatusResponse>> updateOrderStatus(UUID orderId, UpdateOrderStatusRequest updateOrderStatusRequestDto,@Parameter(hidden = true) Passport passport);

    ResponseEntity<ResponseDTO<CancelOrderResponse>> cancelOrder(UUID orderId,@Parameter(hidden = true) Passport passport);

    ResponseEntity<ResponseDTO<Void>> deleteOrder(UUID orderId, @Parameter(hidden = true) Passport passport);

}

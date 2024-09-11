package com.two_ddang.logistics.delivery.presentation.request;

import com.two_ddang.logistics.core.entity.DeliveryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Schema(title = "배달 생성 요청 DTO")
@AllArgsConstructor
public class DeliveryCreateRequest {

    @Schema(title = "주문 ID")
    private UUID orderId;
    @Schema(title = "출발 허브 ID")
    private UUID departHubId;
    @Schema(title = "도착 허브 ID")
    private UUID arrivedHubId;
    @Schema(title = "도착지")
    private String deliveryAddress;
    @Schema(title = "수령 업체 ID")
    private UUID receiveCompanyId;
    @Schema(title = "배송 상태")
    private DeliveryStatus deliveryStatus;

}

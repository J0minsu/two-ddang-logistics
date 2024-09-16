package com.two_ddang.logistics.delivery.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(title = "운송 경로 도착 요청 DTO")
@AllArgsConstructor
public class TransitRouteArriveRequest {

    private int actualDistance;
    private int actualTime;

}

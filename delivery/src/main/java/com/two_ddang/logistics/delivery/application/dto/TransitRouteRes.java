package com.two_ddang.logistics.delivery.application.dto;

import com.two_ddang.logistics.core.entity.TransitStatus;
import com.two_ddang.logistics.delivery.domain.vo.TransitRouteVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(title = "운송 경로 응답 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransitRouteRes {

    private int sequence;
    private UUID transitRouteId;
    private UUID deliveryId;
    private UUID departmentHubId;
    private UUID arriveHunId;
    private String route;
    private TransitStatus transitStatus;
    private LocalDateTime arriveTime;

    public static TransitRouteRes fromEntity(TransitRouteVO route) {

        return new TransitRouteRes(
                route.getSequence(),
                route.getId(), route.getDeliveryId(),
                route.getDepartmentHubId(), route.getArriveHubId(),
                route.getRoute(), route.getTransitStatus(), route.getArriveAt()
        );
    }

}

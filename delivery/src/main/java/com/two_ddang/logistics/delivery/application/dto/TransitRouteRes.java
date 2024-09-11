package com.two_ddang.logistics.delivery.application.dto;

import com.two_ddang.logistics.core.entity.TransitStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(title = " DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransitRouteRes {

    private UUID transitRouteId;
    private UUID deliveryId;
    private UUID orderId;
    private UUID departmentHubId;
    private UUID arriveHunId;
    private String route;
    private TransitStatus transitStatus;
    private LocalDateTime arriveTime;


    public static TransitRouteRes example() {

        UUID uuid = UUID.randomUUID();

        return new TransitRouteRes(uuid, uuid, uuid, uuid, uuid, "고속도로", TransitStatus.WAIT, LocalDateTime.now());
    }


}

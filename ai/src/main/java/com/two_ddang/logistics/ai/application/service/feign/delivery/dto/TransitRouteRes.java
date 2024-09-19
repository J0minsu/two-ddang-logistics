package com.two_ddang.logistics.ai.application.service.feign.delivery.dto;

import com.two_ddang.logistics.core.entity.TransitStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
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

}

package com.two_ddang.logistics.delivery.application.service.feign.ai.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransitRouteResponse {

    private UUID departmentHubId;
    private String departureAddress;
    private UUID arriveHubId;
    private String arriveAddress;
    private int estimateTime;
    private int estimateDistance;
    private String route;

}
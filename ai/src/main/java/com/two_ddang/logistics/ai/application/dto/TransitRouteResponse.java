package com.two_ddang.logistics.ai.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransitRouteResponse {

    private int sequence;
    private UUID departmentHubId;
    private String departureAddress;
    private UUID arriveHubId;
    private String arriveAddress;
    private int estimateTime;
    private int estimateDistance;
    private String route;
}

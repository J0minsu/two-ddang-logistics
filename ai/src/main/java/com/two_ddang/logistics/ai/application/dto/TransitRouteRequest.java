package com.two_ddang.logistics.ai.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransitRouteRequest {

    private UUID departmentHubId;
    private String departureAddress;
    private UUID arriveHubId;
    private String arriveAddress;

}

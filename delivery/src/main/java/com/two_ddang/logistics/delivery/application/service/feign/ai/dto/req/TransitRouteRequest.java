package com.two_ddang.logistics.delivery.application.service.feign.ai.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransitRouteRequest {

    private UUID departmentHubId;
    private String departureAddress;
    private UUID arriveHubId;
    private String arriveAddress;

}
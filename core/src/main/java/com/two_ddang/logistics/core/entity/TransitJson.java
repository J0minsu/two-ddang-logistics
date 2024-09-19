package com.two_ddang.logistics.core.entity;

import java.util.UUID;

public record TransitJson(
        Integer sequence,
        UUID departmentHubId,
        UUID arriveHubId,
        Integer totalEstimateDistance

) {

}

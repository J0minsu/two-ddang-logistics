package com.two_ddang.logistics.delivery.application.dto;


import com.two_ddang.logistics.delivery.domain.model.enums.TransitStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@Schema(title = "운송 응답 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransitRes {

    private UUID transitId;
    private UUID transitAgentId;
    private UUID departmentHubId;
    private UUID arriveHubId;
    private TransitStatus transitStatus;
    private int totalCount;
    private int stayCount;
    private Map<Integer, TransitRouteRes> route;

    public static TransitRes fromVO(
            UUID transitId,
            UUID transitAgentId,
            UUID departmentHubId,
            UUID arriveHubId,
            TransitStatus transitStatus,
            int totalCount,
            int stayCount,
            Map<Integer, TransitRouteRes> route) {
        return new TransitRes(transitId, transitAgentId, departmentHubId, arriveHubId, transitStatus, totalCount, stayCount, route);
    };

    public static TransitRes example(int size) {

        Map<Integer, TransitRouteRes> map = IntStream.range(1, size + 1)
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        i -> TransitRouteRes.example()
                ));

        UUID uuid = UUID.randomUUID();

        return new TransitRes(uuid, uuid, uuid, uuid,
                TransitStatus.WAIT, size, 0, map);
    }

}

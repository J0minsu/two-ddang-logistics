package com.two_ddang.logistics.hub.application.dto;

import com.two_ddang.logistics.hub.domain.vo.HubRouteVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.util.RouteMatcher;

import java.util.UUID;

@Data
@Schema(title = "허브 간 이동 경로 응답 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HubRouteRes {

    private UUID departmentHubId;
    private UUID arriveHubId;
    private int takeTime;
    private String route;

    public static HubRouteRes fromVO(HubRouteVO hubRoute) {

        return new HubRouteRes(hubRoute.getDepartmentHub().getId(), hubRoute.getArriveHub().getId(), hubRoute.getTakeTime(), hubRoute.getRoute());

    }

    public static HubRouteRes example() {

        UUID uuid = UUID.randomUUID();

        return new HubRouteRes(uuid, uuid, 127, "경부고속도로");
    }

}

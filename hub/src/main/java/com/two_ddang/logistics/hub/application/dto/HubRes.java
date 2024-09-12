package com.two_ddang.logistics.hub.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.two_ddang.logistics.hub.domain.vo.HubVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(title = "허브 응답 DTO")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HubRes {


    private UUID hubId;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private LocalDateTime createdAt;


    public static HubRes fromVO(HubVO hub) {
        return new HubRes(hub.getId(), hub.getName(), hub.getAddress(), hub.getLatitude(), hub.getLongitude(), hub.getCreatedAt());

    }

    public static HubRes example() {

        UUID uuid = UUID.randomUUID();

        return new HubRes(
                uuid, "인천 허브 1센터", "경기도 성남시 수정구 중원동 144-4",
                "127.00001", "57.1245", LocalDateTime.now()
        );

    }

}

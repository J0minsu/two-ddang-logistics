package com.two_ddang.logistics.delivery.application.service.feign.hub.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(title = "허브 응답 DTO")
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class HubRes {


    private UUID hubId;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private LocalDateTime createdAt;

}
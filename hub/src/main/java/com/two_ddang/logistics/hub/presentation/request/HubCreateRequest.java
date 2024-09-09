package com.two_ddang.logistics.hub.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@Schema(title = "허브 생성 요청 DTO")
@AllArgsConstructor
public class HubCreateRequest {

    private String name;
    private String address;
    private String latitude;
    private String longitude;

}

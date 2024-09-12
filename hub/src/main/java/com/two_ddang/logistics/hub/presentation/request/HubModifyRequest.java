package com.two_ddang.logistics.hub.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(title = "허브 수정 요청 DTO")
@AllArgsConstructor
public class HubModifyRequest {

    private String name;

}

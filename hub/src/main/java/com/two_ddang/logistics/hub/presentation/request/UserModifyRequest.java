package com.two_ddang.logistics.hub.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(title = "사용자 수정 요청 DTO")
@AllArgsConstructor
public class UserModifyRequest {

    private String contact;
    private String email;

}

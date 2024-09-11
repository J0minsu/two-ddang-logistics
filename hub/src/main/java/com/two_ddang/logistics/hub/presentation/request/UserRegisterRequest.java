package com.two_ddang.logistics.hub.presentation.request;

import com.two_ddang.logistics.core.entity.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Schema(title = "회원가입 DTO")
@AllArgsConstructor
public class UserRegisterRequest {

    private String username;
    private String password;
    private String name;
    private String contact;
    private String email;
    private UserType role;
    private UUID slackId;


}

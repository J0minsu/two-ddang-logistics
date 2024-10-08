package com.two_ddang.logistics.hub.presentation.request;

import com.two_ddang.logistics.core.entity.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Schema(title = "회원가입 DTO")
@AllArgsConstructor
public class UserRegisterRequest {

    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-z0-9]+$")
    private String username;
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[\\W_]).+$")
    private String password;
    private String name;
    private String contact;
    private String email;
    private UserType role;
    private UUID slackId;


}

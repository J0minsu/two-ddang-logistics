package com.two_ddang.logistics.auth.user.dto;

import com.two_ddang.logistics.core.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
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

package com.two_ddang.logistics.core.util;

import com.two_ddang.logistics.core.entity.UserType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PassPort {

    private String id;

    private Integer userId;

    private String userName;

    private LocalDateTime expirationTime;
    private UserType userType;

    public PassPort(Integer userId, String userName, LocalDateTime expirationTime,
                    UserType userType) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.userName = userName;
        this.expirationTime = expirationTime;
        this.userType=userType;
    }

}

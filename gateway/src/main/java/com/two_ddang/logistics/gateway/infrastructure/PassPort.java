package com.two_ddang.logistics.gateway.infrastructure;

import com.two_ddang.logistics.core.entity.UserType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PassPort {

    private String id;

    private Long userId;

    @Getter
    private String userName;

    @Getter
    private LocalDateTime expirationTime;
    private UserType userType;

    public PassPort(Long userId, String userName, LocalDateTime expirationTime,
                    UserType userType) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.userName = userName;
        this.expirationTime = expirationTime;
        this.userType=userType;
    }

}

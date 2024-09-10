package com.two_ddang.logistics.gateway.infrastructure;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PassPort {

    private String id;

    @Getter
    private String userName;

    @Getter
    private LocalDateTime expirationTime;

    public PassPort(String userName, LocalDateTime expirationTime) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
        this.expirationTime = expirationTime;
    }

}

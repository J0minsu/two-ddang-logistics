package com.two_ddang.logistics.core.entity;

import lombok.Getter;

@Getter
public enum UserType {

    HUB("ROLE_HUB"),
    DELIVERY("ROLE_DELIVERY"),
    COMPANY("ROLE_COMPANY"),
    MASTER("ROLE_MASTER"),
    ;

    private final String userType;

    UserType(String userType) {
        this.userType = userType;
    }

}

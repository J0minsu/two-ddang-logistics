package com.two_ddang.logistics.core.util;

import com.two_ddang.logistics.core.entity.UserType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Passport {

    private Integer userId;

    private String email;

    private String userName;

    private Date expirationTime;
    private UserType userType;

    public Passport(Integer userId, String userName, String email, Date expirationTime,
                    UserType userType) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.expirationTime = expirationTime;
        this.userType=userType;
    }

}

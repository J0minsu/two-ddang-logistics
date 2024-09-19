package com.two_ddang.logistics.core.util;

import com.two_ddang.logistics.core.entity.UserType;
import com.two_ddang.logistics.core.exception.ApplicationException;
import com.two_ddang.logistics.core.exception.ErrorCode;
import com.two_ddang.logistics.core.exception.PermissionDeniedApplicationException;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class ValidationUtils {

    /**
     *
     * @param roles : 호출 가능한 사용자 타입
     * @param userType : 호출자 사용자 타입
     */
    public static void validateRole(Stream<UserType> roles, UserType userType) {
        if (!roles.anyMatch(i -> i == userType)) {
            throw new PermissionDeniedApplicationException();
        }
    }

}

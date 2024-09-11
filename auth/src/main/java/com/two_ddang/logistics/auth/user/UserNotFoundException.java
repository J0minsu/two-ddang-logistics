package com.two_ddang.logistics.auth.user;

import com.two_ddang.logistics.core.exception.ApplicationException;
import com.two_ddang.logistics.core.exception.ErrorCode;

public class UserNotFoundException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.USER_NOT_FOUND;

    protected UserNotFoundException() {
        super(ERROR_CODE);
    }

}

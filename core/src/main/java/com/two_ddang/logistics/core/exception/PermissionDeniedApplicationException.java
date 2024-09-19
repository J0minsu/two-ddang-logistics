package com.two_ddang.logistics.core.exception;

import lombok.Getter;

@Getter
public class PermissionDeniedApplicationException extends ApplicationException {

    public PermissionDeniedApplicationException() {
        super(ErrorCode.CAN_NOT_ACTION_ROLE);
    }

}

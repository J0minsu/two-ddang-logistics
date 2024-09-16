package com.two_ddang.logistics.delivery.infrastructrure.exception;

import com.two_ddang.logistics.core.exception.ApplicationException;
import com.two_ddang.logistics.core.exception.ErrorCode;
import lombok.Getter;

@Getter
public class PermissionDeniedApplicationException extends ApplicationException {

    public PermissionDeniedApplicationException() {
        super(ErrorCode.CAN_NOT_ACTION_ROLE);
    }

}

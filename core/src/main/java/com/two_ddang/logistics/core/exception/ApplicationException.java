package com.two_ddang.logistics.core.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private final ErrorCode errorCode;

    /**
     * 테스트 시 불필요한 message 작성을 피하기 위해서 만든 API 입니다.
     * 되도록 {@link #ApplicationException(ErrorCode errorCode, String message)} 을 사용하는 것을 추천합니다
     * @param errorCode HttpResponse 에 에러 관련 메시지를 전송하기 위해 공통적으로 설정한 ErrorCode 입니다.
     */
    protected ApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @param errorCode HttpResponse 에 에러 관련 메시지를 전송하기 위해 공통적으로 설정한 ErrorCode 입니다.
     * @param message 실제 로그에 적재할 Exception Message 입니다. 최대한 자세하게 작성하시면 디버깅에 편합니다.
     */
    protected ApplicationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        if (super.getMessage() == null) {
            return errorCode.getMessage();
        }

        return super.getMessage();
    }
}

package com.two_ddang.logistics.hub.holder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Schema(title = "공통 응답 객체")
public class Result<T> {

    @Schema(hidden = true)
    private static final String SUCCESS = "SUCCESS";
    @Schema(hidden = true)
    private static final String FAILURE = "FAILURE";

    @Schema(description = "응답 결과", example = "SUCCESS")
    private String result;
    @Schema(description = "응답 데이터")
    private T data;
    @Schema(description = "(에러)메세지", example = "일치하는 요소가 없습니다.")
    private String message;

    private LocalDateTime responseAt = LocalDateTime.now();

    private Result(String result, T data, String message) {
        this.result = result;
        this.data = data;
        this.message = message;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS, data, null);
    }

    public static <T> Result<T> failure(String message) {
        return new Result<>(FAILURE, null, message);
    }
}

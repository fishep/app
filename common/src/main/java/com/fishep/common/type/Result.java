package com.fishep.common.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result(StatusCode code, String message, T data) {
        this.code = code.getCode();
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        return new Result(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), null);
    }

    public static <T> Result success(T data) {
        return new Result(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), data);
    }

    public static <T> Result success(String message, T data) {
        return new Result(StatusCode.SUCCESS.getCode(), message, data);
    }

    public static Result failure() {
        return new Result(StatusCode.FAILURE.getCode(), StatusCode.FAILURE.getMessage(), null);
    }

    public static <T> Result failure(T data) {
        return new Result(StatusCode.FAILURE.getCode(), StatusCode.FAILURE.getMessage(), data);
    }

    public static <T> Result failure(String message, T data) {
        return new Result(StatusCode.FAILURE.getCode(), message, data);
    }
}

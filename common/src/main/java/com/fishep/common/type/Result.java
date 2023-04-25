package com.fishep.common.type;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public Result(StatusCode code, String message, Object data) {
        this.code = code.getCode();
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        return new Result(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), null);
    }

    public static Result success(Object data) {
        return new Result(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), data);
    }

    public static Result success(String message, Object data) {
        return new Result(StatusCode.SUCCESS.getCode(), message, data);
    }

    public static Result failure() {
        return new Result(StatusCode.FAILURE.getCode(), StatusCode.FAILURE.getMessage(), null);
    }

    public static Result failure(Object data) {
        return new Result(StatusCode.FAILURE.getCode(), StatusCode.FAILURE.getMessage(), data);
    }

    public static Result failure(String message, Object data) {
        return new Result(StatusCode.FAILURE.getCode(), message, data);
    }
}

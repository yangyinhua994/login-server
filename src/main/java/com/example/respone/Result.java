package com.example.respone;

import com.example.msg.MsgEnum;
import lombok.Data;

@Data
public class Result<T> {

    private int code = SUCCESS_CODE;
    private String message = SUCCESS_MESSAGE;
    private T data;

    private static final int FAIL_CODE = 500;
    private static final int SUCCESS_CODE = 200;

    private static final String SUCCESS_MESSAGE = "success";
    private static final String FAIL_MESSAGES = "fail";

    public Result(boolean isSuccess) {
        if (!isSuccess){
            this.code = FAIL_CODE;
            this.message = FAIL_MESSAGES;
        }
    }

    public Result(boolean isSuccess, int code) {
        if (!isSuccess) {
            this.message = FAIL_MESSAGES;
        }
        this.code = code;
    }

    public Result(boolean isSuccess, String message) {
        if (!isSuccess) {
            this.code = FAIL_CODE;
        }
        this.message = message;
    }

    public Result(boolean isSuccess, T data) {
        if (!isSuccess) {
            this.code = FAIL_CODE;
            this.message = FAIL_MESSAGES;
        }
        this.data = data;
    }

    public Result(boolean isSuccess, String message, T data) {
        if (!isSuccess) {
            this.code = FAIL_CODE;
        }
        this.message = message;
        this.data = data;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static <T> Result<T> success() {
        return new Result<>(true);
    }

    public static <T> Result<T> fail() {
        return new Result<>(false);
    }

    public static <T> Result<T> success(int code) {
        return new Result<>(true, code);
    }

    public static <T> Result<T> fail(int code) {
        return new Result<>(false, code);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(true, message);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(false, message);
    }

    public static <T> Result<T> fail(MsgEnum msgEnum) {
        return new Result<>( msgEnum.getCode(), msgEnum.getMsg(), null);
    }

    public static <T> Result<T> fail(MsgEnum msgEnum, T data) {
        return new Result<>( msgEnum.getCode(), msgEnum.getMsg(), data);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, data);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<>(false, data);
    }

    public static <T> Result<T> success(String message,T data) {
        return new Result<>(true, message, data);
    }

    public static <T> Result<T> fail(String message,T data) {
        return new Result<>(false, message, data);
    }

    public static <T> Result<T> success(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> fail(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

}





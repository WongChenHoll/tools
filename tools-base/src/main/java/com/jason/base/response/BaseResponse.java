package com.jason.base.response;

import com.jason.base.enums.ResponseEnum;

import java.io.Serializable;

/**
 * @author WangChenHol
 * @date 2021-9-26 12:56
 **/
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = -8627165253899122689L;
    private String code; // 响应码
    private String message; // 响应信息
    private T data; // 响应数据

    private BaseResponse() {
    }

    private BaseResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> error(String code, String message, T data) {
        return new BaseResponse<T>(code, message, data);
    }

    public static <T> BaseResponse<T> error(String code, String message) {
        return new BaseResponse<T>(code, message, null);
    }

    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<T>(ResponseEnum.ERROR.getCode(), message, null);
    }

    public static <T> BaseResponse<T> error(T data) {
        return new BaseResponse<T>(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getMessage(), data);
    }

    public static <T> BaseResponse<T> error() {
        return new BaseResponse<T>(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getMessage(), null);
    }

    public static <T> BaseResponse<T> error(ResponseEnum responseEnum, T data) {
        return new BaseResponse<T>(responseEnum.getCode(), responseEnum.getMessage(), data);
    }

    public static <T> BaseResponse<T> error(ResponseEnum responseEnum) {
        return new BaseResponse<T>(responseEnum.getCode(), responseEnum.getMessage(), null);
    }

    public static <T> BaseResponse<T> success(String code, String message, T data) {
        return new BaseResponse<T>(code, message, data);
    }

    public static <T> BaseResponse<T> success(String code, String message) {
        return new BaseResponse<T>(code, message, null);
    }

    public static <T> BaseResponse<T> success(String message) {
        return new BaseResponse<T>(ResponseEnum.SUCCESS.getCode(), message, null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), data);
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<T>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), null);
    }

    public static <T> BaseResponse<T> success(ResponseEnum responseEnum, T data) {
        return new BaseResponse<T>(responseEnum.getCode(), responseEnum.getMessage(), data);
    }

    public static <T> BaseResponse<T> success(ResponseEnum responseEnum) {
        return new BaseResponse<T>(responseEnum.getCode(), responseEnum.getMessage(), null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

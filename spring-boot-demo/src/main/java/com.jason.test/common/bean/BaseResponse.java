package com.jason.test.common.bean;

import com.jason.test.common.constans.BaseConstans;

import java.util.Date;

/**
 * 相应类
 *
 * @author ChenHol.Wong
 * @create 2020/7/27 21:26
 */
public class BaseResponse<T> {
    private Date responseTime;
    private String code;
    private String message;
    private T data;

    public BaseResponse(Date responseTime, String code, String message, T data) {
        this.responseTime = responseTime;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(String message, String code, T data) {
        this.responseTime = new Date();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(String message, T data) {
        this.responseTime = new Date();
        this.message = message;
        this.data = data;
    }

    public BaseResponse(String message) {
        this.responseTime = new Date();
        this.message = message;
    }

    public BaseResponse(T data) {
        this.responseTime = new Date();
        this.message = "";
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        BaseResponse<T> response = new BaseResponse<>(message, data);
        response.setCode(BaseConstans.SUCCESS_CODE);
        return response;
    }

    public static <T> BaseResponse<T> success(String message) {
        return success(message, null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return success("", data);
    }

    public static <T> BaseResponse<T> error(String message, T data) {
        BaseResponse<T> response = new BaseResponse<>(message, data);
        response.setCode(BaseConstans.ERROR_CODE);
        return response;
    }

    public static <T> BaseResponse<T> error(String message) {
        return error(message, null);
    }

    public static <T> BaseResponse<T> error(T data) {
        return error("", data);
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
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

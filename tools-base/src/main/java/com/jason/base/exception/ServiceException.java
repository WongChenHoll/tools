package com.jason.base.exception;

import com.jason.base.enums.ResponseEnum;

/**
 * @author WangChenHol
 * @date 2021-9-26 12:41
 **/
public class ServiceException extends Exception {

    private final String code;

    private ServiceException() {
        super();
        this.code = null;
    }

    private ServiceException(String message) {
        super(message);
        this.code = null;
    }

    private ServiceException(String message, Throwable throwable) {
        super(message, throwable);
        this.code = null;
    }

    private ServiceException(String message, String code, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    private ServiceException(String message, String code) {
        super(message);
        this.code = code;
    }

    public static ServiceException baseException(String message, ResponseEnum responseEnum) {
        return new ServiceException(message, responseEnum.getCode());
    }

    public static ServiceException errorException(String message, ResponseEnum responseEnum, Throwable throwable) {
        return new ServiceException(message, responseEnum.getCode(), throwable);
    }

    public static ServiceException paramsException(String message) {
        return baseException(message, ResponseEnum.PARAM_VALID_ERROR);
    }

    public static ServiceException serverException(String message) {
        return baseException(message, ResponseEnum.SERVER_ERROR);
    }
}

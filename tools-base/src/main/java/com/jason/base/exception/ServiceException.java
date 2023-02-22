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

    private ServiceException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
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

    public static ServiceException connectionException(String message, Throwable throwable) {
        return errorException(message, ResponseEnum.CONNECTION_FAIL, throwable);
    }

    public static ServiceException serverException(String message, Throwable throwable) {
        return errorException(message, ResponseEnum.SERVER_ERROR, throwable);
    }

    public static ServiceException paramsException(String message) {
        return baseException(message, ResponseEnum.VALID_PARAM_ERROR);
    }

    public static ServiceException validException(String message) {
        return baseException(message, ResponseEnum.VALID_ERROR);
    }

    public static ServiceException pathException(String message) {
        return baseException(message, ResponseEnum.PATH_ERROR);
    }

    public static ServiceException serverException(String message) {
        return baseException(message, ResponseEnum.SERVER_ERROR);
    }

    public static ServiceException fileException(String message) {
        return baseException(message, ResponseEnum.FILE_ERROR);
    }

    public static ServiceException fileNotExistException(String message) {
        return baseException(message, ResponseEnum.FILE_NOT_EXIST);
    }

    public static ServiceException fileNotExistException() {
        return new ServiceException(ResponseEnum.FILE_NOT_EXIST);
    }
}

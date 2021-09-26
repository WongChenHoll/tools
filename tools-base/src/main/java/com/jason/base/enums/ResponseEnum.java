package com.jason.base.enums;

/**
 * @author WangChenHol
 * @date 2021-9-26 13:05
 **/
public enum ResponseEnum {
    SUCCESS("20000", "请求成功"),
    ERROR("40000", "请求失败"),
    PARAM_VALID_ERROR("40100", "参数校验失败"),
    SERVER_ERROR("50000", "服务器异常");

    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResponseEnum(String code, String message) {


        this.code = code;
        this.message = message;
    }
}

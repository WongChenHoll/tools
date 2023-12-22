package com.jason.base.enums;

/**
 * @author WangChenHol
 * @date 2021-9-26 13:05
 **/
public enum ResponseEnum {
    SUCCESS("20000", "请求成功"),
    ERROR("40000", "请求失败"),

    VALID_PARAM_ERROR("40100", "参数校验失败"),
    VALID_ERROR("40101", "内容校验失败"),

    FILE_ERROR("40200", "文件错误"),
    FILE_NOT_EXIST("40201", "文件不存在"),
    FILE_PATH_ERROR("40202", "文件路径错误"),
    PATH_ERROR("40210", "路径错误"),

    USER_AUTH_ERROR("40500","认证失败，请联系管理员"),
    JURISDICTION_ERROR("40501","您无权操作，请联系管理员添加"),

    CONNECTION_FAIL("40900", "获取连接失败"),

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

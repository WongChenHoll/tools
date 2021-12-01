package com.jason.test.common.constans;

/**
 * @author ChenHol.Wong
 * @create 2020/7/28 20:40
 */
public class BaseConstans {
    private BaseConstans() {
    }

    public static final String SUCCESS_CODE = "0000";

    public static final String ERROR_CODE = "9999";

    public static final String OPER_SUCCESS_MES = "操作成功";
    public static final String OPER_ERROR_MES = "操作失败";

    public static final String LOGIN_SESSION_ATTRIBUTE = "login-session";
    public static final String REDIS_KEY_LOGIN_INFO = "test:spring-boot:session:";


}

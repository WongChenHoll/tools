package com.jason.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WangChenHol
 * @date 2021-11-16 16:20
 **/
public class DateUtil {

    /**
     * 获取当前时间的字符串类型值
     */
    public static String currDateStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}

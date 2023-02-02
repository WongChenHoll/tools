package com.jason.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * @author WongChenHoll
 * @description 字符串的操作
 * @date 2023-2-1 星期三 16:14
 **/
public class StrUtil {
    private StrUtil() {
    }

    private static final Logger logger = LoggerFactory.getLogger(StrUtil.class);

    /**
     * 取出字符串中的空格、回车、制表符、换行符、换页符
     *
     * @param str 目标字符串
     * @return 结果
     */
    public static String replaceSpace(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        Pattern compile = Pattern.compile("[\t\r\n\f]");
        return compile.matcher(str).replaceAll("");
    }
}

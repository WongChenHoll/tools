package com.jason.base.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author WongChenHoll
 * @description 字符串的操作
 * @date 2023-2-1 星期三 16:14
 **/
public class StrUtil {
    private StrUtil() {
    }

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

    public static List<String> split(String str) {
        if (StringUtils.isBlank(str)) {
            return new ArrayList<>();
        }
        if (str.contains(",")) {
            return new ArrayList<>(Arrays.asList(str.split(",")));
        } else {
            return new ArrayList<>(Collections.singletonList(str));
        }
    }
}

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

    /**
     * 将数据库中的列名转换成实体类中的字段名（去除下划线，首字母大写）
     *
     * @param column 数据库中的列名
     * @return 实体类中的字段名
     */
    public static String toFieldName(String column) {
        column = column.toLowerCase();
        if (column.contains("_")) {
            StringBuilder c = new StringBuilder(column.substring(0, column.indexOf("_")));
            String[] split = column.split("_");
            for (int i = 1; i < split.length; i++) {
                c.append(upperFirst(split[i]));
            }
            return c.toString();
        } else {
            return column.toLowerCase();
        }
    }

    /**
     * 将数据库表名转换成类名
     *
     * @param tableName 表名
     * @return 类名
     */
    public static String toClassName(String tableName) {
        tableName = tableName.toLowerCase();
        if (tableName.contains("_")) {
            StringBuilder t = new StringBuilder();
            for (String s : tableName.split("_")) {
                t.append(upperFirst(s));
            }
            return t.toString();
        } else {
            return upperFirst(tableName);
        }
    }

    /**
     * 将字符串的第一个字符转换成大写
     *
     * @param str 被操作字符串
     * @return 转换后的字符串
     */
    public static String upperFirst(CharSequence str) {
        if (null == str) {
            return null;
        }
        if (str.length() > 0) {
            char firstChar = str.charAt(0);
            if (Character.isLowerCase(firstChar)) {
                return Character.toUpperCase(firstChar) + str.subSequence(1, str.length()).toString();
            }
        }
        return str.toString();
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

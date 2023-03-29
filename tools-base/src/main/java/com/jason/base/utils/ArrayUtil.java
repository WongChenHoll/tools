package com.jason.base.utils;

/**
 * @author WongChenHoll
 * @description 数组工具类
 * @date 2023-3-29 星期三 10:47
 **/
public class ArrayUtil {
    private ArrayUtil() {
    }

    public static int[] copy(int[] array) {
        return copy(array);
    }

    public static String[] copy(String[] array) {
        return copy(array);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] copy(T[] array) {
        if (array == null) {
            return (T[]) new Object[]{};
        }
        Object[] copy = new Object[array.length];
        if (array.length == 0) {
            return (T[]) copy;
        }
        System.arraycopy(array, 0, copy, 0, array.length);
        return (T[]) copy;
    }
}

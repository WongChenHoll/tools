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
        if (array == null) {
            return new int[]{};
        }
        int[] copy = new int[array.length];
        if (array.length == 0) {
            return copy;
        }
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }

    public static String[] copy(String[] array) {
        if (array == null) {
            return new String[]{};
        }
        String[] copy = new String[array.length];
        if (array.length == 0) {
            return copy;
        }
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }
}

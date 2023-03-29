package com.jason.base.utils;

/**
 * 输出数据工具
 *
 * @author WangChenHol
 * @date 2021-7-25 10:25
 **/
public class PrintUtil {

    public static void printIntArray(int[] array) {
        System.out.print("元素个数：" + array.length + "， 元素内容：{");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                System.out.print(array[i]);
            } else {
                System.out.print(array[i] + ", ");
            }
        }
        System.out.print("}");
        System.out.println(" ");
    }
}

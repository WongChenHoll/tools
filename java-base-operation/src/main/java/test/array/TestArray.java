package test.array;

import java.util.Arrays;

/**
 * 将数组中的最大值放在后面，最小值放在前面
 *
 * @author ChenHol.Wong
 * @create 2020/2/5 10:59
 */
public class TestArray {
    public static void main(String[] args) {
        int[] arr = {3, 9, 6, 0, 4, 2};
        int[] array = exchangeArray(arr);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static int[] exchangeArray(int[] arr) {
        int length = arr.length;
        int first = arr[0];
        int last = arr[length - 1];

        int[] copy = Arrays.copyOf(arr, length);// 复制数组

        Arrays.sort(arr);//排序
        int min = arr[0];
        int max = arr[length - 1];

        int minIndex = findIndex(copy, min);
        int maxIndex = findIndex(copy, max);

        // 最小值和第一个交换
        copy[0] = min;
        copy[minIndex] = first;
        // 最大值和最后一个交换
        copy[length - 1] = max;
        copy[maxIndex] = last;

        return copy;
    }

    private static int findIndex(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}

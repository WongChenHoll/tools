package test.function.operator;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

/**
 * @author ChenHol.Wong
 * @create 2020/12/13 12:23
 */
public class TestBinaryOperator {
    public static void main(String[] args) {
        // 将两个参数相加
        test(100, 200, (a, b) -> a + b);
        // 先让两个参数相加，然后再乘以2
        testAndThen(100, 200, (a, b) -> a + b, val -> val * 2);
        // 找出两个参数中小的那个
        testMinBy(100, 200, Integer::compareTo);
        // 找出两个参数中大的值
        testMayBy(100, 200, (x, y) -> (x < y) ? -1 : ((x == y) ? 0 : 1));
    }

    public static void test(int val1, int val2, BinaryOperator<Integer> operator) {
        Integer apply = operator.apply(val1, val2);
        System.out.println(apply);
    }

    public static void testAndThen(int val1, int val2, BinaryOperator<Integer> operator, Function<Integer, Integer> after) {
        BiFunction<Integer, Integer, Integer> andThen = operator.andThen(after);
        Integer apply = andThen.apply(val1, val2);
        System.out.println(apply);
    }

    public static void testMinBy(int val1, int val2, Comparator<Integer> comparator) {
        BinaryOperator<Integer> minBy = BinaryOperator.minBy(comparator);
        Object apply = minBy.apply(val1, val2);
        System.out.println(apply);
    }

    public static void testMayBy(int val1, int val2, Comparator<Integer> comparato) {
        BinaryOperator<Integer> maxBy = BinaryOperator.maxBy(comparato);
        Integer apply = maxBy.apply(val1, val2);
        System.out.println(apply);
    }
}

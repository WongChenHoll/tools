package test.function.operator;

import java.util.function.IntUnaryOperator;

/**
 * @author ChenHol.Wong
 * @create 2020/12/17 16:33
 */
public class TestIntUnaryOperator {
    public static void main(String[] args) {
        testApply(100, val -> val * 2 - 1);
        testAndThen(100, val -> val * 2, a -> a + 2);
        testCompose(100, a -> a - 10, val -> val * 2);

    }

    public static void testApply(int val, IntUnaryOperator operator) {
        int apply = operator.applyAsInt(val);
        System.out.println(apply);
    }

    public static void testAndThen(int val, IntUnaryOperator operator, IntUnaryOperator after) {
        IntUnaryOperator andThen = operator.andThen(after);
        int apply = andThen.applyAsInt(val);
        System.out.println(apply);
    }

    public static void testCompose(int val, IntUnaryOperator before, IntUnaryOperator operator) {
        IntUnaryOperator compose = operator.compose(before);
        int apply = compose.applyAsInt(val);
        System.out.println(apply);
    }
}

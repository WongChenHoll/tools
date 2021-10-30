package test.function.operator;

import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

/**
 * @author ChenHol.Wong
 * @create 2020/12/17 17:44
 */
public class TestIntBinaryOperator {

    public static void main(String[] args) {
        test(100, 200, (x, y) -> x * y);

    }

    public static void test(int val1, int val2, IntBinaryOperator operator) {
        int apply = operator.applyAsInt(val1, val2);
        System.out.println(apply);
    }
}

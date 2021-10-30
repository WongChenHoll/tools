package test.function.function;

import java.util.function.IntToLongFunction;

/**
 * @author ChenHol.Wong
 * @create 2020/12/12 15:18
 */
public class TestIntToLongFunction {

    public static void main(String[] args) {
        test(100, val -> val * 2);
    }

    public static void test(int val, IntToLongFunction function) {
        long apply = function.applyAsLong(val);
        System.out.println(apply);
    }
}

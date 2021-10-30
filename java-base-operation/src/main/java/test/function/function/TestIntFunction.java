package test.function.function;

import java.util.function.IntFunction;
import java.util.function.LongFunction;

/**
 * @author ChenHol.Wong
 * @create 2020/12/12 15:10
 */
public class TestIntFunction {

    public static void main(String[] args) {
        test1(10, val -> val * 5);
        test2(10, val -> val + "aaa");
    }

    public static void test1(int val, IntFunction<Integer> function) {
        Integer apply = function.apply(val);
        System.out.println(apply);
    }

    public static void test2(int val, IntFunction<String> function) {
        String apply = function.apply(val);
        System.out.println(apply);
    }
}

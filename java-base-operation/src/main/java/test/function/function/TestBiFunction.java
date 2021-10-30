package test.function.function;

import test.function.model.User;

import java.util.Collections;
import java.util.function.BiFunction;

/**
 * @author ChenHol.Wong
 * @create 2020/12/6 13:16
 */
public class TestBiFunction {

    public static void main(String[] args) {
        test("abc", "123", (str1, str2) -> (str1 + str2).length());

        test(new User("张三", 20, "北京"), (str1, str2) -> str1.length() + str2.length());

    }

    public static void test(String str1, String str2, BiFunction<String, String, Integer> function) {
        Integer apply = function.apply(str1, str2);
        System.out.println(apply);
    }

    public static void test(User user, BiFunction<String, String, Integer> function) {
        Integer apply = function.apply(user.getName(), user.getAddress());
        System.out.println(apply);
    }
}

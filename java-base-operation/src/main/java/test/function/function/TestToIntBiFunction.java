package test.function.function;

import test.function.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToIntBiFunction;

/**
 * @author ChenHol.Wong
 * @create 2020/12/12 15:35
 */
public class TestToIntBiFunction {
    public static void main(String[] args) {
        test("123", 10, (str, val) -> Integer.valueOf(str) * val);

        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        User user = new User("王五", 30, "上海");

        test(list, user, (names, e) -> {
            for (int i = 0; i < names.size(); i++) {
                if (e.getName().equals(names.get(i))) {
                    return i;
                }
            }
            return -1;
        });
    }


    public static void test(String str, int val, ToIntBiFunction<String, Integer> function) {
        int apply = function.applyAsInt(str, val);
        System.out.println(apply);
    }

    public static void test(List<String> list, User user, ToIntBiFunction<List<String>, User> function) {
        int apply = function.applyAsInt(list, user);
        System.out.println(apply);
    }
}

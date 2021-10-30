package test.function.function;

import test.function.model.User;

import java.util.function.ToIntFunction;

/**
 * @author ChenHol.Wong
 * @create 2020/12/12 15:28
 */
public class TestToIntFunction {
    public static void main(String[] args) {
        test("123456789", str -> Integer.valueOf(str));

        User user = new User("张三", 23, "北京");
        test(user, obj -> obj.getAge());
        test(user, User::getAge);
    }

    public static void test(String str, ToIntFunction<String> function) {
        int apply = function.applyAsInt(str);
        System.out.println(apply);
    }

    public static void test(User user, ToIntFunction<User> function) {
        int apply = function.applyAsInt(user);
        System.out.println("年龄：" + apply);
    }
}

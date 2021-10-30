package test.function.operator;

import test.function.model.User;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author ChenHol.Wong
 * @create 2020/12/13 12:27
 */
public class TestUnaryOperator {

    public static void main(String[] args) {
        test(100, val -> val * 5);
        testAndThen1("test", str -> str.replaceAll("t", " operator "), a -> a.toUpperCase());
        testAndThen2("test UnaryOperator of andThen", obj -> obj.toUpperCase(), str -> str.contains("UnaryOperator"));
        testCompose(new User("张三", 33, "北京市朝阳区"), User::getAddress, str -> {
            if (str.contains("北京市")) {
                return str;
            } else if (str.contains("上海市")) {
                return "外地";
            } else {
                return "未知";
            }
        });
    }

    public static void test(int val, UnaryOperator<Integer> operator) {
        Integer apply = operator.apply(val);
        System.out.println(apply);
    }

    public static void testAndThen1(String val, UnaryOperator<String> operator, UnaryOperator<String> after) {
        Function<String, String> andThen = operator.andThen(after);
        String apply = andThen.apply(val);
        System.out.println(apply);
    }

    public static void testAndThen2(String val, UnaryOperator<String> operator, Function<String, Boolean> after) {
        Function<String, Boolean> andThen = operator.andThen(after);
        Boolean apply = andThen.apply(val);
        System.out.println(apply);
    }

    public static void testCompose(User user, Function<User, String> before, UnaryOperator<String> operator) {
        Function<User, String> compose = operator.compose(before);
        String apply = compose.apply(user);
        System.out.println(apply);
    }
}

package test.function.function;

import java.util.function.Function;

/**
 * @author ChenHol.Wong
 * @create 2020/12/5 16:18
 */
public class TestFunction {

    public static void main(String[] args) {
        // 计算字符串的长度
        testApply("test", val -> val.length());
        // 用空格分隔字符串，并计算分割后数组的长度
        testApply("test function demo", val -> val.split(" ").length);

        // 将字符串转为大写并加上“Compose”，然后计算拼接后的字符串的长度
        testCompose("test", str -> str.toUpperCase() + "Compose", str -> str.length());
        // 将Integer类型的123和字符串拼“testCompose”接起来，然后将其转为大写
        testCompose(Integer.valueOf("123"), val -> "testCompose(" + val + ")", str -> str.toUpperCase());

        // 将Integer类型的666和字符串类型的888拼接起来，然后转为Integer类型并乘以2
        testAndThen(Integer.valueOf("666"), val -> String.valueOf(val), str -> Integer.valueOf(str + "888") * 2);
    }

    static void testApply(String str, Function<String, Integer> function) {
        Integer apply = function.apply(str);
        System.out.println(apply);
    }

    public static void testCompose(String str, Function<String, String> before, Function<String, Integer> function) {
        Function<String, Integer> compose = function.compose(before);
        Integer apply = compose.apply(str);
        System.out.println(apply);
    }

    public static void testCompose(Integer val, Function<Integer, String> before, Function<String, String> function) {
        Function<Integer, String> compose = function.compose(before);
        String apply = compose.apply(val);
        System.out.println(apply);
    }

    public static void testAndThen(Integer val, Function<Integer, String> function, Function<String, Integer> after) {
        Function<Integer, Integer> andThen = function.andThen(after);
        Integer apply = andThen.apply(val);
        System.out.println(apply);
    }
}

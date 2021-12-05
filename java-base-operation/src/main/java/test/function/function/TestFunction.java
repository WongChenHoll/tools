package test.function.function;

import test.function.model.Teacher;
import test.function.model.User;

import java.util.function.Function;

/**
 * Function类型函数，表示：代表其参数与返回类型不一致的函数。
 * <pre>
 *
 *     // 该函数的泛型中，T：表示输入的参数类型，R：表示输出的数据类型
 *      public interface Function<T, R> {
 *
 *          // 默认的抽象函数，用于执行逻辑
 *          R apply(T t);
 *
 *          default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
 *              Objects.requireNonNull(before);
 *              return (V v) -> apply(before.apply(v));
 *          }
 *
 *          default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
 *              Objects.requireNonNull(after);
 *              return (T t) -> after.apply(apply(t));
 *          }
 *
 *          static <T> Function<T, T> identity() {
 *              return t -> t;
 *          }
 *      }
 * </pre>
 *
 * @author ChenHol.Wong
 * @create 2020/12/5 16:18
 */
public class TestFunction {

    public static void main(String[] args) {
        // 计算字符串的长度，也可以使用Lambda表达式：testApply("test", String::length);
        testApply("test", val -> val.length());
        // 用空格分隔字符串，并计算分割后数组的长度
        testApply("test function demo", val -> val.split(" ").length);
        // 角色转换
        System.out.println(conversionUser(new User("张三", 40, "北京"), user -> new Teacher(user.getName(), user.getAge(), "语文")));

        // 将字符串转为大写并加上“Compose”，然后计算拼接后的字符串的长度
        testCompose("test", str -> str.toUpperCase() + "Compose", str -> str.length());
        // 将Integer类型的123和字符串拼“testCompose”接起来，然后将其转为大写
        testCompose(Integer.valueOf("123"), val -> "testCompose(" + val + ")", str -> str.toUpperCase());

        // 将Integer类型的666和字符串类型的888拼接起来，然后转为Integer类型并乘以2
        testAndThen(Integer.valueOf("666"), val -> String.valueOf(val), str -> Integer.valueOf(str + "888") * 2);
    }

    /**
     * Function接口，泛型<T, R>，T：输入的参数类型，R：输出的结果类型。
     * 在本函数中，输入的是String类型，输出的结果是：Integer类型。
     * 直接调用Function中的apply()方法。
     *
     * @param str      输入的参数
     * @param function 函数体
     */
    static void testApply(String str, Function<String, Integer> function) {
        Integer apply = function.apply(str);
        System.out.println(apply);
    }

    /**
     * 将普通用户转换为教师角色
     *
     * @param user     普通用户
     * @param function 转换的过程
     * @return 返回一个教师角色
     */
    static Teacher conversionUser(User user, Function<User, Teacher> function) {
        return function.apply(user);
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

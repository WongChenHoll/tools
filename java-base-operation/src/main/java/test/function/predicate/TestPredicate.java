package test.function.predicate;

import java.util.function.Predicate;

/**
 * @author ChenHol.Wong
 * @create 2020/12/18 10:47
 */
public class TestPredicate {

    public static void main(String[] args) {
        test(100, val -> val > 50);

        testNegate(100, v -> v > 50);

        testOr(100, v -> v > 200, o -> o > 300);
        testOr(100, v -> v > 200, o -> o > 50);
        testOr(100, v -> v > 50, o -> o > 300);

        testAnd("test", str -> str.equals("test"), o -> o.length() == 4);
        testAnd("test", str -> str.equals("test"), o -> o.length() == 8);
        testAnd("test", str -> str.equals("testtest"), o -> o.length() == 4);

        testEqual("test", "test");
        testEqual("test", "testtest");
        testEqual("testtest", "test");
    }

    public static void test(int val, Predicate<Integer> predicate) {
        boolean test = predicate.test(val);
        System.out.println(test);
    }

    public static void testNegate(int val, Predicate<Integer> predicate) {
        Predicate<Integer> negate = predicate.negate();
        boolean test = negate.test(val);
        System.out.println(test);
    }

    public static void testOr(int val, Predicate<Integer> predicate, Predicate<Integer> other) {
        boolean test = predicate.or(other).test(val);
        System.out.println(test);
    }

    public static void testAnd(String str, Predicate<String> predicate, Predicate<String> other) {
        boolean test = predicate.and(other).test(str);
        System.out.println(test);
    }

    public static void testEqual(String s1, String s2) {
        Predicate<Object> equal = Predicate.isEqual(s1);
        boolean test = equal.test(s2);
        System.out.println(test);
    }
}

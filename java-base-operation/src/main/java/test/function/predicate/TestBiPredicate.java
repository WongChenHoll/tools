package test.function.predicate;

import java.util.function.BiPredicate;

/**
 * @author ChenHol.Wong
 * @create 2020/12/18 11:17
 */
public class TestBiPredicate {
    public static void main(String[] args) {
        test(100, 200, (v1, v2) -> v1 > v2);

        testOr("test", "testtest", (s1, s2) -> s2.length() == s1.length(), (o1, o2) -> o1.equals(o2));

        testOr("test", "testtest", (s1, s2) -> s2.length() == 4, (o1, o2) -> "testtest".equals(o2));
        testOr("test", "testtest", (s1, s2) -> s2.length() == 4, (o1, o2) -> "test".equals(o2));
        testOr("test", "testtest", (s1, s2) -> s2.length() == 8, (o1, o2) -> "testtest".equals(o2));

        testAnd("test", "test", (s1, s2) -> s2.length() == s2.length(), (o1, o2) -> o1.equals(o2));

    }

    public static void test(int v1, int v2, BiPredicate<Integer, Integer> predicate) {
        boolean test = predicate.test(v1, v2);
        System.out.println(test);
    }

    public static void testOr(String s1, String s2, BiPredicate<String, String> predicate, BiPredicate<String, String> other) {
        BiPredicate<String, String> or = predicate.or(other);
        boolean test = or.test(s1, s2);
        System.out.println(test);
    }

    public static void testAnd(String s1, String s2, BiPredicate<String, String> predicate, BiPredicate<String, String> other) {
        BiPredicate<String, String> and = predicate.and(other);
        boolean test = and.test(s1, s2);
        System.out.println(test);
    }
}

package test.collection.indissub;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChenHol.Wong
 * @create 2020/10/4 13:34
 */
public class TestStream {

    public static String[] attr1 = new String[]{"A", "B", "C", "D", "E", "F", null};
    public static String[] attr2 = new String[]{"1", "2", "3", "D", "E", "F", null};
    static List<String> list1 = Arrays.asList(attr1);
    static List<String> list2 = Arrays.asList(attr2);

    public static void main(String[] args) {
        List<Object> intersection = list1.stream().filter(item -> list2.contains(item)).collect(Collectors.toList());
        System.out.println("交集：" + intersection);

        List<String> subtract1 = list1.stream().filter(s -> !list2.contains(s)).collect(Collectors.toList());
        System.out.println("集合list1的差集：" + subtract1);
        List<String> subtract2 = list2.stream().filter(s -> !list1.contains(s)).collect(Collectors.toList());
        System.out.println("集合list2的差集：" + subtract2);

        List<String> union1 = list1.parallelStream().collect(Collectors.toList());
        List<String> union2 = list2.parallelStream().collect(Collectors.toList());
        union1.addAll(union2);
        List<String> union3 = union1.stream().distinct().collect(Collectors.toList());
        System.out.println("并集：" + union3);
    }
}

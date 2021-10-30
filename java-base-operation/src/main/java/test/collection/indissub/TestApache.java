package test.collection.indissub;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 测试apache包里的集合并集、交集、补集
 * 交集 = intersection;
 * 并集 = union;
 * 补集 = complement;
 * 析取 = disjunction;
 * 减去 = subtract;
 *
 * @author ChenHol.Wong
 * @create 2020/10/4 12:06
 */
public class TestApache {

    public static String[] attr1 = new String[]{"A", "B", "C", "D", "E", "F", null};
    public static String[] attr2 = new String[]{"1", "2", "3", "D", "E", "F", null};
    public static List<String> list1 = Arrays.asList(attr1);
    public static List<String> list2 = Arrays.asList(attr2);

    public static void main(String[] args) {
        System.out.println("交集：" + CollectionUtils.intersection(list1, list2)); // 交集
        System.out.println("补集：" + CollectionUtils.disjunction(list1, list2)); // 补集
        System.out.println("并集：" + CollectionUtils.union(list1, list2)); // 并集
        System.out.println("list1的差集：" + CollectionUtils.subtract(list1, list2)); // list1的差集
        System.out.println("list2的差集：" + CollectionUtils.subtract(list2, list1)); // list2的差集
    }
}

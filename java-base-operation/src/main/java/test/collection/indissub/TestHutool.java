package test.collection.indissub;

import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试 Hutool 包里的集合并集、交集、补集
 *
 * @author ChenHol.Wong
 * @create 2020/10/4 14:57
 */
public class TestHutool {
    public static String[] attr1 = new String[]{"A", "B", "C", "D", "E", "F", null};
    public static String[] attr2 = new String[]{"1", "2", "3", "D", "E", "F", null};

    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList(attr2));

        System.out.println("交集：" + CollectionUtil.intersection(list1, list2)); // 交集
        System.out.println("补集：" + CollectionUtil.disjunction(list1, list2)); // 补集
        System.out.println("并集：" + CollectionUtil.union(list1, list2)); //并集
        System.out.println("list1的差集"+CollectionUtil.subtract(list1,list2));
        System.out.println("list2的差集"+CollectionUtil.subtract(list2,list1));
        System.out.println("list1的差集：" + CollectionUtil.subtractToList(list1, list2));
        System.out.println("list2的差集：" + CollectionUtil.subtractToList(list2, list1));
    }
}

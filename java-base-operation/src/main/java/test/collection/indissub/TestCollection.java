package test.collection.indissub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 测试 Collection接口自带的方法求集合并集、交集、补集
 *
 * @author ChenHol.Wong
 * @create 2020/10/5 13:16
 */
public class TestCollection {

    public static String[] attr1 = new String[]{"A", "B", "C", "D", "E", "F", null};
    public static String[] attr2 = new String[]{"1", "2", "3", "D", "E", "F", null};

    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList(attr2));
        list1.retainAll(list2);
        System.out.println("交集：" + list1);

        ArrayList<String> list3 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list4 = new ArrayList<>(Arrays.asList(attr2));
        HashSet<Object> set = new HashSet<>();
        set.addAll(list3);
        set.addAll(list4);
        System.out.println("并集：" + set);

        ArrayList<String> list5 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list6 = new ArrayList<>(Arrays.asList(attr2));
        list5.removeAll(list6);
        System.out.println("集合A的差集：" + list5);
        ArrayList<String> list7 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list8 = new ArrayList<>(Arrays.asList(attr2));
        list8.removeAll(list7);
        System.out.println("集合B的差集：" + list8);
    }
}

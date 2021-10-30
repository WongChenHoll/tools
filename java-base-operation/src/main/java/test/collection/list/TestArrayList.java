package test.collection.list;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author ChenHol.Wong
 * @create 2020/8/10 22:38
 */
public class TestArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(1,0);

        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("测试 >> 用法："+(12 >> 1));
    }
}

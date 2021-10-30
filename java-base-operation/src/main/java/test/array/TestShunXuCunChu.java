package test.array;

import java.util.ArrayList;

/**测试线性表中顺序存储
 * @author ChenHol.Wong
 * @create 2020/5/16 22:51
 */
public class TestShunXuCunChu {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list.toString());

        // 向List中第二个位置添加元素，即下表是1的位置，则之前此位置后面的所有元素都要往后移一位
        list.add(1,"2");
        System.out.println(list.toString());
        // 删除某个位置的元素后，则其后面的元素都会向前移一位
        list.remove(2);
        System.out.println(list.toString());

    }
}

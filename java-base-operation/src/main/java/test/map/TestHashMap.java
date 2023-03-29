package test.map;

import java.util.*;

/**
 * 测试HashMap的源码流程
 *
 * @author ChenHol.Wong
 * @create 2020/8/23 13:35
 */
public class TestHashMap {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        // 放入50个元素，查看每个元素的位置，以及链表情况
        for (int i = 0; i < 50; i++) {
            map.put(i + "", "" + i * 10);
        }

        // 生成一些 key，是其在HashMap中的table数组坐标相同
        HashMap<Integer, List<String>> hash = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int index = index(i + "");
            if (hash.containsKey(index)) {
                hash.get(index).add(i + "");
            } else {
                hash.put(index, new ArrayList<>(Collections.singletonList(i + "")));
            }
        }
        System.out.println(hash);

        // 选中其中一些坐标相同的数据
        // 这里选择坐标都是17的数据，共15个。17=[111, 155, 199, 232, 276, 353, 397, 430, 474, 551, 595, 672, 793, 870, 991]
        // 因为数据超过8个，因为该位置（table[17]）的链表会转换成红黑树保存
        List<String> list = hash.get(17);
        for (String s : list) {
            map.put(s, s);
        }
        // 取数，查看执行流程
        System.out.println(map.get("397"));
    }

    static int index(Object key) {
        return 127 & hash(key);
    }

    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}

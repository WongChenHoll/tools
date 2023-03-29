package test.map;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * @author WongChenHoll
 * @description
 * @date 2023-3-28 星期二 15:15
 **/
public class TestHashTable {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("1", "a");
        System.out.println(0x7FFFFFFF);
        System.out.println(1 << 30);
        System.out.println(Integer.MAX_VALUE);

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("","");

        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("","");
    }
}

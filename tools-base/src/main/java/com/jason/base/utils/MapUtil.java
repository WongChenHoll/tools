package com.jason.base.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author WongChenHoll
 * @description
 * @date 2022-11-9 星期三 10:55
 **/
public class MapUtil {
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("aa", 11);
        hashMap.put("bb", 22);
        hashMap.put("cc", 33);
        hashMap.put("dd", 44);
        hashMap.put("ee", 55);
        hashMap.put("ff", 66);
        hashMap.put("gg", 77);
        hashMap.put("hh", 88);
        hashMap.put("ii", 99);

//        System.out.println(subMap(hashMap, 0, 5));
//        System.out.println(subMap(hashMap, 5));
        System.out.println(subMapAsNew(hashMap, 3, 5));
        System.out.println(subMapAsNew(hashMap, 5));

    }

    private MapUtil() {
    }

    public static <K, V> Map<K, V> subMap(Map<K, V> map, int count) {
        return subMap(map, 0, count - 1);
    }

    public static <K, V> Map<K, V> subMap(Map<K, V> map, int from, int end) {
        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        int cursor = 0;
        while (iterator.hasNext()) {
            iterator.next();
            if (cursor < from || cursor > end) {
                iterator.remove();
            }
            cursor++;
        }
        return map;
    }


    public static <K, V> Map<K, V> subMapAsNew(Map<K, V> map, int count) {
        return subMap(map, 0, count - 1);
    }

    public static <K, V> Map<K, V> subMapAsNew(Map<K, V> map, int from, int end) {
        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        LinkedHashMap<K, V> linkedHashMap = new LinkedHashMap<>();
        int cursor = 0;
        while (iterator.hasNext()) {
            Map.Entry<K, V> next = iterator.next();
            if (cursor >= from && cursor <= end) {
                linkedHashMap.put(next.getKey(), next.getValue());
            }
            cursor++;
        }
        return linkedHashMap;
    }
}

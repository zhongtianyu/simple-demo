package com.sz.Circle;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/7/12 14:34
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(16);
        map.put("1", "");
        map.put("2", "");
        map.put("3", "");

        changeMap(map);
        System.out.println(map);

        List<String> list = new ArrayList<>();
        String extracted = extracted(list);
        System.out.println(extracted);
        list.add("234");
        for (String s : list) {
            System.out.println(s);
        }
    }

    private static String extracted(List list) {
        list.add("123");
        return "1";
    }

    private static void changeMap(Map<String, String> map) {
        /*for (String key : map.keySet()) {
            if (key.equals("1")) {
                map.put(key, "1");
            }
        }*/
        Stream<String> stringStream = map.keySet().stream().filter("1"::equals);
    }
}

package com.sz.String;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/7/23 10:08
 */
public class Final {

    private final static Map<String, String> configMap = new ConcurrentHashMap<String, String>(16);

    public static void main(String[] args) {
        final Map<String, String> map = new ConcurrentHashMap<String, String>(16);
        put(map);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }
    }



    private static void put(Map<String, String> map) {
        String key1 = "1";
        String key2 = "2";
        String value1 = "1";
        String value2 = "2";
        configMap.putIfAbsent(key1, value1);
        configMap.put(key1, value1);
        configMap.put(key1, value2);
        configMap.put(key2, value2);

        map.put(key1, value1);
        map.put(key1, value2);
        map.put(key2, value2);
    }
}

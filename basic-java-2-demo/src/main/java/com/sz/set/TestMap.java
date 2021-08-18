package com.sz.set;

import java.util.HashMap;
import java.util.Map;

/**
 * @author karanda
 * @description: 测试map
 * @date: 2020/8/28 9:37
 */
public class TestMap {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        map.put("4",4);
        map.put("5",5);

        for (String value : map.keySet()) {
            System.out.println(value);
        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key+"|"+value);
        }
    }
}

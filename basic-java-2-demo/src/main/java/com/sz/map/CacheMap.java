package com.sz.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/8/6 12:07
 */
public class CacheMap {

    private final static HashMap<String,String> cacheMap = new HashMap<String,String>();

    public static void main(String[] args) {
        cacheMap.put("1","2");
        changeMap();

        for (Map.Entry<String, String> entry : cacheMap.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    private static void changeMap() {
        if(!cacheMap.get("1").equals("")||cacheMap.get("1") != null){
            cacheMap.put("1","3");
        }
    }
}

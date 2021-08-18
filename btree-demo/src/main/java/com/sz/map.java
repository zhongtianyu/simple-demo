package com.sz;

import java.util.HashMap;

/**
 * @author karanda
 * @description: TODO
 * @date: 2020/8/6 21:44
 */
public class map {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put(1,'e');
        hashMap.put(2,"123");

        System.out.println("1:"+hashMap.keySet());
        System.out.println("2:"+hashMap.entrySet());


        String path= "123456789";
        System.out.println("3:"+path.trim());

    }
}

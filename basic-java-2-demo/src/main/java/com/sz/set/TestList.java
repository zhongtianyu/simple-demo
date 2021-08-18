package com.sz.set;

import java.util.ArrayList;
import java.util.List;

/**
 * @author karanda
 * @description: List集合
 * @date: 2020/8/29 10:26
 */
public class TestList {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<String>();

        list.add(0,"0");
        list.add(1,"01");
        list.add(2,"02");
        list.add(3,"03");
        list.add(4,"02");
        list.add(5,"05");

        for (String num : list) {
            System.out.println(num);
        }

        System.out.println("——————————————————————————");
        System.out.println(list.size());
    }
}

package com.sz.Circle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2020/11/3 15:49
 */
public class Continue {
    public static void main(String[] args) {

        //check1();
        check2();
    }

    private static void check2() {
        //continue的作用就是跳过当前循环
        List<String> OutList = new ArrayList<String>();
        OutList.add("0");
        OutList.add("1");
        OutList.add("2");

        for (String out : OutList) {
            if ("1".equals(out)||"2".equals(out)||"3".equals(out)) {
                System.out.println(out);
            }
        }
    }

    private static void check1() {
        //continue的作用就是跳过当前循环
        List<String> OutList = new ArrayList<String>();
        OutList.add("0");
        OutList.add("1");

        for (String out : OutList) {
            List<String> Inlist = new ArrayList<String>();
            if (Inlist == null) {
                continue;
            } else if (Inlist.size() == 0) {
                System.out.println(out);
            } else {
                System.out.println("我是最后的了");
            }
        }
    }
}

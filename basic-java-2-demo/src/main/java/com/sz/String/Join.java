package com.sz.String;

import java.util.ArrayList;

/**
 *
 *  join(a, b)，将b字节序列以a分割，如果b只有一个则返回b
 *  本质上是一个StringJoiner对象，通过append拼接a
 * @author chenjiahao
 * @date 2021/7/26 14:04
 */
public class Join {

    public static void main(String[] args) {
        String[] headers = new String[2];
        ArrayList<String> list = new ArrayList<>();
        String abc ="";
        list.add("a");
        //list.add("b");
        String join = String.join(",", list);
        System.out.println(join);
    }
}

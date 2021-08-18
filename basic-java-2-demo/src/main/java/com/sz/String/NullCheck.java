package com.sz.String;

/**
 * 如果为null，与字符串拼接是会将null一起拼接
 *
 * @author chenjiahao
 * @date 2021/7/14 14:45
 */
public class NullCheck {

    public static void main(String[] args) {

        String a = "123";

        String b = null;
        String c = "";
        Object d = null;

        System.out.println(a + b);//123null
        System.out.println(a + c);//123
        System.out.println(a + d);//123null
    }
}

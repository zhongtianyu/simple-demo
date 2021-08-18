package com.sz;

/**
 *
 *
 * @author chenjiahao
 * @date 2021/8/3 10:46
 */
public class BooleanCheck {
    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        String c = "a";
        int d = 3;
        System.out.println(false + "a");
        System.out.println("a" + false);
        System.out.println(a == c + false);//等同于比较"a" == "cfalse"
        System.out.println(d++);
        System.out.println(++d);
        System.out.println("a == b:" + a == b);
    }
}

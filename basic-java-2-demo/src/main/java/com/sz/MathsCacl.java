package com.sz;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/8/3 17:52
 */
public class MathsCacl {
    public static void main(String[] args) {
        String num = "1234";
        String ca = "-1234";
        extracted(num);
        extracted(ca);
    }

    private static void extracted(String num) {
        if (num != null) {
            int anInt = Integer.parseInt(num);
            anInt = -Math.abs(anInt);
            String value = String.valueOf(anInt);
            System.out.println(value);
        }
    }
}

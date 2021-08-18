package com.sz.String;


/**
 * @description TODO
 *
 * @author chenjiahao
 * @date 2021/7/27 10:33
 */
public class NewStringBuffer {

    public static void main(String[] args) {

        StringBuffer checkNull = new StringBuffer();
        checkNull.append("1");
        System.out.println("checkNull:"+checkNull);

        sb();
    }

    private static void sb() {
        StringBuffer checkBlank = new StringBuffer("");
        checkBlank.append("1");
        System.out.println("checkBlank:"+checkBlank);
        StringBuffer checkString = new StringBuffer("123");
        System.out.println("checkString:"+checkString);
        //StringBuffer checkNull = new StringBuffer(null);
       // System.out.println(checkNull.toString());

    }
}

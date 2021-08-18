package com.sz.String;

/**
 *
 * trim()去除首尾的 空格
 *
 * @author chenjiahao
 * @date 2021/8/2 21:20
 */
public class Trim {
    public static void main(String[] args) {

        String a = new String("   www.  baidu  .com   ");
        System.out.println("原始值:"+a);
        System.out.println("截取以后的值："+a.trim());
    }
}

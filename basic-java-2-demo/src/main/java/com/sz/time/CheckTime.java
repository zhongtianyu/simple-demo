package com.sz.time;

import java.util.Date;

/**
 * @author karanda
 * @description: TODO
 * @date: 2020/8/25 16:26
 */
public class CheckTime {

    public static void main(String[] args) {

        //@SuppressWarnings在编译阶段不检查过时的方法
        @SuppressWarnings(value = "unchecked")
        long now = new Date().getTime();

        Date date = new Date();
        System.out.println("new Date:"+date);
        long timeMillis = System.currentTimeMillis();

        System.out.println("new Date().getTime():"+now +"\r\n"+"System:"+timeMillis);

    }
}

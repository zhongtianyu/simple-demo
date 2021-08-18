package com.sz.time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author karanda
 * @description: 获取日期的方法
 * @date: 2020/8/24 18:19
 */
public class CheckDate {
    public static void main(String[] args) {

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String yyr = dateFormat.format(date);
        String[] strings = yyr.split("-");
        String year = strings[0];
        String month = strings[1];
        String day = strings[2];
        System.out.println("年："+year+ "\r\n月："+month+"\r\n日："+day);
    }
}

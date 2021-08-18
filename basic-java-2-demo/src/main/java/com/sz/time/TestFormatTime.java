package com.sz.time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author karanda
 * @description: TODO
 * @date: 2020/8/27 11:25
 */
public class TestFormatTime {

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(simpleDateFormat.format(date));
    }
}

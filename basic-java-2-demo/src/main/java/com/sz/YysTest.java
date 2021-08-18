package com.sz;

import java.util.ArrayList;

/**
 * @author karanda
 * @description: 有一件工具可随机产生4种效果，当前后2人效果闹铃，闹铃次数大于7时发生警报。
 * @date: 2020/3/4 16:35
 */
public class YysTest {
    public static void main(String[] args) {

        /**
         * 幸运图标
         */
        String luck[] = {"图标1", "图标2", "图标3", "图标4"};

        /**
         * 计数器
         */
        int count = 0;

        /**
         * 警报次数
         */
        int ALERT = 7;

        ArrayList<String> list = new ArrayList<String>();

        //将luck数组中随机获取数字进行排列，当前后两个数字相同时停止并记录数据
        while (count < ALERT) {

            int index = (int) (Math.random() * 3 + 1);

            String sign = luck[index];

            for (int i = 0; i < 1; i++) {
                //往list中添加sign图标
                list.add(sign);
                if (list.get(i).equals(list.get(i + 1))) {
                    System.out.println(list.get(i) + " and count = " + count);
                    count++;
                }
            }
        }
    }
}

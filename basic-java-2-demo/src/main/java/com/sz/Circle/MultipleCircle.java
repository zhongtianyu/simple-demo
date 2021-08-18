package com.sz.Circle;

/**
 * @author chenjiahao
 * @description 多重循环输出测试
 * @date 2021/6/22 10:30
 */
public class MultipleCircle {

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            System.out.println("==========循环次数："+(i+1)+"=============");
            try {
                if (true) {
                    System.out.println("第一个if");
                    try {
                        if (true) {
                            int m = 1 / 0;
                            System.out.println("第二个if");
                        }
                    } catch (Exception e) {
                        System.out.println("第一个exception");
                    }
                } else {
                    System.out.println("false");
                }
            } catch (Exception e) {
                System.out.println("第二个exception");
            }
        }

    }
}

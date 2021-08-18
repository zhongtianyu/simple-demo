package com.sz.Circle;

import java.util.Random;
import java.util.Scanner;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/6/25 15:16
 */
public class SwitchCase {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            for (int i = 0; i < 3; i++) {
                String cache = scanner.next();
                switch (cache) {
                    case "1":
                        System.out.println("游戏");
                        break;
                    case "2":
                    case "3":
                        System.out.println("程序");
                        break;
                    default:
                        System.out.println("计算");
                }
            }
        }
    }
}

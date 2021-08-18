package com.sz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author chenjiahao
 * @description 启动类
 * @date 2020/9/11 15:03
 */
@SpringBootApplication
public class ThreadControllerApp {

    public static void main(String[] args) {
        SpringApplication.run(ThreadControllerApp.class,args);
    }
}
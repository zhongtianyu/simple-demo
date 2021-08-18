package com.cmsz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author chenjiahao
 * @description 启动类
 * @date 2020/9/11 15:03
 */
@SpringBootApplication
@EnableScheduling
public class TaskControllerApp {

    public static void main(String[] args) {
        SpringApplication.run(TaskControllerApp.class,args);
    }
}

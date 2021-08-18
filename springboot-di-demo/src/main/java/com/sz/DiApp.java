package com.sz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

/**
 * @author karanda
 * @description: 启动类
 * @date: 2020/8/7 14:26
 */
@SpringBootApplication
@EnableAutoConfiguration
public class DiApp {

    public static void main(String[] args) {
        SpringApplication.run(DiApp.class,args);
    }
}

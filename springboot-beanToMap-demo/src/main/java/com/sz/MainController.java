package com.sz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author karanda
 * @description: 启动类
 * @date: 2020/1/14 16:27
 */
@SpringBootApplication//(scanBasePackages = "com.sz.*")
@ConfigurationProperties(prefix="spring.datasource")
@MapperScan("com.sz.mapper")
public class MainController {

    public static void main(String[] args) {
        SpringApplication.run(MainController.class,args);
    }
}

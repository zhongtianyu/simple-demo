package com.sz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author karanda
 * @description: 启动类
 * @date: 2020/3/17 23:48
 */
@SpringBootApplication
public class Ch934Application implements CommandLineRunner {

    /**
     * 模板对象
     */
    @Autowired
    JmsTemplate jmsTemplate;

    /**
     * 启动方法，等同于启动类
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) {
        SpringApplication.run(Ch934Application.class,args);
    }
     @Override
     public void run(String... args) {
        jmsTemplate.send("creator",new Creator());
    }
}

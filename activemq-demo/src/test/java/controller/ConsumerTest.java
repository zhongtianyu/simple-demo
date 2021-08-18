package controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author karanda
 * @description: 启动类，用于测试消费者
 * @date: 2020/1/13 16:27
 */
public class ConsumerTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("consumer.xml");
    }
}

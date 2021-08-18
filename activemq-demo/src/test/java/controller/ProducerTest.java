package controller;

import com.test.controller.Producer.ProduceService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author karanda
 * @description: 启动类，用于测试生产者
 * @date: 2020/1/13 16:33
 */
public class ProducerTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("producer.xml");
        ProduceService bean = classPathXmlApplicationContext.getBean(ProduceService.class);
        //发送消息
        for (int i = 0; i < 5; i++) {
            bean.sendMessage("test"+ i );
        }
        classPathXmlApplicationContext.close();
    }
}

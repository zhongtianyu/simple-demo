package com.activemqdemo.queue03;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;


/**
 * @author karanda
 * @description: 练习1-消费者的类
 * @date: 2020/1/14 9:53
 */
public class MessageCousmer02 {

    public static String ACTIVEMQ_URL = "tcp://127.0.0.2:61616";

    public static String QUEUE_NAME = "queue03";

    public static void main(String[] args) throws Exception {
        //创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //从工厂中获得连接对象
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();

        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //建立通道
        Destination destination = session.createQueue(QUEUE_NAME);
        //创建消费者
        MessageConsumer consumer = session.createConsumer(destination);

        //创建一个监听器，监听是否有生产者
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {

                Message Textmessage = (TextMessage) message;
                try {
                    System.out.println("消费者收到的消息是：" + ((TextMessage) Textmessage).getText());
                } catch (JMSException e) {
                    System.out.println("没有监听者");
                }
            }
        });
        /*while(true){
            TextMessage message =(TextMessage)consumer.receive(10000);
                    if (null != message){
                        System.out.println("接收到的消息是："+message.getText());
                    }else {
                        break;
                    }
        }*/
    }
}


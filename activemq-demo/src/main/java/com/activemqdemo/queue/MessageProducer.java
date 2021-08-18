package com.activemqdemo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author karanda
 * @description: 生产者的创建类
 * @date: 2020/1/13 11:19
 */
public class MessageProducer {

    /**
     * 定义ActiveMQ的连接地址
     */
    private static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    /**
     * 定义接收消息队列的名称
     */
    private static final String QUEUE_NAME = "queue";
    /**
     * 定义打印文本的次数
     */
    private static final int SEND_NUMBER = 5;

    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //创建连接
        Connection connection = factory.createConnection();
        //打开连接
        connection.start();

        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列目标
        Destination destination = session.createQueue(QUEUE_NAME);
        //创建一个生产者
        javax.jms.MessageProducer sessionProducer = session.createProducer(destination);
        //创建模拟100个消息
        for (int i = 0; i <= SEND_NUMBER; i++) {
            TextMessage textMessage = session.createTextMessage("这是第一个queue发的消息 "+i);
            //发送消息
            sessionProducer.send(textMessage);
            //在本地打印消息
        System.out.println(textMessage.getText());
    }
        //关闭连接
        connection.close();
    }
}

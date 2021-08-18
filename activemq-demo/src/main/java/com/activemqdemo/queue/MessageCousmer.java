package com.activemqdemo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author karanda
 * @description: 消费者的创建类
 * @date: 2020/1/13 11:35
 */
@SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
public class MessageCousmer {

    /**
     * 定义ActiveMQ的连接地址
     */
    private static final String ACTIVEMQ_URL = "tcp://localhost:61616";
    /**
     * 定义发送消息队列的名称
     */
    private static final String QUEUE_NAME = "queue";

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
        //创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //创建消费的监听
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                Message textMessage = (TextMessage) message;
                try {
                    System.out.println(((TextMessage) textMessage).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

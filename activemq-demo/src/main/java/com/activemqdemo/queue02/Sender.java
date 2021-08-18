package com.activemqdemo.queue02;

import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.*;

/**
 * @author karanda
 * @description: 模拟写法
 * @date: 2020/1/13 15:05
 */
public class Sender {

    private static final String ACTIVEMQ_URL = "tcp://localhost:61616";

    private static final String QUEUE_NAME = "queue02";

    private static final int SEND_NUMBER = 10;

    public static void main(String[] args) {
        //ConnectionFactory:连接工厂，JMS用它创建连接
        ActiveMQConnectionFactory connectionFactory;

        //Connection:JMS客户端到JMS Provider的连接
        Connection connection = null;

        //Session:一个发送或接收消息的线程
        Session session;

        //Destination:消息的目的地；消息的接收者
        Destination destination;

                connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        try {
            connection = connectionFactory.createConnection();

            connection.start();

            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

            destination = session.createQueue(QUEUE_NAME);

            MessageProducer producer = session.createProducer(destination);
            
            sendMessage(session,producer);

        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != connection){
                    connection.close();
                }
            } catch (Throwable ignore) {}
        }
    }

    private static void sendMessage(Session session, MessageProducer producer) throws JMSException {

        for (int i = 0; i < SEND_NUMBER; i++) {
            TextMessage textMessage = session.createTextMessage("activemq发送的消息"+ i );
            producer.send(textMessage);
            System.out.println(textMessage.getText() + " "+ i);
        }
    }
}

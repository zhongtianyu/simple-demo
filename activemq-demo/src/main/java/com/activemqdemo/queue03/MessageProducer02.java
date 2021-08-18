package com.activemqdemo.queue03;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author karanda
 * @description: MQ练习-生产者的类
 * @date: 2020/1/14 9:25
 */
public class MessageProducer02 {
    /**
     * 定义ACTIVEMQ的连接地址
     */
    public static String ACTIVEMQ_URL = "tcp://127.0.0.2:61616";

    public static String QUEUE_NAME = "queue03";

    public static void main(String[] args) {

        Connection connection = null;
        //创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        try {
            //从工厂中获得连接
            connection = connectionFactory.createConnection();
            //开始连接
            connection.start();
            //创建会话
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //建立连接通道queue
            Destination destination = session.createQueue(QUEUE_NAME);
            //建立生产者，确定队列对象（destination）
            MessageProducer producer = session.createProducer(destination);

            sendMessage(session, producer);

            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(Session session, MessageProducer producer) throws JMSException {

        for (int i = 0; i < 4; i++) {
            //发送消息
            TextMessage textMessage = session.createTextMessage("这是ACTIVEMQ发送的消息"+i);
            //将创建的消息传到队列中
            producer.send(textMessage);

            System.out.println("我查到了MQ发的消息是：" + textMessage.getText());
        }
        //将输出的结果传到工作台
    }
}

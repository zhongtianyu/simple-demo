package com.test.controller.Consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author karanda
 * @description： 消息的监听者，用于处理消息
 * @date: 2020/1/13 16:21
 */
public class ComsumerMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage)message;
        try {
            System.out.println("接收到的消息..." + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

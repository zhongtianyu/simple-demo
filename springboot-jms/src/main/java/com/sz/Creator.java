package com.sz;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author karanda
 * @description: 创造者，实现MessageCreator
 * @date: 2020/3/17 23:39
 */
public class Creator implements MessageCreator {

    @Override
    public Message createMessage(Session session) throws JMSException {
        return session.createTextMessage("我是创造者");
    }
}

package com.sz.cosumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import sun.plugin2.message.Message;

/**
 * @author karanda
 * @description: 消费者
 * @date: 2020/3/17 23:55
 */
@Component
public class Reciver {

    @JmsListener(destination = "creator")
    public void reciverMessage(String message){
        System.out.println("获得消息:<"+message+">");
    }
}

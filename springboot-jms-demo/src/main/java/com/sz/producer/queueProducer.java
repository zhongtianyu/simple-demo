package com.sz.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author karanda
 * @description: TODO
 * @date: 2020/3/16 23:23
 */
@RestController
public class queueProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("send")
    public void send(String msg){
        jmsMessagingTemplate.convertAndSend("cache",msg);
    }

    @RequestMapping("/sendMap")
    public void sendMap(){
        Map map = new HashMap<>();
        map.put("one",123);
        map.put("two",1234);
        jmsMessagingTemplate.convertAndSend("cache_map",map);
    }
}

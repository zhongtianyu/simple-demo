package com.sz.consumer;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author karanda
 * @description: TODO
 * @date: 2020/3/16 23:34
 */
@Component
public class queueConsumer {

       @JmsListener(destination = "cache")
       public void reaMessage(String msg){
           System.out.println("读取信息："+msg);
   }
}

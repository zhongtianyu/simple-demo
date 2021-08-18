package com.sz;

import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author karanda
 * @description: 启动类检测
 * @date: 2020/3/16 20:44
 */

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello,SpringBoot!";
    }

    @RequestMapping("/hello2")
    public String hello2(){
        return "hello,SpringBoot";
    }
}

package com.sz.OrderOfBeanCreate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(value ="ConfigurationC")
public class ConfigurationC extends ConfigurationA {

    @Autowired(required = false)
    BeanA a;

    @Bean(name="CGetName")
    @Override
    public String getName(){
        return "C继承"+super.getName();
    }

    @Bean(name="CGetNewName")
    @Override
    public String getNewName(){
        //a.testA();
        //return "我是C，是getNewName中"+getName();
        return super.getNewName();
    }
}
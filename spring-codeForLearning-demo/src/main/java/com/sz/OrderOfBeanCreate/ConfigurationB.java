package com.sz.OrderOfBeanCreate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "ConfigurationB")
public class ConfigurationB extends ConfigurationA {

    @Bean(name="BGetName")
    @Override
    public String getName(){
       return "B继承"+super.getName();
    }

    @Bean(name="BGetNewName")
    @Override
    public String getNewName(){
        return super.getNewName();
    }
}
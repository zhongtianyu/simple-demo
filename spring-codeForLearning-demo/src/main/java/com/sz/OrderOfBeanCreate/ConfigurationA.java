package com.sz.OrderOfBeanCreate;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration(value ="ConfigurationA")
public class ConfigurationA {

    @Bean(name="AGetName")
    public String getName() {
        return "工具类A";
    }

    @Bean(name="AGetNewName")
    public String getNewName() {
        BeanC beanC = new BeanC();
        beanC.setName("new了一个Bean");
        return "工具类A "+beanC.getName();
    }
}

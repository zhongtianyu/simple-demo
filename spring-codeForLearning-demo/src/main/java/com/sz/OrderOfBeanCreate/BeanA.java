package com.sz.OrderOfBeanCreate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 *
 * @author chenjiahao
 *
 * @date 2021/7/5 11:59
 */
@Component
public class BeanA {

    @Autowired
    BeanB b;


    @PostConstruct
    public void init(){
        BeanC beanC = new BeanC();
        beanC.setName("中的new 的C");
        System.out.println("这是A的init方法");
        System.out.println("这是A的init方法"+beanC.getName());
    }

    public BeanA(){
        BeanC beanC = new BeanC();
        beanC.setName("中的new 的C");
        System.out.println("这是A的构造方法");
        System.out.println("这是A的构造方法"+beanC.getName());
    }

    void testA(){
        BeanC beanC = new BeanC();
        beanC.setName("中的new 的C");
        System.out.println("这是A的测试方法");
        System.out.println("这是A的测试方法"+beanC.getName());
        b.testB();
    }
}

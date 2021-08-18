package com.sz.OrderOfBeanCreate;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 *
 * @author chenjiahao
 *
 * @date 2021/7/5 11:59
 */
@Service
public class BeanB {

    @PostConstruct
    public void init(){
        System.out.println("这是B的init方法");
    }

    public BeanB(){
        BeanC beanC = new BeanC();
        beanC.setName("中的new 的C");
        System.out.println("这是B的构造方法");
        System.out.println("这是B的构造方法"+beanC.getName());
    }

    void testB(){
        System.out.println("这是B的测试方法");
    }
}

package com.sz.OrderOfBeanCreate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试顺序:构造方法>@Autowired>@PostConstruct，方法中都可以将new对象
 *
 * @author chenjiahao
 * @date 2021/7/5 12:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestOrder {

    @Autowired
    BeanA a;

    @Autowired
    @Qualifier(value = "AGetName")
    String AGetName;

    @Autowired
    @Qualifier(value = "BGetName")
    String BGetName;

    @Autowired
    @Qualifier(value = "CGetName")
    String CGetName;

    @Autowired
    @Qualifier(value = "AGetNewName")
    String AGetNewName;

    @Autowired
    @Qualifier(value = "BGetNewName")
    String BGetNewName;

    @Autowired
    @Qualifier(value = "CGetNewName")
    String CGetNewName;

    @Test
    public void testOrder() {
        a.testA();
    }

    @Test
    public void testConfiguration() {
        System.out.println("===============B、C继承A==============");
        System.out.println("A为'工具类A'，输出为<" + AGetName+">");
        System.out.println("B为super.A方法，输出为<" + BGetName+">");
        System.out.println("C为super.A方法，输出为<" + CGetName+">");
        System.out.println("=====================================");
        System.out.println("A为'工具类A'+'new对象',输出为<"+AGetNewName+">");
        System.out.println("B为'B继承'+super.A方法，输出为<"+BGetNewName+">");
        System.out.println("C为'C继承'+super.A方法，输出为<"+CGetNewName+">");
    }
}

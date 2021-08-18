package com.sz;

import com.sz.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/8/17 16:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class testThread {

    @Autowired
    TestService service;

    @Test
    public void testThreadPool(){
        for (int i = 0; i < 5; i++) {
            service.service();
        }
    }
}

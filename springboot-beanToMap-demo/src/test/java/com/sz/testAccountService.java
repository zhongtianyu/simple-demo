package com.sz;

import com.sz.cosumer.impl.AccountServiceImpl;
import com.sz.domain.AfFlow;
import com.sz.util.BeanMapTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/6/1 10:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class testAccountService {

    @Autowired
    AccountServiceImpl accountService;

    @Test
    public void test(){

        String id = "0055-account";
        for (AfFlow afFlow : accountService.findByProcessId(id)) {
            Map<String, ?> stringMap = BeanMapTool.beanToMap(afFlow);
            System.out.println("  =============================================  ");
            for (Map.Entry<String, ?> stringEntry : stringMap.entrySet()) {
                System.out.print(stringEntry.getKey()+ " : ");
                System.out.println(stringEntry.getValue());
            }
        }
    }
}

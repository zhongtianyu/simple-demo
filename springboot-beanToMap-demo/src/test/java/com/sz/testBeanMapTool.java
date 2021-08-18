package com.sz;

import com.sz.domain.AfFlow;
import com.sz.util.BeanMapTool;
import org.junit.Test;

import java.util.Map;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/6/1 16:25
 */
public class testBeanMapTool {

    @Test
    public void test(){
        AfFlow afFlow = new AfFlow();
        afFlow.setBUSILINE("0055");
        afFlow.setPROCESSID("0055-account");
        Map<String, ?> stringMap = BeanMapTool.beanToMap(afFlow);
        for (Map.Entry<String, ?> stringEntry : stringMap.entrySet()) {
            System.out.print(stringEntry.getKey()+ " : ");
            System.out.println(stringEntry.getValue());
        }
    }
}

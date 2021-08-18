package com.sz;

import com.huawei.shade.org.apache.http.annotation.Contract;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/6/25 11:23
 */
public class testJava8 {

    private static String provices = "100,200";

    private static String tableNames = "BL_0055_ACC_ORI_#{province}";

    public static void main(String[] args) {

        ArrayList list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println("=============Test1========");
        //Test1
        Object collect = list.stream().filter(value -> value == "2").collect(Collectors.toList());
        System.out.println(collect);

        System.out.println("=============Test2========");
        //Test2
        list.forEach(System.out::println);

        System.out.println("=============Test3========");
        //Test3
        String replace = tableNames.replace("#{province}", "100");
        System.out.println(replace);
        String tableName = "BL_0055_ACC_ORI_#{province}";
        extracted(tableName);

        //Test4
        Optional<String> provices = Optional.ofNullable(testJava8.provices);
        provices.orElse("清理历史数据失败，业务线%s配置文件%s参数[%s]不能为空");
    }

    private static void extracted(String tableName) {

        /*ArrayList list =tableName->{
            Stream.of(provices.split(","))
                    .forEach(province -> tableName.replace("#{province}", province))});*/
    }
}

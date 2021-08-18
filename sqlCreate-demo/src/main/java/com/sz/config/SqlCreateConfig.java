package com.sz.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Configuration
//加载配置文件信息
@PropertySource(value = "classpath:sql-create-dev.properties", encoding = "utf-8")
@Data
public class SqlCreateConfig {

    @Value("${table.simple.name.desc}")
    private String simpleNameValue;

    @Value("${table.simple.name}")
    private String simpleName;

    @Bean
    public LinkedHashMap<String, String> getTableNamesSuffix() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        List<String> nameList = Arrays.asList(simpleName.split(","));
        List<String> nameDescList = Arrays.asList(simpleNameValue.split(","));
        for (int i = 0; i <nameList.size() && i<nameDescList.size() ; i++) {
            map.put(nameList.get(i),nameDescList.get(i));
        }
        return map;
    }
}
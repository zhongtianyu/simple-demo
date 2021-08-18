package com.sz.config;

import com.sz.entity.CleanDataConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import sun.security.krb5.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjiahao
 * @description TODO
 * @date 2021/6/4 13:27
 */
@Configuration
public class DataStoreConfig {

    @Bean("CleanDataConfList")
    public List<?> getCleanDataMapList() {

        ArrayList<CleanDataConfig> configList = new ArrayList<>();

        CleanDataConfig config = new CleanDataConfig();
        config.setBusiLine("1001");
        config.setTableName("BL_1001_ACC_ORI_UPAY");
        configList.add(config);
        return configList;
    }
}

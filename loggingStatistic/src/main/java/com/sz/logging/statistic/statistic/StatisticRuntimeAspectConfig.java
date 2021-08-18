package com.sz.logging.statistic.statistic;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author : Vander
 * @date :   2021-04-08
 * @description :
 */
@Configuration
@EnableAspectJAutoProxy
public class StatisticRuntimeAspectConfig {

    @Bean
    public StatisticRuntimeAspect statisticRuntimeAspect() {
        return new StatisticRuntimeAspect();
    }

}

package com.sz.ConditionalOnBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuration2 {

    @Bean
    public Bean2 bean2() {
        return new Bean2();
    }
}
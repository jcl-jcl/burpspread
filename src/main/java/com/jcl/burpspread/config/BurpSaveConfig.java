package com.jcl.burpspread.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BurpSaveConfig {
    @Bean
    public List<String> saveBurp(){
        return Lists.newArrayList("a","b","c");
    }
}

package com.mfy.rule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

@Configuration
public class MySelfRule
{
    @Bean
    public IRule myRule()
    {
        return new CustomRule();// 我自定义为每台机器5次
    }
}


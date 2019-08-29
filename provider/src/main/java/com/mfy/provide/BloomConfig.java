package com.mfy.provide;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 布隆过滤器
 * */
@Component
public class BloomConfig {

    @Bean
    public BloomFilter bloomFilter(){
        BloomFilter<String> filter = BloomFilter.create(new Funnel<String>() {
            @Override
            public void funnel(String s, PrimitiveSink primitiveSink) {
                primitiveSink.putString(s, Charsets.UTF_8);
            }
        }, 10000, 0.0001);
        return filter;
    }
}

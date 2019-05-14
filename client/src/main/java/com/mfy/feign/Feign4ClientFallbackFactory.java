package com.mfy.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component // 不要忘记添加，不要忘记添加
public class Feign4ClientFallbackFactory implements FallbackFactory<Feign4Client>
{
    @Override
    public Feign4Client create(Throwable throwable)
    {
        return new Feign4Client() {
            @Override
            public String show() {
                return "show方法fallBack";
            }

            @Override
            public String find() {
                return "find方法fallBack";
            }
        };
    }
}

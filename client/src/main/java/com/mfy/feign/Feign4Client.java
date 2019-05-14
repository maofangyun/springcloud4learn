package com.mfy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "provider",decode404 = true,fallbackFactory = Feign4ClientFallbackFactory.class)
public interface Feign4Client {

    @RequestMapping(value = "/provide",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    String show();

    @RequestMapping(value = "/get",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    String find();
}

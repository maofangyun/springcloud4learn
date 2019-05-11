package com.mfy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "provider")
@Component
public interface Feign4Client {

    @RequestMapping(value = "/provide",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    String show();
}

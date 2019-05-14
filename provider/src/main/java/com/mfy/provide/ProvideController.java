package com.mfy.provide;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProvideController {

    @RequestMapping("/provide")
    public String test(HttpServletRequest request){
        return "提供provide服务！"+request.getServerPort();
    }

    @RequestMapping("/get")
    public String find(HttpServletRequest request){
        return "提供find服务！"+request.getServerPort();
    }
}

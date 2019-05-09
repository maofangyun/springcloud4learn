package com.mfy.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @RequestMapping("/test")
    public void test(){
        System.out.println("test");
    }
}

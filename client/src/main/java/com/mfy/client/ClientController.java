package com.mfy.client;

import com.mfy.feign.Feign4Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private Feign4Client feign4Client;

    @RequestMapping(value = "/show")
    public String test(){
        String show = feign4Client.show();
        return show;
    }

    @RequestMapping(value = "/find")
    public String test1(){
        String find = feign4Client.find();
        return find;
    }
}

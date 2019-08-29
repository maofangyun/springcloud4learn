package com.mfy.provide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProvideController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/provide")
    public String test(HttpServletRequest request) throws InterruptedException {
        String value = String.valueOf(Thread.currentThread().getId());
        while(RedisDistributeLock.lock(redisTemplate,"test", value,30) == 0){
            continue;
        }
        ValueOperations ops = redisTemplate.opsForValue();
        Object count = ops.get("count");
        if(count == null)
            count = 0;
        int num = (int)count;
        ops.set("count",++num);
        RedisDistributeLock.unLock(redisTemplate,"test",value);
//        Thread.sleep(4000);
        String s = "提供provide服务！" + request.getServerPort();
        System.out.println(s);
        return s;
    }

    @RequestMapping("/get")
    public String find(HttpServletRequest request){
        return "提供find服务！"+request.getServerPort();
    }

    @RequestMapping("/count")
    public int count(HttpServletRequest request){
        ValueOperations ops = redisTemplate.opsForValue();
        return (int)ops.get("count");
    }
}

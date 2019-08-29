package com.mfy.provide;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 基于redis的分布式锁实现
 * */
public class RedisDistributeLock {

    public static int lock(final RedisTemplate redisTemplate, String key, String val, int expireTime){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Boolean success = valueOperations.setIfAbsent(key, val, expireTime, TimeUnit.SECONDS);
        //获取锁失败
        if(!success)
            return 0;
        //开启守护线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;){
                    redisTemplate.expire(key,expireTime,TimeUnit.SECONDS);
                    try {
                        Thread.sleep(expireTime*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        return 1;
    }

    public static void unLock(final RedisTemplate redisTemplate, String key, String val){
        String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        RedisScript<Boolean> redisScript = new DefaultRedisScript<Boolean>(luaScript);
        ((DefaultRedisScript<Boolean>) redisScript).setResultType(Boolean.class);
        List<String> keys = new ArrayList<>();
        keys.add(key);
        redisTemplate.execute(redisScript,keys,val);
    }
}

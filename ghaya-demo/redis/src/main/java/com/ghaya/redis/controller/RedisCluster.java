package com.ghaya.redis.controller;


import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisCluster {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    @GetMapping(value = "/getLock3")
    public String getLock3() {
        String lockKey = "lock";
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            redissonLock.lock();
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("售卖成功,剩余" + realStock + "");

                return "success";
            } else {
                System.out.println("剩余库存不足");
                return "fail";
            }
        } finally {
            redissonLock.unlock();
        }
    }
}
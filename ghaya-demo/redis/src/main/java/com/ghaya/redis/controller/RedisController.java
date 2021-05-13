package com.ghaya.redis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁实现
 * https://www.bilibili.com/video/BV1FJ411a7gv
 */
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //我们在Zookeeper中提到过分布式锁,
    ////这里我们先用redis实现一个简单的分布式锁,
    ////这里是我们一个简单的售卖减库存的小实例,
    ////剩余库存假设存在数据库内

    /**
     * 单线程高并发
     *
     * @return
     */
    @RequestMapping("/getLock1")
    public String getLock1() {
        synchronized (this) {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realstock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realstock + "");
                System.out.println("售卖成功,剩余" + realstock + "");
                return "success";
            } else {
                System.out.println("剩余库存不足");
                return "fail";
            }

        }
    }


    /**
     * @return
     */
    @RequestMapping("/getLock2")
    public String getLock2() {
        String key = "product_001";
        String lockValue = UUID.randomUUID().toString();
        //取redis锁
        //10,TimeUnit.SECONDS  超时销毁
//        Boolean isExist = stringRedisTemplate.opsForValue().setIfAbsent(key, "ange");
//        Boolean isExist = stringRedisTemplate.opsForValue().setIfAbsent(key, "ange",10,TimeUnit.SECONDS);  //当业务流程超出10秒  会出现恶性循环失效锁  下一个请求删除了上一个的锁
        Boolean isExist = stringRedisTemplate.opsForValue().setIfAbsent(key, lockValue, 10, TimeUnit.SECONDS);  //用uuid来解决别人删了我的锁 失效锁问题


        if (!isExist) {
            //            System.out.println(Thread.currentThread().getId()+Thread.currentThread().getName());
            return "lock";
        }
        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));


        try {
            if (stock > 0) {
                int realstock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realstock + "");
                System.out.println("售卖成功,剩余" + realstock + "");
//                int i = 1/0;//异常死锁
                stringRedisTemplate.delete(key);
                return "success";
            } else {
                System.out.println("剩余库存不足");
                stringRedisTemplate.delete(key);
                return "fail";
            }
        } finally {
            if (isExist &&
                    lockValue.equals(stringRedisTemplate.opsForValue().get(key))) {//避免出现别人删了我的锁
                stringRedisTemplate.delete(key);
            }
        }


    }

}

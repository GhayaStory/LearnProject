package com.ghaya.redis.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 缓存击穿
 * 用互斥锁
 */
public class 缓存击穿 {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    static Lock reenLock = new ReentrantLock();

    public List<String> getData04() throws InterruptedException {
        List<String> result = new ArrayList<String>();
        // 从缓存读取数据
        result = getDataFromCache();
        if (result.isEmpty()) {
            if (reenLock.tryLock()) {
                try {
                    System.out.println("我拿到锁了,从DB获取数据库后写入缓存");
                    // 从数据库查询数据
                    result = getDataFromDB();
                    // 将查询到的数据写入缓存
                    setDataToCache(result);
                } finally {
                    reenLock.unlock();// 释放锁
                }

            } else {
                result = getDataFromCache();// 先查一下缓存
                if (result.isEmpty()) {
                    System.out.println("我没拿到锁,缓存也没数据,先小憩一下");
                    Thread.sleep(100);// 小憩一会儿
                    return getData04();// 重试
                }
            }
        }
        return result;
    }

    public List<String> getDataFromCache(){
        System.out.println("缓存读取数据");
        return null;
    }

    public List<String> getDataFromDB(){
        System.out.println("数据库读取数据");
        return null;
    }

    public void setDataToCache(List<String> list){
        System.out.println("写入缓存" + list);
    }


}

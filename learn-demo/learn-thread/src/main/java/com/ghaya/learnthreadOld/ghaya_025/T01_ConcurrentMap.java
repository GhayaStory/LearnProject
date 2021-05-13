/**
 * http://blog.csdn.net/sunxianghuang/article/details/52221913
 * http://www.educity.cn/java/498061.html
 * 阅读concurrentskipmap
 */
package com.ghaya.learnthreadOld.ghaya_025;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class T01_ConcurrentMap {

    public static void main(String[] args) {

//        ConcurrentHashMap   默认分16组锁定对象  性能相对Hashtable较好
//        Hashtable 锁定整个对象  同时只能访问一个
//        Map<String, String> map = new ConcurrentHashMap<>();//并发的HashMap   958-1000
        Map<String, String> map = new ConcurrentSkipListMap<>();//高并发并且排序  插入效率低   跳表数据结构

//        Map<String, String> map = new Hashtable<>();//所有方法都加锁    1400-1480
//        Map<String, String> map = new HashMap<>();//加锁方法Collections.synchronizedMap()
//        Map<String, String> map = new TreeMap<>();//非并发下  排序
        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < ths.length; i++) {
            ths[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    map.put("a"+r.nextInt(100000),"a"+r.nextInt(100000));
                }
                latch.countDown();
            },"t"+i);
        }
        //将数组转换成list
        Arrays.asList(ths).forEach(t->t.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);

    }

}






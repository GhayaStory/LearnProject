package com.ghaya.learnthreadOld.ghaya_025;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ArrayBlockingQueue 有个数上限
 */

public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);//
        }
        boolean b = false;
        strs.put("aaa");//满了会等待，程序无限阻塞
//        b = strs.add("aaa");//满了会抛异常
//        b = strs.offer("aaa");//满了返回false
//        b = strs.offer("aaa",1,TimeUnit.SECONDS);//一段时间后还加不进去就不加了
//
        System.out.println(strs);
        System.out.println(b);
    }
}

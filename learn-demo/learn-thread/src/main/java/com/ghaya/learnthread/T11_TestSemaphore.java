package com.ghaya.learnthread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 限流锁
 */
public class T11_TestSemaphore {

    public static void main(String[] args) {

        Semaphore s = new Semaphore(2);//几个线程同时执行

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    s.acquire();
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"..........");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    s.release();
                }
            },"t"+i).start();
        }

    }
}

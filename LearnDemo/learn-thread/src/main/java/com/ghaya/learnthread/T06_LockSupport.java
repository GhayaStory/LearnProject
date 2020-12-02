package com.ghaya.learnthread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T06_LockSupport {
    volatile List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return this.list.size();
    }

    public static void main(String[] args) {
        T06_LockSupport c = new T06_LockSupport();
        CountDownLatch latch = new CountDownLatch(1);

        Thread t2 = new Thread(()->{
            if(c.size()!=5){
                LockSupport.park();
            }
            System.out.println("t2 END");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2");
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                c.add(i);
                if (c.size()==5){
                    LockSupport.unpark(t2);
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");


        t2.start();

        t1.start();

    }
}

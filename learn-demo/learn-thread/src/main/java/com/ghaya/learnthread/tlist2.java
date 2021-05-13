package com.ghaya.learnthread;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add.size
 * 写两个线程，线盈添加10个元系到容器中，线程2实现监控元系的个数，当个数到5个时，线程2给出提示并结束
 * 分所下面这个程序，能完成这个功能吗？
 */
public class tlist2 {
//    volatile //修饰引用
//    private volatile List list = new LinkedList();

    private List list = Collections.synchronizedList(new LinkedList());

    public void add(Object o) {
        list.add(o);
        System.out.println(this.list.size());
    }

    public int size() {
        return this.list.size();
    }


    public static void main(String[] args) {
        tlist2 tlist = new tlist2();
        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    tlist.add(i);
                    System.out.println("add" + i);
                    if (tlist.size() == 5) {
                        lock.notify();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}

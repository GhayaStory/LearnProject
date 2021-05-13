package com.ghaya.learnthread;


import sun.rmi.transport.ObjectTable;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add.size
 * 写两个线程，线盈添加10个元系到容器中，线程2实现监控元系的个数，当个数到5个时，线程2给出提示并结束
 * 分所下面这个程序，能完成这个功能吗？
 */
public class tlist {
//    volatile //修饰引用
//    private volatile List list = new LinkedList();

    private /*volatile*/ List list = Collections.synchronizedList(new LinkedList());

    public tlist() {

    }

    public /*synchronized*/ void add(Object o){
        list.add(o);
        System.out.println(this.list.size());
    }

    public /*synchronized*/ int size(){
        return this.list.size();
    }


    public static void main(String[] args) {
        tlist tlist = new tlist();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                tlist.add(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread t2 = new Thread(() -> {
           while(true){
                if(tlist.size()==3){
                    System.out.println("t2结束");
                    break;
                }
            }
        });

        t1.start();
        t2.start();
    }
}

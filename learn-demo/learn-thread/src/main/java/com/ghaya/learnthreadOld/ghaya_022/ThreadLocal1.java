/**
 * ThreadLocal 线程局部变量
 */
package com.ghaya.learnthreadOld.ghaya_022;

import java.util.concurrent.TimeUnit;

public class ThreadLocal1 {
    volatile static Person p = new Person();

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        },"t1").start();


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "lisi";
            System.out.println(p.name);
        },"t2").start();
    }
}

class Person{
    String name = "zhangsan";
}

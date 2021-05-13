/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁
 * 也就是说synchronized获得的锁是可重入的
 * @author Ghaya
 */
package com.ghaya.learnthreadOld.ghaya_009;

import java.util.concurrent.TimeUnit;

public class T {
    synchronized void m1(){
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        m2();
    }

    synchronized void m2(){
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("m2");
    }

    /**
     * synchronized 的锁可以重复获得  同一线程  同一把锁
     */
    /**
     * 模拟死锁
     * 线程1 锁A
     * 线程2 锁B
     * A锁中调用B锁  无法调用
     * 或者A调B调C调D调A循环死锁
     *
     * 活锁
     */
}

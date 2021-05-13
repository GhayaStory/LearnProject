/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁，
 * 也就是说synchonized获得的锁是可重入的
 * 这里是继承中可能发生的情形，子类调用父类的同步方法
 * @author Ghaya
 */
package com.ghaya.learnthreadOld.ghaya_010;

import java.util.concurrent.TimeUnit;

public class T {
    synchronized void m(){
        System.out.println("move start");
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("move end");
    }

    public static void main(String[] args) {
        new TT().m();
    }
}
class TT extends T{
    @Override
    synchronized void m() {
        System.out.println("child move start");
        super.m();
        System.out.println("child move end");
    }
}

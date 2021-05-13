/**
 * reentrantlock用于替代synchronized
 * 本例中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 这里是复习snchronized最原始的语义
 *
 * 使用reentranlock可以完成同样的功能
 * 需要注意的是，必须要  手动释放锁
 * 使用synchronized锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 * @author Ghaya
 */
package com.ghaya.learnthreadOld.ghaya_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock2 {
    Lock lock = new ReentrantLock();

    void m1(){

        lock.lock();  //等同于   synchronized(this)   但是要手动释放锁
        try {
            for(int i=0;i<10;i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2(){
        lock.lock();
        System.out.println("m2 ....");
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLock2 rl = new ReentrantLock2();
        new Thread(rl::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(rl::m2).start();
    }

}

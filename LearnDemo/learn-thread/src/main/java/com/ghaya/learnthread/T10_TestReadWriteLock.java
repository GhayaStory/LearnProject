package com.ghaya.learnthread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * 读写锁
 * 读写互斥
 *
 * 读锁共享
 * 写锁互斥
 */
public class T10_TestReadWriteLock {
    static Lock lock = new ReentrantLock();
    private static int value;
    static ReadWriteLock readwritelock = new ReentrantReadWriteLock();
    static Lock readlock = readwritelock.readLock();
    static Lock writelock = readwritelock.writeLock();

    //模拟读取操作
    public static void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v) {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("write over！");
            //模拟写操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public static void main(String[] args) {
        Runnable readR = () -> read(lock);
        //Runnable readR=()->read(readLock);
        Runnable writeR = () -> write(lock, new Random().nextInt());
        //Runnable writeR=()->write(writelock, new Random(). nextInt());
        for (int i = 0; i < 18; i++) new Thread(readR).start();
        for (int i = 0; i < 2; i++) new Thread(writeR).start();
    }
}

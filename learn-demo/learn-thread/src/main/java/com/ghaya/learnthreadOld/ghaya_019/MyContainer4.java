/**
 * 曾经的面试题：（淘宝）
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素个数，当个数到5个时，线程2给出提示并结束
 *
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环浪费cpu，如果不用死循环，该怎么做？
 *
 * 调用被锁定对象的wait方法和notify方法
 * wait：暂停 线程1并且释放锁
 * notify：其他 线程2 叫醒继续 线程1
 * notifyAll： 叫醒继续该对象的所有线程
 *
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 *
 * 阅读下面的程序，并分析输出结果
 * 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才接收到通知而退出
 * 为什么？
 *
 * @author Ghaya
 */

//写一个程序计算一个数组中数值总和
//1 for循环
//2

package com.ghaya.learnthreadOld.ghaya_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer4 {
    //添加volatile  使t2监视线程能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer4 c = new MyContainer4();

        final Object lock = new Object();

        //先启动监听线程
        new Thread(() -> {
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() + " 启动");
                if(c.size()!=5){
                    try {
                        lock.wait();//暂停线程  释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
                System.out.println("t2 结束");
                //通知t1继续执行
                lock.notify();
            }
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("t1启动");
            synchronized (lock){
                for(int i=0;i<10;i++){
                    c.add(new Object());
                    System.out.println(Thread.currentThread().getName() + " add " + i);
                    if(c.size() == 5){
                        lock.notify();//继续线程   不会释放锁
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        },"t1").start();

    }
}

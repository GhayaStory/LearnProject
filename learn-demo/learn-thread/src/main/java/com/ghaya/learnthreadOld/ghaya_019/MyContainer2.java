/**
 * 曾经的面试题：（淘宝）
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素个数，当个数到5个时，线程2给出提示并结束
 *
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环浪费cpu，如果不用死循环，该怎么做？
 * @author Ghaya
 */

//写一个程序计算一个数组中数值总和
//1 for循环
//2

package com.ghaya.learnthreadOld.ghaya_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer2 {
    //添加volatile  使t2监视线程能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer2 c = new MyContainer2();

        new Thread(() -> {
            for(int i=0;i<10;i++){
                c.add(new Object());
                System.out.println(Thread.currentThread().getName() + " add " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(()->{
            while(true){
                if(c.size()>=5){
                    System.out.println(Thread.currentThread().getName() + "End");
                }
            }
        },"t2").start();

    }
}

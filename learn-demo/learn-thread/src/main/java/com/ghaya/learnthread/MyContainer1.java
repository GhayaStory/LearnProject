/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，一级getCount方法，
 * 能够支持两个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyALl来实现
 *
 */
package com.ghaya.learnthread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：写一个周定容量同步容器，拥育put 和 get方法，以及getcount方法。
 * 能够支持2个生产者线程以及10个消费音线程的阻塞调用(还可以用cas自旋阻塞)
 *
 * 使用wait 和 notify/notifyAll来实现
 */
public class MyContainer1<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;//最多10个元素
    private int count = 0;

    public synchronized void put(T t){
        while(lists.size() == MAX){
            System.out.println("lists Max");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        this.notifyAll();
    }

    public synchronized T get(){
        T t = null;
        while(lists.size() == 0){
            System.out.println("lists null");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count --;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();
        //启动消费者线程
        for(int i=0;i<10;i++){
            new Thread(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(2001);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j=0;j<5;j++){
                    System.out.println("c:"+c.get());
                }
            },"c"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启用生产者线程
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<5;j++){
                    String tStr = Thread.currentThread().getName()+""+j;
                    c.put(tStr);
                    System.out.println("p:"+ tStr);
                }
            },"p"+i).start();
        }
    }
}

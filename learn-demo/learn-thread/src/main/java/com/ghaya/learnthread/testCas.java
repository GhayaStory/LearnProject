package com.ghaya.learnthread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：写一个周定容量同步容器，拥育put 和 get方法，以及getcount方法。
 * 能够支持2个生产者线程以及10个消费音线程的阻塞调用
 * <p>
 * 使用wait 和 notify/notifyAll来实现
 */
public class testCas<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;//最多10个元素
    private int count = 0;
    private int ver = 0;


    void put(T t) {
        while(count < MAX){//当前个数小于容量
            int i = count;
            int v = ver;
            i++;
            if (i == count - 1 && v==ver ) {
                lists.add(t);
                count++;
                System.out.println(count);
            }
        }
    }

    void get() {

    }

    public static void main(String[] args) {
        testCas<String> testCas = new testCas();

        new Thread(() -> {
            testCas.put("Abc");
        }, "p").start();

//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            new Thread(() -> {
//                testCas.put(""+ finalI);
//            }, "p" + i).start();
//
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

}

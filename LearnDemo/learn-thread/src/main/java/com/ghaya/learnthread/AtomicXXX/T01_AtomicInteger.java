/**
 * 解决同样的问题的更高效的方法，使用AtomXXX类
 * AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 * @author Ghaya
 */

package com.ghaya.learnthread.AtomicXXX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T01_AtomicInteger {
    ///*volatile*/ int count = 0;
    AtomicInteger count = new AtomicInteger(0);  //原子操作类

    /*synchronized*/ void m(){
        for(int i = 0; i < 10000; i++)
            //if count.get() < 1000//原子性
            //此处可能会有线程进来
            count.incrementAndGet();//等于  原子性的 count++  用底层
    }

    public static void main(String[] args) {
        T01_AtomicInteger t = new T01_AtomicInteger();
        List<Thread> threads = new ArrayList<Thread>();
        for(int i = 0; i<10; i++){
            threads.add(new Thread(t::m,"thread-"+i));
        }

        threads.forEach((o)->o.start());
        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
}


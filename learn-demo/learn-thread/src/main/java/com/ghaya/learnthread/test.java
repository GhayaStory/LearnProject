package com.ghaya.learnthread;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class test {

    static volatile int balance;


    public /*synchronized*/ static void addBalance() {
//        synchronized(test.class){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance++;
            System.out.println(balance);
//        }

        new AtomicLong().incrementAndGet();
        new LongAdder().add(1);

    }


    public static void main(String[] args) {

//        System.out.println(args);

//        System.out.println(18 >> 1);
//        ReentrantLock lock = new ReentrantLock();
//        lock.lock();



//        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("key","value");
//        new ArrayList<>();
//        LinkedList<Object> list = new LinkedList<>();
//        list.add(new Object());
//        new HashSet<>().add(123);
        Hashtable<Object, Object> m = new Hashtable<>();
        m.put("abc",123);
        ThreadLocal<String> t = new ThreadLocal<>();
        t.set("123");
        t.get();


//        System.out.println((int)Math.pow(2,36));


    }

}

package com.ghaya.learnthreadOld;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class testCountDownLatch {
    volatile private int count = 0;
    public static void main(String[] args) {
        testCountDownLatch t = new testCountDownLatch();

        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            try {
                latch.await();
                while(true){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("t1 run");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }).start();
        new Thread(() -> {
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                    if(t.count>5 && t.count<8){
                        latch.countDown();
                        System.out.println("t2 keep");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 run");
            }

        }).start();
        new Thread(() -> {
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                    t.count ++;
                    System.out.println(t.count>10);
                    if(t.count==10){
                        latch.await();
                        System.out.println("t3 end");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t3 run:"  +  t.count);
            }

        }).start();

    }
}

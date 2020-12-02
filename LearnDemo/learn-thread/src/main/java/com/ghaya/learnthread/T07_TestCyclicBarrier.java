package com.ghaya.learnthread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 * 栅栏
 *
 */
public class T07_TestCyclicBarrier {
    public static void main(String[] args) {
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(20);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20, () -> {
            System.out.println("满员");
        });

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

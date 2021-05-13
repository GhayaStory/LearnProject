package com.ghaya.learnthread.testVolatile;


/**
 * 测试cpu以同一个缓存行为单位进行可见性通知
 */
public class T01_CacheLinePadding {
    private static class T {
        public volatile  long x = 0L;
    }

    public static T[] arr = new T[2];

    static {
        //大概率放在同一个缓存行里
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });
        Thread t2 = new Thread(()->{
            for (long i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime()-start)/100_0000);
    }
}

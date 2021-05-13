package ghaya.learn.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class test {

    static class T {
        public volatile int x = 0;
    }

    public static void main(String[] args) throws InterruptedException {

        int count = 10;

        Thread t1 = new Thread(() -> {
            ExecutorService eServiuce = Executors.newFixedThreadPool(count);
            for (int i = 0; i < count; i++) {
                int finalI = i;
                eServiuce.submit(()->{
                    try {
                        while(true){
                            System.out.println("1_" + finalI);
                            TimeUnit.SECONDS.sleep(5);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
        Thread t2 = new Thread(() -> {
            ExecutorService eServiuce = Executors.newFixedThreadPool(count);
            for (int i = 0; i < count; i++) {
                int finalI = i;
                eServiuce.submit(()->{
                    try {
                        while(true){
                            System.out.println("2_"+ finalI);
                            TimeUnit.SECONDS.sleep(3);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        });

        t1.start();
        t2.start();
    }

}



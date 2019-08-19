package learn.courseThread.ghaya_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T09_SingleThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();// 只有一个线程，保证任务执行顺序，只用一个线程执行
        System.out.println(service);

        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.execute(()->{
                System.out.println(j+" "+Thread.currentThread().getName());
            });
        }
    }
}

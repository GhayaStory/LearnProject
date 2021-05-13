/**
 *  认识Future
 *  .Callable的返回值
 */
package com.ghaya.learnthreadOld.ghaya_026;

import java.util.concurrent.*;

/**
 * 多线程并行执行，取回结果
 */
public class T06_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(()->{
            System.out.println("start");
            TimeUnit.MILLISECONDS.sleep(2000);
            return 1000;
        });//new Callable(){ Integer call();}
        new Thread(task).start();
        System.out.println(task.get());//阻塞，任务执行完拿到返回值

        //****************************

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f = service.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return 1;
        });
        System.out.println(f.get());
        System.out.println(f.isDone());
        service.shutdown();
    }
}

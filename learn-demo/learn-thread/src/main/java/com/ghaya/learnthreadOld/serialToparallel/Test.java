package com.ghaya.learnthreadOld.serialToparallel;


import com.common.entity.GhayaRes;

import java.util.concurrent.*;

/**
 * demo解决问题，
 * 接口串行调用多个服务
 * 改为多线程并行调用
 * @author https://www.bilibili.com/video/av33459923
 *
 */

public class Test {

    private static UserService service = new UserService();
    private static Test test = new Test();

    public static void main(String[] args) throws Exception{
//        note.t1();
//        note.t2();
//        test.t3();
        test.t4();
    }

    public void t4() throws Exception {//2020-11-25 11:30:45
        int count = 5;
        GhayaRes res = new GhayaRes("s0 Res");

        //用线程池
        ExecutorService eServiuce = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            String t = i+"";
            res.putAll(eServiuce.submit(() ->  service.sAll(t)).get());
        }
        //取出并行调用的结果s
        System.out.println(res.getAll());
        eServiuce.shutdown();
    }

    public void t3() throws Exception {
        //用线程池
        ExecutorService eServiuce = Executors.newFixedThreadPool(5);
        Future<GhayaRes> futureTask = eServiuce.submit(() -> {
            GhayaRes res = service.s1();
            return res;
        });
        Future<GhayaRes> futureTask2 = eServiuce.submit(() -> {
            GhayaRes res = service.s2();
            return res;
        });
        //取出并行调用的结果s
        GhayaRes res = new GhayaRes("s0 Res");
        res.putAll(futureTask.get());
        res.putAll(futureTask2.get());
        System.out.println(res.getAll());

        eServiuce.shutdown();
    }


    public void t2() throws Exception {
        Callable<GhayaRes> ghayaResCallable = () -> {
            GhayaRes res = service.s1();
            return res;
        };
        Callable<GhayaRes> ghayaResCallable2 = () -> {
            GhayaRes res = service.s2();
            return res;
        };

        //包装
        FutureTask<GhayaRes> ghayaResFutureTask = new FutureTask<>(ghayaResCallable);
        FutureTask<GhayaRes> ghayaResFutureTask2 = new FutureTask<>(ghayaResCallable2);

        //多线程运行
        new Thread(ghayaResFutureTask).start();
        new Thread(ghayaResFutureTask2).start();

        //取出并行调用的结果s
        GhayaRes res = new GhayaRes("s0 Res");
        res.putAll(ghayaResFutureTask.get());
        res.putAll(ghayaResFutureTask2.get());
        System.out.println(res.getAll());

    }

    /**
     *  基础并行调用方法
     *  只能调用，但是没有返回值也无法处理异常
     */
    public void t1(){
        //服务1
        Thread thread = new Thread(() -> {
            try {
                GhayaRes res = service.s1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();

        //服务2
        Thread thread2 = new Thread(() -> {
            try {
                GhayaRes res = service.s2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread2.start();
    }
}

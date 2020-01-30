package learn.courseThread.serialToparallel;


import com.sun.org.apache.xpath.internal.functions.FuncTrue;
import learn.Base.GhayaRes;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
//        test.t1();
        test.t2();
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

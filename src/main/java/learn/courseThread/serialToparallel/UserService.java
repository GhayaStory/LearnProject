package learn.courseThread.serialToparallel;


import learn.Base.GhayaRes;

import java.util.concurrent.TimeUnit;

/**
 * demo解决问题，
 * 接口串行调用多个服务
 * 改为多线程并行调用
 * @author https://www.bilibili.com/video/av33459923
 *
 */


public class UserService {

    public GhayaRes s1() throws Exception{
        GhayaRes res = new GhayaRes("s1 Res");

        System.out.println("s1 ing ");
        //模拟延迟
        TimeUnit.MILLISECONDS.sleep(2000);

        System.out.println("s1 ed ");
        return res;
    }

    public GhayaRes s2() throws Exception{
        GhayaRes res = new GhayaRes("s2 Res");

        System.out.println("s2 ing ");
        //模拟延迟
        TimeUnit.MILLISECONDS.sleep(4000);
        System.out.println("s2 ed ");

        return res;
    }

    public GhayaRes s3() throws Exception{
        GhayaRes res = new GhayaRes("s3 Res");

        System.out.println("s3 ing ");
        //模拟延迟
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("s3 ed ");
        return res;
    }
}

/**
 * 自定义线程池
 */
package com.ghaya.learnthreadOld.ghaya_026;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T13_ThreadPoolExecutor {
    public static void main(String[] args) {
        ThreadPoolExecutor p = new ThreadPoolExecutor(1,1,1L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        //ThreadPoolExecutor 是大多数线程池的源头   ForkJoinPool不是
    }
}

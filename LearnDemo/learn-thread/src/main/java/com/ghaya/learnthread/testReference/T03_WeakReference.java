package com.ghaya.learnthread.testReference;

import java.lang.ref.WeakReference;


/**
 * 弱引用
 * 只要用到遇到gc就会被回收
 *
 * ThreadLocal 线程本地对象
 *
 */
public class T03_WeakReference {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
    }
}

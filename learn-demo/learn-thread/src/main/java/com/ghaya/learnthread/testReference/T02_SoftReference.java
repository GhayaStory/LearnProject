package com.ghaya.learnthread.testReference;

import java.lang.ref.SoftReference;


/**
 * 软引用
 * 内存要满了则自动移除，做缓存用
 */
public class T02_SoftReference {
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        byte[] b = new byte[1024 * 1024 * 5];
        System.out.println(m.get());


    }
}

package com.ghaya.learnthread.testReference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 虚引用
 * 管理堆外内存  DirectByteBuffer
 * 原来：网卡->系统->JVM->系统->网卡
 * 现在zero copy：网卡->系统->JVM->网卡
 * https://www.bilibili.com/video/BV1xK4y1C7aT?p=7  6:00
 *
 * 原来：网络访问数据时，网卡复制数据给操作系统，然后操作系统会把数据分一块复制给JVM，JVM进行处理后要返回数据给网卡，会复制一份给操作系统，再交给网卡。
 * 现在省略复制给操作系统，直接NIO给网卡。此时用虚引用管理管理堆外内存。
 *
 * DirectByteBuffer对象虚引用被回收后，会放到一个队列里
 * JVM  有一个GC专门监视队列内容，队列有内容则同步删除堆外内存
 *
 */
public class T04_PhantomReference {

    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);//指定虚引用队列

        new Thread(()->{
            while (true){
                LIST.add(new byte[1024*1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(()->{
            while (true){
                Reference<? extends M> poll = QUEUE.poll();
                if(poll != null){
                    System.out.println("需饮用对象被jvm回收了---------"+ poll);
                }
            }
        }).start();
    }
}

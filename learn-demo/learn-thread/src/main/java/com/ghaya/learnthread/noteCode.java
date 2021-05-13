package com.ghaya.learnthread;

import org.openjdk.jol.info.ClassLayout;

/**
 * 多线程笔记代码
 */
public class noteCode {
    public static void main(String[] args) {
        noteCode n = new noteCode();
        n.testMemoryLayout();
    }

    public void testMemoryLayout(){
        Object o = new Object();
        //jol 内存布局
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}

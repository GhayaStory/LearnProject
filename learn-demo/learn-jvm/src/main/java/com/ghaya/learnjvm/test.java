package com.ghaya.learnjvm;

public class test {
    public static void main(String[] args) {
        new Thread(()->{

        },"test thread").start();
    }

    //native：凡是带了native 关键字的，说明java的作用范围达不到了，会去调用底层c语言的库！
    //会进入本地方法栈
    //调用本地方法本地接口 JNI
    //JNI作用：扩展Java的使用，融合不同的变成语言为Java所用！  最初：C,C++
    //专门在内存中开辟一块区域Native Method Stack，登记native方法

    private native void start0();

}

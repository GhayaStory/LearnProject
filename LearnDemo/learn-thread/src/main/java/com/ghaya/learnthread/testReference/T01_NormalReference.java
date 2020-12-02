package com.ghaya.learnthread.testReference;

import java.io.IOException;


/**
 * 普通引用类型
 */
public class T01_NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m =null;
        System.gc();
        System.in.read();
    }
}

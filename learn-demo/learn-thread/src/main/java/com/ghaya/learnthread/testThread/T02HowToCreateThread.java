package com.ghaya.learnthread.testThread;

public class T02HowToCreateThread {
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("Thread Thread");
        }
    }

    static class MyRun implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread Runnable");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(()->{
            System.out.println("lambda");
        }).start();
    }
}

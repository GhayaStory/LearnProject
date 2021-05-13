package com.ghaya.redis;

public class Site implements Runnable {

    //记录剩余票数
    private int count = 10;
    //记录当前抢到第几张票
    private int num = 0;
    boolean flag = false;

    public void run() {
        //循环，当剩余票数为0时，结束
        while (!false) {
            this.a();
        }
    }

    synchronized public void a() {
        if (count <= 0) {
            flag = true;
            return;
        } else {

        }
        //1、修改数据(剩余票数，抢到第几张票)
        count--;
        //2、显示信息，反馈用户抢到第几张票
        num++;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        System.out.println(Thread.currentThread().getName() + "抢到第" + num + "张票，剩余" + count + "张票");
    }

    public static void main(String[] args) {
        Site site = new Site();
        Thread personl = new Thread(site, "ange");
        Thread person2 = new Thread(site, "pika");
        Thread person3 = new Thread(site, "rock");
        personl.start();
        person2.start();
        person3.start();
    }
}


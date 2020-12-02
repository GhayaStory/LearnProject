package com.ghaya.learnthread;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class T08_TestPhaser {

    static Random r = new Random();
    static testPhaser phaser = new testPhaser();

    static void milliSleep(int milli){
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static class Person implements Runnable{
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void first(){
            milliSleep(r.nextInt(1000));
            System.out.printf("第一阶段\n",name);
            phaser.arriveAndAwaitAdvance();
        }
        public void sevond(){
            milliSleep(r.nextInt(1000));
            System.out.printf("第二阶段\n",name);
            phaser.arriveAndAwaitAdvance();
        }
        public void third(){
            milliSleep(r.nextInt(1000));
            System.out.printf("第三阶段\n",name);
            phaser.arriveAndAwaitAdvance();
        }
        public void fourth(){
            if(name.equals("p5")||name.equals("p6")){
                milliSleep(r.nextInt(1000));
                System.out.printf("第四阶段\n",name);
                phaser.arriveAndAwaitAdvance();
            }else{
                phaser.arriveAndDeregister();
            }

        }

        @Override
        public void run() {
            first();
            sevond();
            third();
            fourth();
        }
    }

    static class testPhaser extends Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch(phase){
                case 1:
                    System.out.println(1);
                    return false;
                case 2:
                    System.out.println(2);
                    return false;
                case 3:
                    System.out.println(3);
                    return false;
                case 4:
                    System.out.println(4);
                    return true;
                    default:
                        System.out.println("error");
                        return false;

            }
        }
    }

    public static void main(String[] args) {
        phaser.bulkRegister(7);
        for (int i = 0; i < 7; i++) {
            new Thread(new Person("p"+i)).start();
        }

    }

}

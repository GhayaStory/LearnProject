/**
 * synchronized关键字
 * 对某个对象加锁
 * @author Ghaya
 */

package ghaya.learn.courseThread.ghaya_005;

import java.util.concurrent.TimeUnit;

public class T implements Runnable{
    private int count = 10;

    public /*synchronized*/ void run(){//这里等同于synchronized(ghaya.learn.courseThread.ghaya_004.T.class)
        //synchronized (T.class){
            count--;
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+",count = " + count);
        //}
    }


    public static void main(String[] args) {
        T t = new T();
        for(int i=0;i<10;i++){
            new Thread(t,"THREAD" + i).start();
        }
    }
    /**
     打印结果
     THREAD1,count = 8
     THREAD0,count = 8
     THREAD2,count = 7
     THREAD3,count = 6
     THREAD4,count = 5

     不加锁的情况下  会出现同时访问方法出现里面属性乱
     */
}

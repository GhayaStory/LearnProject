/**
 * synchronized关键字
 * 对某个对象加锁
 * @author Ghaya
 */

package learn.courseThread.ghaya_006;

public class T implements Runnable{
    private int count = 10;

    public synchronized void run(){//这里等同于synchronized(learn.courseThread.ghaya_004.T.class)
        count--;
        System.out.println(Thread.currentThread().getName()+",count = " + count);
    }


    public static void main(String[] args) {
        T t = new T();
        for(int i=0;i<5;i++){
            new Thread(t,"THREAD" + i).start();
        }
    }
    /**
     打印结果
     THREAD0,count = 9
     THREAD1,count = 8
     THREAD2,count = 7
     THREAD4,count = 6
     THREAD3,count = 5

     加锁之后便不会出现问题，排队访问方法
     */
}

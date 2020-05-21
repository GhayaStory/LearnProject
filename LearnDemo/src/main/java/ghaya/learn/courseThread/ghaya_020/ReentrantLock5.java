/**
 * reentrantlock用于替代synchronized
 * 本例中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 这里是复习snchronized最原始的语义
 *
 * 使用reentranlock可以完成同样的功能
 * 需要注意的是，必须要  手动释放锁
 * 使用synchronized锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 *
 * 使用reentranlock可以进行“尝试锁定”trylock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 *
 * 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应，
 * 在一个线程等待锁的过程中，可以被打断
 *
 * ReentrantLock还可以指定为公平锁（线程排队等待或者最长时间等待的线程优先访问）
 *
 * @author Ghaya
 */
package ghaya.learn.courseThread.ghaya_020;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock5 extends Thread{
    private static ReentrantLock lock = new ReentrantLock(true);//参数为true表示为公平锁顺序获得锁  false的话随机获得t1t2的锁

    public void run(){
        for(int i=0;i<100;i++){
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 rl = new ReentrantLock5();
        Thread t1 = new Thread(rl);
        Thread t2 = new Thread(rl);
        t1.start();
        t2.start();


    }

}

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，一级getCount方法，
 * 能够支持两个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyALl来实现
 *
 * 使用Lock和Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的置顶哪些线程被唤醒
 *
 */
package ghaya.learn.courseThread.ghaya_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer2<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;//最多10个元素
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t){
        try {
            lock.lock();
            while(lists.size() == MAX){
                System.out.println("lists null");
                producer.await();//lock锁分组调用  生产者await
            }
            lists.add(t);
            count++;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public T get(){
        T t = null;
        try {
            lock.lock();
            while(lists.size() == 0){
                System.out.println("lists null");
                consumer.await();//lock锁分组调用  消费者await
            }
            t = lists.removeFirst();
            count --;
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        MyContainer2<String> c = new MyContainer2<>();
        //启动消费者线程
        for(int i=0;i<10;i++){
            new Thread(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(2001);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j=0;j<5;j++){
                    System.out.println("c:"+c.get());
                }
            },"c"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启用生产者线程
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<5;j++){
                    String tStr = Thread.currentThread().getName()+""+j;
                    c.put(tStr);
                    System.out.println("p:"+ tStr);
                }
            },"p"+i).start();
        }
    }
}

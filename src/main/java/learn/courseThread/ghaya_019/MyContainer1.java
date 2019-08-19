/**
 * 曾经的面试题：（淘宝）
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素个数，当个数到5个时，线程2给出提示并结束
 *
 * @author Ghaya
 */

package learn.courseThread.ghaya_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer1 {
    List lists = new ArrayList();  //此处没加volatile添加可见性t2线程可能访问不到

    public void add(Object o){
        lists.add(o);
    }

    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        MyContainer1 c = new MyContainer1();

        Thread t1 = new Thread(() -> {
           for(int i=0;i<10;i++){
               c.add(new Object());
               System.out.println(Thread.currentThread().getName() + " add " + i);
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        },"t1");
        t1.start();

        Thread t2 = new Thread(()->{
            while(true){
                if(c.size()==5){  //有延迟可能t1已经跑到6了   t2便会错过判断
                    break;
                }
            }
            System.out.println(Thread.currentThread().getName() + " End");
        },"t2");
        t2.start();

    }
}

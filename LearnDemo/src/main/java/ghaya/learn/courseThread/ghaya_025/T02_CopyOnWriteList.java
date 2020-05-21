/**
 * 写时复制容器 copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 *
 */

package ghaya.learn.courseThread.ghaya_025;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class T02_CopyOnWriteList {

    public static void main(String[] args) {
        List<String> lists =
//                new ArrayList<>();  //虽然效率高  但是有并发问题    140   99826
//                new Vector<>();//效率低一些  无并发问题  141  100000
        new CopyOnWriteArrayList<>();//适用于写少读多的业务 消耗也大  7984    100000  会把集合复制一份读取的时候读取那份复制的  读取方法不用加锁

        Random r = new Random();
        Thread[] ths = new Thread[100];

        for (int i = 0; i < ths.length; i++) {
            Runnable task = new Runnable(){
             @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        lists.add("a" + r.nextInt(10000));
                    }
                }
            };
            ths[i] = new Thread(task);
        }
        runAndComputeTime(ths);
        System.out.println(lists.size());
    }

    //读取数据的方法
    static void runAndComputeTime(Thread[] ths){
        long s1 = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t -> t.start());
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2-s1);
    }

}






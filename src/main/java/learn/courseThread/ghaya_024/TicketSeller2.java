/**
 *  有N张火车票，每张票都有一个编号
 *  同时有10个窗口对外售票
 *  请写一个模拟程序
 *
 *  分析下面的程序可能会产生哪些问题？
 *  重复销售？超量销售？
 *
 *  Vector同步容器
 *
 */

package learn.courseThread.ghaya_024;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller2 {
    static Vector<String> tickets = new Vector<>();

    static {
        for(int i=0;i<10000;i++){
            tickets.add("票编号："+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                //使用Vector 线程安全的集合
                //虽然remove原子性  但是判断与移除操作分离，依然可能出现问题
                while(tickets.size()>0){
                    //此处可能被打断
                    try {
                        TimeUnit.MICROSECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了--"+tickets.remove(0));
                }
            }).start();
        }
    }
}

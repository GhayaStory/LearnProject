package ghaya.learn.courseThread.ghaya_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 *  特殊的TransferQueue
 *  生产者直接给消费者
 */
public class T09_SynchronusQueue {//容量为0
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa");//阻塞等待消费者消费
//        strs.add("aaa");//容量为0不能用add
        System.out.println(strs.size());
    }
}

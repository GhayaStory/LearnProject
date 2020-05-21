package ghaya.learn.courseThread.ghaya_025;

import java.util.concurrent.LinkedTransferQueue;

/**
 *
 */
public class T08_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //该Queue提供的新的方法  更高并发的情况
        // 有消费者线程的时候 该方法会直接给消费者线程，不经过集合队列
        //找不到消费者时会阻塞
        strs.transfer("aaa");
        //strs.put("aaa");  put不会阻塞

        /*new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }
}

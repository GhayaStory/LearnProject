package learn.courseThread.ghaya_business;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 20个人去售票厅买票
 * 窗口2个
 * 控制同一时间并发数为2
 */
public class twentyTotwoDemo {

    /**
     * 执行任务类，获取信号量和释放信号量
     */
    class SemaphoreRunable implements Runnable {
        private Semaphore semaphore;//信号量
        private int user;//记录几个用户

        public SemaphoreRunable(Semaphore semaphore, int user) {
            this.semaphore = semaphore;
            this.user = user;
        }

        public void run() {
            try {
                semaphore.acquire();
                System.out.println("买票用户" + user + "开始买票");
                Thread.sleep((long) (Math.random() * 2000));

                System.out.println("用户" + user + "结束买票");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void execute() {
        //定义窗口个数
        final Semaphore semaphore = new Semaphore(2);
        //线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //模拟20个用户
        for (int i = 0; i < 20; i++) {
            threadPool.execute(new SemaphoreRunable(semaphore, (i + 1)));
        }
        threadPool.shutdown();
    }

    public static void main(String[] args) {
        twentyTotwoDemo ttt = new twentyTotwoDemo();
        ttt.execute();
    }

}

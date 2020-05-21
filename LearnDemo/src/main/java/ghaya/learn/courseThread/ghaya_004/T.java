/**
 * synchronized关键字
 * 对某个对象加锁
 * @author Ghaya
 */

package ghaya.learn.courseThread.ghaya_004;

public class T {
    private static int count = 10;

    public synchronized static void m(){//这里等同于synchronized(ghaya.learn.courseThread.ghaya_004.T.class)
        count--;
        System.out.println(Thread.currentThread().getName()+",count = " + count);
    }

    public static void mm(){
        synchronized (T.class){//考虑这里写synchronized(this)是否可以? 静态中不存在this的引用
            count--;
        }
    }

    public static void main(String[] args) {
        T t = new T();
        t.m();
        t.m();
        t.m();
    }
}

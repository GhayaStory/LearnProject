/**
 * synchronized关键字
 * 对某个对象加锁
 * @author Ghaya
 */

package ghaya.learn.courseThread.ghaya_003;

public class T {
    private int count = 10;

    public synchronized void m(){//等同于在方法的代码执行时要synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName()+",count = " + count);
    }

    public static void main(String[] args) {
        T t = new T();
        t.m();
        t.m();
        t.m();
        t.m();
        t.m();
    }
}

/**
 * synchronized关键字
 * 对某个对象加锁
 * @author Ghaya
 */

package learn.courseThread.ghaya_001;

public class T {
    private int count = 10;
    private Object o = new Object();

    public void m(){
        synchronized (o){//任何线程要执行下面代码,必须拿到0的锁
            count--;
            System.out.println(Thread.currentThread().getName()+",count = " + count);
        }
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

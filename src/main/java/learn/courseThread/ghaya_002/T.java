/**
 * synchronized关键字
 * 对某个对象加锁
 * @author Ghaya
 */

package learn.courseThread.ghaya_002;

public class T {
    private int count = 10;

    public void m(){
        synchronized (this){//任何线程要执行下面代码,必须拿到this的锁  锁定自身
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

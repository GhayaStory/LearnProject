package ghaya.learn.desginMode.singleton;


/**
 * 单例模式，多次调用只返回同一个实例
 */
//懒汉
public class Singleton {
    private Singleton() {}
    private static Singleton single=null;
    //静态工厂方法
    public static synchronized Singleton getInstance() {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }
}

//懒汉 （双重判断）
class Singleton0 {
    private Singleton0() {}
    private static volatile Singleton0 single=null;//volatile 要加（变量改变时通知其他线程） 指令重排问题
    //静态工厂方法（双重判断） 减少锁消耗
    public static Singleton0 getInstance() {
        if (single == null) {//必要性 减少锁消耗
            synchronized (Singleton.class){
                if (single == null) {
                    single = new Singleton0();
                }
            }
        }
        return single;
    }
}

/*
静态内部类
JVM保证单例  （虚拟机加载类的时候只加载一次）
加载外部类时不会加载内部类，这伴可以实现懒加载
 */

/*public*/ class Singleton1 {
    private static class LazyHolder {
        private static final Singleton1 INSTANCE = new Singleton1();
    }
    private Singleton1 (){}
    public static final Singleton1 getInstance() {
        return LazyHolder.INSTANCE;
    }
}

/*
饿汉式
类加我到内存后，就实例化一个单例，JVM保证线程安全
简单实用，推荐实用！
唯一缺点：不管用到与否，类装我时就完成实例化（不用不需要装载）

 */
/*public*/ class Singleton2 {
    private static final Singleton2 single = new Singleton2();
    private Singleton2() {}
    //静态工厂方法
    public static Singleton2 getInstance() {
        return single;
    }
}

/*
Effective Java
枚举单例
不禁可以解决线程同步，还可以防止反序列化
 */
enum Sinleton3{
    INSTANCE;
    public void m(){}

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Sinleton3.INSTANCE);
//                System.out.println(Sinleton3.INSTANCE.hashCode());
            }).start();
        }
    }
}

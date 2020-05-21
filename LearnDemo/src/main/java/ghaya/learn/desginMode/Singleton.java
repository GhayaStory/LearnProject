package ghaya.learn.desginMode;

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
//静态内部类
/*public*/ class Singleton1 {
    private static class LazyHolder {
        private static final Singleton1 INSTANCE = new Singleton1();
    }
    private Singleton1 (){}
    public static final Singleton1 getInstance() {
        return LazyHolder.INSTANCE;
    }
}

//饿汉式单例类.在类初始化时，已经自行实例化
/*public*/ class Singleton2 {
    private Singleton2() {}
    private static final Singleton2 single = new Singleton2();
    //静态工厂方法
    public static Singleton2 getInstance() {
        return single;
    }
}

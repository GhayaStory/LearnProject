package ghaya.learn.desginMode.proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;


/**
 * 马老师的动态代理
 */
public class Tank implements Movable {


    @Override
    public void move() {
        System.out.println("mmmmmmmmmmmmmmm");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Tank tank = new Tank();

//        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

        Movable m = (Movable) Proxy.newProxyInstance(Tank.class.getClassLoader(),//
                new Class[]{Movable.class},//实现哪个接口
                new TimeProxy(tank)//被代理对象调用时处理
        );

        m.move();
    }
}

class TimeProxy implements InvocationHandler {

    Movable m;

    public TimeProxy(Movable m) {
        this.m = m;
    }

    public void before() {
        System.out.println("start");
    }

    public void after() {
        System.out.println("end");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object o = method.invoke(m, args);
        after();
        return o;
    }
}

interface Movable {
    void move();
}

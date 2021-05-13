package ghaya.learn.courseReflection;


import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

//www.bilibili.com/video/av28591889
public class testReflection {
    //如何获取Class的实例（3种）
    public void test4() throws ClassNotFoundException {
        //1.调用运行时类的本身的.class属性
        Class<Person> c1 = Person.class;
        System.out.println(c1.getName());
        //2.通过运行时类对象获取
        Person person = new Person();
        Class<? extends Person> c2 = person.getClass();
        System.out.println(c2.getName());
        //3.通过Class的静态方法获取
        Class<?> c3 = Class.forName("ghaya.learn.courseReflection.Person");
        System.out.println(c3.getName());
        //4.通过类的加载器
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class<?> c4 = classLoader.loadClass("ghaya.learn.courseReflection.Person");
        System.out.println(c4.getName());

    }
    /**
     * java.lang.Class  反射的源头
     * 创建了一个类，通过编译（javac.exe），生成对应的.class文件，
     * 之后使用java.exe加载（JVM的类加载器完成）.class文件
     * 加载到内存之后，就是一个运行时类，存放在缓存区，那么这个运行时类本身就是Class实例
     * 1.每一个运行时类只加载一次
     * 2.有了Class实例之后，可以操作
     *  1）创建对应的运行时类的对象
     *  2）获取运行时类的完整结构（属性，方法，构造器，内部类，父类，所在包，异常，注解）
     *  3）调用对应的运行时类的置顶结构（属性，方法，构造器）
     *  4) 反射的应用：动态代理
     */
    @Test
    public void test3(){
        Person p = new Person();
        Class clazz = p.getClass();//通过运行时类的对象，调用getClass()返回其运行时类
        //获取类的属性，方法，构造器，父类，所在包，声明的异常，注解
        System.out.println(clazz);
    }

    //创建一个类的对象，并调用其中的方法，属性
    //反射
    @Test
    public void test2 () throws Exception{
        Person a = new Person();
        Class clazz = Person.class;//a.getClass();//
        //1.创建clazz对应的运行时Person类的对象
        Person p = (Person) clazz.newInstance();
        System.out.println(p);
        //2.通过反射调用运行时类的置顶的属性
        //2.1
        Field f1 = clazz.getField("name");//获取public name属性
        f1.set(p,"Ghaya");//设置name属性 不需要set方法
        //2.2
        Field f2 = clazz.getDeclaredField("age");//获取private age属性
        f2.setAccessible(true);//设置true可以非法访问
        f2.set(p,26);//设置age属性 不需要set方法
        System.out.println(p);
        //3.通过反射调用运行时类的指定对象方法
        Method m1 = clazz.getMethod("show");//获取方法
        m1.invoke(p);//启动方法
        Method m2 = clazz.getMethod("display",String.class);//获取方法  带参数
        m2.invoke(p,"Story");//启动方法



    }



    //非反射
    @Test
    public void test1(){
        Person p = new Person();
        p.setAge(26);
        p.setName("Ghaya");
        System.out.println(p);
        p.show();
        p.display("Story");
    }
}

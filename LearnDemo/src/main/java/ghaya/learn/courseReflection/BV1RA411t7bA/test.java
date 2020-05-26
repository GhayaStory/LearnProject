package ghaya.learn.courseReflection.BV1RA411t7bA;

import ghaya.entity.Dog;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 介绍反射
 * class相当于类的说明书
 * 当前进度： p3 （在此表示当前看到的p，b站合集没有记录）
 */
public class test {


    @Test
    public void test() throws Exception {
        //三种获得说明书的方式
        //1
        Class dogClass1 = Dog.class;
        //2
        Class dogClass2 = Class.forName("ghaya.entity.Dog");//需要处理异常
        Object dog2 = dogClass2.newInstance();

        //3
        Dog dog3 = new Dog();
        Class dogClass3 = dog3.getClass();


//        System.out.println(dogClass1);
//        System.out.println(dogClass2);
//        System.out.println(dogClass3);
//        System.out.println(dogClass1.getResource("").getPath());//路径
//        System.out.println(dogClass1.getSuperclass());//获取父类
//        System.out.println(dogClass1.getInterfaces()[0]);//获得接口
        //判断是不是接口
        //Dog dogNew = (Dog)dogClass2.newInstance();//实例化

        //方法调用
        Method eat = dogClass2.getMethod("eat");
        eat.invoke(dog2);

        Method eat2 = dogClass2.getMethod("eat" ,String.class);
        eat2.invoke(dog2,"牛肉");

        Method eat3 = dogClass2.getMethod("eat" ,String.class,int.class);
        eat3.invoke(dog2,"肌肉",3);

        //获取方法参数类型
        Class<?>[] parameterTypes = eat3.getParameterTypes();
        //parameterTypes.iter
        for (Class<?> parameterType : parameterTypes) {
            System.out.println(parameterType);
        }
        //...

        //获得构造器
        Constructor constructor = dogClass2.getConstructor(String.class, String.class);
        Object o = constructor.newInstance("a", "b");


    }
}

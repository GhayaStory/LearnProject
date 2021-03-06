package com.ghaya.springaop.proxy;

import com.ghaya.springaop.proxy.impl.TeacherImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 * 实现InvocationHandler接口
 */
public class DyProxy<T> implements InvocationHandler {
    private T target;

    public DyProxy(T target) {
        this.target = target;
    }

    public T getProxy() {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(), target.getClass().getInterfaces(), this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object rs = method.invoke(this.target, args);//业务方法
        after();
        return null;
    }


    void before() {
        System.out.println("before.....动态代理.......");
    }

    void after() {
        System.out.println("after......动态代理......");
    }

    public static void main(String[] args) {
        ITeacher teacher = new DyProxy<ITeacher>(new TeacherImpl()).getProxy();
        teacher.teach("动态代理");
    }

}

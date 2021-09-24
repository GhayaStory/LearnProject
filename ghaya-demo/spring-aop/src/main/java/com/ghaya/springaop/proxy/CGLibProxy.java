package com.ghaya.springaop.proxy;

import com.ghaya.springaop.proxy.impl.ProductImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxy<T> implements MethodInterceptor {

    private T target;

    public CGLibProxy(T target) {
        this.target = target;
    }

    public T getProxy() {
        return (T) Enhancer.create(this.target.getClass(), this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object rs = methodProxy.invokeSuper(o, objects);
        after();
        return rs;
    }

    void before() {
        System.out.println("before.....CGLibProxy.......");
    }

    void after() {
        System.out.println("after......CGLibProxy......");
    }

    public static void main(String[] args) {
        ProductImpl product = new CGLibProxy<ProductImpl>(new ProductImpl()).getProxy();
        product.show("CGLib动态代理");


    }
}

package com.ghaya.mybatis;

import com.ghaya.mybatis.pojo.Account;
import com.ghaya.mybatis.pojo.Blog;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * MetaObject mybatis提供的反射获取工具类
 * PropertyTokenizer  metaObject中
 *
 */
public class MetaObjectTest {

    @Test
    public void test1(){
        System.out.println("0--------------------");
        //装饰器
        Blog blog = new Blog();
        //已知该类型，可以对他进行直接setget操作
        blog.setTitle("Title!!!!AABC");
        System.out.println(blog.getTitle());
        System.out.println(blog);
        System.out.println("1--------------------");

        //mybatis在操作对象时并不知道是什么类型
        Object obj  = blog;
        //手动反射取值
        //根据配置可以知道一些属性和方法名
        Method getTitles = null;
        try {
            getTitles = obj.getClass().getDeclaredMethod("getTitle");
        } catch (NoSuchMethodException e) {//可能找不到方法
            e.printStackTrace();
        }
        try {
            Object invoke = getTitles.invoke(obj);
            System.out.println(invoke);
        } catch (IllegalAccessException e) {//没有访问权限的异常  private protected 等访问控制
            e.printStackTrace();
        } catch (InvocationTargetException e) {//当被调用的方法的内部抛出了异常而没有被捕获时，将由此异常接收
            e.printStackTrace();
        }
        System.out.println("2--------------------");


        //用metaObject反射取值
        Configuration configuration = new Configuration();
        MetaObject metaObject = configuration.newMetaObject(obj);
        //直接操作属性
        metaObject.setValue("id","111");
        System.out.println(metaObject.getValue("Id"));
        //操作子属性
        //自动创建属性对象
        metaObject.setValue("author.name","博客作者本人");//可以自动创建User对象
        //自动查找属性名，下划线转驼峰
        String phoneNumberKey = metaObject.findProperty("author.phone_number", true);//驼峰查找
        metaObject.setValue(phoneNumberKey,"驼峰查找属性名来的电话");
        System.out.println(metaObject.getValue("author"));
        //数组类型 设置  以及索引查找
        System.out.println("2--------------------数组");

        ArrayList<Object> objects = new ArrayList();
        objects.add(null);
        metaObject.setValue("author.account",objects);//需要手动创建
        System.out.println(metaObject.getValue("author.account"));
        metaObject.setValue("author.account[0]",new Account().setAccount("账号1"));
        System.out.println(metaObject.getValue("author.account[0].account"));
        //Map  一样的
        metaObject.setValue("author.map",new HashMap<>());
        metaObject.setValue("author.map[val1]","测试map1");
        metaObject.setValue("author.map[val2]",objects);
        System.out.println(metaObject.getValue("author"));


    }



    @Test
    public void test2(){
        Object obj  = new Blog();
        Configuration configuration = new Configuration();
        MetaObject metaObject = configuration.newMetaObject(obj);
        ArrayList<Object> objects = new ArrayList();
        objects.add(null);
        objects.add(null);
        objects.add(null);
        metaObject.setValue("author.account",objects);
        metaObject.setValue("author.account[0]",new Account().setAccount("账号1"));//缺少设置一个行
        metaObject.setValue("author.account[1]",new Account().setAccount("账号2"));//缺少设置一个行
        Object value = metaObject.getValue("author.account[0].account");
        System.out.println(value);
    }
}

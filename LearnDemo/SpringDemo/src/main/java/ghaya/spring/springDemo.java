package ghaya.spring;

import ghaya.spring.controller.UserController;
import ghaya.spring.service.UserService;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class springDemo {

    @Test
    public void test() throws Exception{
        //扫描注解
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        //创建对象
        UserService userService = new UserService();
        System.out.println(userService);
        //获取所有属性
        Field serviceField = clazz.getDeclaredField("userService");
        serviceField.setAccessible(true);//成员变量为private,故必须进行此操作
        //通过方法才能够设置具体得属性值
        String name = serviceField.getName();
        //拼接方法的名称
        name = name.substring(0,1).toUpperCase()+ name.substring(1,name.length());
        String setMethodName = "set" + name;
        //通过方法注入属性的对象
        Method method = clazz.getMethod(setMethodName, UserService.class);
        //反射
        method.invoke(userController,userService);
        System.out.println(userController.getUserService());

    }
}

package com.ghaya.springaop;

import com.ghaya.springaop.service.UserService;
import com.ghaya.springaop.proxy.CGLibProxy;
import com.ghaya.springaop.proxy.DyProxy;
import com.ghaya.springaop.proxy.ITeacher;
import com.ghaya.springaop.proxy.TeacherProxy;
import com.ghaya.springaop.proxy.impl.ProductImpl;
import com.ghaya.springaop.proxy.impl.TeacherImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAopApplicationTests {

    @Autowired
    UserService userService;




    @Test
    void contextLoads() {

        userService.funA();
        userService.funB();
        userService.funC();
    }

    //静态代理
    @Test
    void test01(){
        TeacherProxy teacherProxy = new TeacherProxy(new TeacherImpl());
        teacherProxy.teach("静态代理");
    }

    //动态代理
    @Test
    void test02(){
        ITeacher teacher = new DyProxy<ITeacher>(new TeacherImpl()).getProxy();
        teacher.teach("动态代理");
    }


    //CGLib动态代理
    @Test
    void test03(){
        ProductImpl product = new CGLibProxy<ProductImpl>(new ProductImpl()).getProxy();
        product.show("CGLib动态代理");
    }
}

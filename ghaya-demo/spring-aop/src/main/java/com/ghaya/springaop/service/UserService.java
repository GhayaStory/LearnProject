package com.ghaya.springaop.service;


import com.ghaya.springaop.aop.LogAnnotation;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbcUserService {

    public void funA() {
        super.before();
        System.out.println("AAAAAAAAAA");
        super.after("A");
    }

    @LogAnnotation(value = "funB", desc = "B方法")
    public void funB() {
        super.before();
        System.out.println("BBBBBBBBBBB");
        super.after("B");
    }

    public void funC() {
        super.before();
        System.out.println("CCCCCCCCC");
        super.after("C");
    }
}

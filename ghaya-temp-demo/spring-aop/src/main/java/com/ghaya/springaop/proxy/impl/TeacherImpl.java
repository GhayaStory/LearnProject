package com.ghaya.springaop.proxy.impl;

import com.ghaya.springaop.proxy.ITeacher;

public class TeacherImpl implements ITeacher {
    @Override
    public void teach(String name) {
        System.out.println("I am " + name);
    }

    public void teachEn() {
        System.out.println("teach Eng");
    }
}

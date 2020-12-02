package ghaya.proxy;

import ghaya.proxy.impl.TeacherImpl;

/**
 * 代理类
 */
public class TeacherProxy implements ITeacher{
    private TeacherImpl teacher;

    public TeacherProxy(TeacherImpl teacher) {
        this.teacher = teacher;
    }

    @Override
    public void teach(String name) {
        before();
        teacher.teach(name);
        after();
    }

    void before(){
        System.out.println("before............");
    }

    void after(){
        System.out.println("after............");
    }

}

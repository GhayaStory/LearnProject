package ghaya.service;

public abstract class AbcUserService {

    public void before(){
        System.out.println(this.getClass().getName());
    }

    public void after(String fun){
        System.out.println("fun" + fun);
    }
}

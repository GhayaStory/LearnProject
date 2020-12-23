package ghaya.注解的使用;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class cusAnnotaion {
    @myA2(name="123")
    public void test() {}


    @myA3("123")//value属性可以省略
    public void test2() {}
}


@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface myA2 {
    //注解的参数：参数类型 + 参数名();
    String name() default "";
    int age() default 0;
    int id() default -1;// -1代表不存在

    String[] schools() default {"1","2"};
}


@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface myA3 {
    //注解的参数：参数类型 + 参数名();
    String value() default "";
}



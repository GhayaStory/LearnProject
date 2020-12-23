package ghaya.注解的使用;


import java.lang.annotation.*;

/**
 *
 */
public class yuanAnnoation {

}



//表示注解可以用在哪些地方
@Target(value = {ElementType.METHOD,ElementType.TYPE})

//表示注解在什么阶段有效
@Retention(value=RetentionPolicy.RUNTIME)

//表示是否JavaDOC
@Documented

//说明子类可以继承该注解
@Inherited
@interface myA{

}

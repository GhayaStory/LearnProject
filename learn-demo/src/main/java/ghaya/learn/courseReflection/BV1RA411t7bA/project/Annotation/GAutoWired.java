package ghaya.learn.courseReflection.BV1RA411t7bA.project.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动注入的注解
 * @author zn
 * @date 2020/4/2
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GAutoWired {

}

package com.ghaya.springaop.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAOP {


    /**
     * 切点
     * (1)任意公共方法的执行：execution(public**(..))
     * (2)任何一个以“set”开始的方法的执行：execution(*set*(..))
     * (3)service的任意方法的执行：executio(*com.abc.service.*(.))
     */
//    @Pointcut("execution(* com.ghaya.springaop.service..*(..))")

    @Pointcut("@annotation(LogAnnotation)")
    public void pointcut(){

    }
//    @Before("pointcut()")
//    @Before("execution(* com.ghaya.springaop.service..*(..))")
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        System.out.println("before------------Log");
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();//方法签名
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = methodSignature.getName();
//        System.out.println(className+"----"+methodName);

        //接收注解参数
        LogAnnotation logAnnotation = methodSignature.getMethod()
                .getDeclaredAnnotation(LogAnnotation.class);
        System.out.println(logAnnotation.toString());




    }

    @Around("pointcut()")
    public Object aroud(ProceedingJoinPoint joinPoint) throws Throwable {

        //手动执行业务方法
        System.out.println("aroud");
        Object result = joinPoint.proceed();
        return result;

    }




}

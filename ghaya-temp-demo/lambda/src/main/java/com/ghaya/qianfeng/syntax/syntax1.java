package com.ghaya.qianfeng.syntax;

import com.ghaya.qianfeng.interfaces.LambdaNoneReturnNoneParameter;
import com.ghaya.qianfeng.interfaces.LambdaNoneReturnSingleParameter;
import com.ghaya.qianfeng.interfaces.LambdaSingleReturnNoneParameter;

/**
 * 基础用法
 */
public class syntax1 {
    public static void main(String[] args) {
        //1. Lambda表达式的基础语法：
        //Lambda是一个匿名函数
        //返回值类型 参数列表 方法体

        // ()：用来描述参数列表
        // {}: 用来描述方法体
        // ->: Lambda运算符，读作goes to

        //无返回值，无参数
        LambdaNoneReturnNoneParameter lambda1 = () -> {
            System.out.println("lambda1");
        };
        lambda1.test();
        //无返回值，单个参数
        LambdaNoneReturnSingleParameter lambda2 = (int a) -> {
            System.out.println(a);
        };

        //无返回值，多参数
        //...
        //有返回值，无参数
        LambdaSingleReturnNoneParameter lambda4 = () -> {
          System.out.println(100);
          return 100 ;
        };
        //有返回值，单个参数
        //...
        //有返回值，多个参数
        //...


    }
}

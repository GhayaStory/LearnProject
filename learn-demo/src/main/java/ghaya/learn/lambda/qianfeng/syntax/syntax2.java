package ghaya.learn.lambda.qianfeng.syntax;

import ghaya.learn.lambda.qianfeng.interfaces.LambdaNoneReturnMutipleParameter;
import ghaya.learn.lambda.qianfeng.interfaces.LambdaNoneReturnSingleParameter;
import ghaya.learn.lambda.qianfeng.interfaces.LambdaSingleReturnMutipleParameter;
import ghaya.learn.lambda.qianfeng.interfaces.LambdaSingleReturnSingleParameter;

/**
 * 精简方法
 */
public class syntax2 {
    public static void main(String[] args) {
        //语法精简：
        //1. 参数类型：
        //由于在接口的抽象方法中，已经定义了参数的数量类型
        //参数类型可以省略
        LambdaNoneReturnMutipleParameter lambda1 = (a, b) -> {

        };
        //2. 参数小括号
        //参数只有一个，小括号可以省略
        LambdaNoneReturnSingleParameter lambda2 = a -> {
            System.out.println(a);
        };

        //3. 方法大括号：
        //只有一条语句，大括号可以省略
        LambdaNoneReturnSingleParameter lambda3 = a -> System.out.println(a);
        //4. 有返回值
        LambdaSingleReturnSingleParameter lambda4 = a -> a;

        LambdaSingleReturnMutipleParameter lambda5 = (a, b) -> a + b;

    }
}

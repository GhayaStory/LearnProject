package ghaya.learn.lambda.qianfeng.syntax;


import ghaya.learn.lambda.qianfeng.interfaces.LambdaSingleReturnSingleParameter;

/**
 * 进阶
 */
public class syntax3 {
    public static void main(String[] args) {
        //方法引用
        //快速指向一个已经实现的方法
        //语法： 方法的隶属者::方法名
        LambdaSingleReturnSingleParameter lambda1 = a -> change(a);
        //静态方法引用
        //参数数量和类型一定要和接口中定义的方法保持一致
        LambdaSingleReturnSingleParameter lambda2 = syntax3::change;

    }

    private static int change(int a) {
        return a * 2;
    }
}

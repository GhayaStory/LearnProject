package learn.lambda.qianfeng.interfaces;


/*修饰函数式接口，接口中的抽象方法只有一个   假如你写多了 会提示报错*/
@FunctionalInterface
public interface LambdaSingleReturnNoneParameter {
    int test();
}

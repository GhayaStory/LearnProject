package ghaya.learn.lambda.qianfeng.closure;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ClosureDemo {
    public static void main(String[] args) {
        //闭包
        //闭包中引用的一定是常量，无法修改该值
        int n = getNumber().get();
        System.out.println(n);



        Consumer<Integer> c = ele -> {
            System.out.println(ele);
        };
        c.accept(1);
    }

    private static Supplier<Integer> getNumber(){
        int num = 10;
        return () -> num;
    }
}

package learn.lambda.qianfeng.functional;

import java.util.function.*;

public class FunctionalInterface {
    public static void main(String[] args) {
        //系统内置的一些函数式接口
        //常用
        //Predicate<T>      :   参数T     返回值     boolean
        //  IntPrdicate int -> boolean
        //  LongPrdicate loong -> boolean
        //  DoublePrdicate double -> boolean

        //Consumer<T>       :   参数T     返回值     void
        //  IntConsumer int -> void
        //  LongConsumer loong -> void
        //  DoubleConsumer double -> void

        //Function<T,R>     :   参数T     返回值     R
        //  IntFunction<R> int -> R
        //  LongFunction<R> loong -> R
        //  DoubleFunction<R> double -> R
        //  IntToLongFunction<R> int -> long
        //...

        //Supplier<T>       :   参数无    返回值     T

        //其他
        //UnaryOperator<T>  :   参数T     返回值     T
        //BinaryOperator<T> :   参数T,T   返回值     T
        //BiFunction<T,U,R> :   参数T,U   返回值     R
        //BiPredicate<T,U>  :   参数T,U   返回值     boolean
        //BiConsumer<T,U>   :   参数T,U   返回值     void

    }
}

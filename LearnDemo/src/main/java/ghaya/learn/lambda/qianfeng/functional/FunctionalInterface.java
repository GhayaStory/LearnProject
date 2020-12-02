package ghaya.learn.lambda.qianfeng.functional;

import java.util.function.*;

public class FunctionalInterface {
    public static void main(String[] args) {
        //系统内置的一些函数式接口
        //常用
        //Predicate<T01_NormalReference>      :   参数T     返回值     boolean
        //  IntPrdicate int -> boolean
        //  LongPrdicate loong -> boolean
        //  DoublePrdicate double -> boolean

        //Consumer<T01_NormalReference>       :   参数T     返回值     void
        //  IntConsumer int -> void
        //  LongConsumer loong -> void
        //  DoubleConsumer double -> void

        //Function<T01_NormalReference,R>     :   参数T     返回值     R
        //  IntFunction<R> int -> R
        //  LongFunction<R> loong -> R
        //  DoubleFunction<R> double -> R
        //  IntToLongFunction<R> int -> long
        //...

        //Supplier<T01_NormalReference>       :   参数无    返回值     T01_NormalReference

        //其他
        //UnaryOperator<T01_NormalReference>  :   参数T     返回值     T01_NormalReference
        //BinaryOperator<T01_NormalReference> :   参数T,T01_NormalReference   返回值     T01_NormalReference
        //BiFunction<T01_NormalReference,U,R> :   参数T,U   返回值     R
        //BiPredicate<T01_NormalReference,U>  :   参数T,U   返回值     boolean
        //BiConsumer<T01_NormalReference,U>   :   参数T,U   返回值     void

    }
}

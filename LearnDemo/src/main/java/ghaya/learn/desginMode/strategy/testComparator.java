package ghaya.learn.desginMode.strategy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Comparator<T> 的使用方法
 * Comparator 比较器
 */
public class testComparator{
    public static void main(String[] args) {
        Dog[] arr = {new Dog(3),new Dog(5),new Dog(1)};
        Sorter<Dog> sorter = new Sorter<>();
//        sorter.sort(arr,new DogSizeComparator());
        sorter.sort(arr,(o1,o2)->{
            if (o1.size < o2.size) return -1;
            else if (o1.size > o2.size) return 1;
            return 0;
        });
        System.out.println(Arrays.toString(arr));
    }
}


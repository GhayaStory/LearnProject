package ghaya.learn.lambda.BV1sE411P7C1.demo;


import ghaya.learn.lambda.BV1sE411P7C1.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream的状态与并行操作
 *
 */
public class StreamDemo4 {


    /**
     *  并行操作
     *  不分顺序，要区分有无状态操作
     *  无状态操作，只和当前元素有关，不需要其他元素参与
     *  有状态操作，反之
     *
     *
     */
    @Test
    public void demo1(){
        Stream.of("1Monkey", "2Lion", "3Giraffe", "4Lemur", "5Lion")
                .parallel()
                .forEach(System.out::println);

        List<String> n = Arrays.asList("1", "2", "3", "4", "5","6", "7");
        n.parallelStream().forEach(System.out::println);

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        long count = strings.parallelStream().filter(string -> string.isEmpty()).count();


    }

    /**
     *
     */
    public void demo2(){
    }


    /**
     *
     */
    public void demo3() {

    }
}

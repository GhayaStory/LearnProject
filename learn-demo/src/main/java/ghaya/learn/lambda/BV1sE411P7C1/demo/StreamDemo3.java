package ghaya.learn.lambda.BV1sE411P7C1.demo;


import ghaya.learn.lambda.BV1sE411P7C1.entity.Employee;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream Map
 *
 */
public class StreamDemo3 {

    public static void main(String[] args) {

        //new StreamDemo3().demo1();
        //new StreamDemo3().demo2();
        //new StreamDemo3().demo3();


        //--------------------------------------------------------------------------

//        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//        // 获取对应的平方数
//        List<HashMap> number = numbers.stream().map(i -> {
//            HashMap hashMap = new HashMap();
//            hashMap.put("number", i * i);
//            return hashMap;
//        }).distinct().collect(Collectors.toList());
//        System.out.println(number);
//
//
//        List<HashMap> text = number.stream().peek(e -> e.put("text", e.get("number") + "元")).collect(Collectors.toList());
//        System.out.println(text);

    }


    /**
     * 用于数据格式转换
     */
    @Test
    public void demo1(){
        List<String> alpha = Arrays.asList("Monkey", "Lion", "Giraffe", "Lemur");

        //不使用Stream管道流
        List<String> alphaUpper = new ArrayList<>();
        for (String s : alpha) {
            alphaUpper.add(s.toUpperCase());
        }

        System.out.println(alphaUpper); //[MONKEY, LION, GIRAFFE, LEMUR]


        AtomicInteger i = new AtomicInteger(1);
        AtomicReference<String> str = new AtomicReference<>("123");
        // 使用Stream管道流
        List<HashMap> key = alpha.stream().map(s -> {
            HashMap<Object, Object> map = new HashMap<>();
            str.set("222");
            map.put("key", s.toUpperCase());
            map.put("count", i.getAndIncrement());
            return map;
        }).collect(Collectors.toList());
//        List<String> collect = key;
        //上面使用了方法引用，和下面的lambda表达式语法效果是一样的
        //List<String> collect = alpha.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());

        System.out.println(key); //[MONKEY, LION, GIRAFFE, LEMUR]



        //map()函数不仅可以处理数据，还可以转换数据的类型。如下：
        List<Integer> lengths = alpha.stream()
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println(lengths); //[6, 4, 7, 5]
        Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .mapToInt(String::length)
                .forEach(System.out::println);

    }

    /**     *
     * 将每一个Employee的年龄增加一岁
     * 将性别中的“M”换成“male”，F换成Female。
     */
    public void demo2(){
        Employee e1 = new Employee(1,23,"M","Rick","Beethovan");
        Employee e2 = new Employee(2,13,"F","Martina","Hengis");
        Employee e3 = new Employee(3,43,"M","Ricky","Martin");
        Employee e4 = new Employee(4,26,"M","Jon","Lowman");
        Employee e5 = new Employee(5,19,"F","Cristine","Maria");
        Employee e6 = new Employee(6,15,"M","David","Feezor");
        Employee e7 = new Employee(7,68,"F","Melissa","Roy");
        Employee e8 = new Employee(8,79,"M","Alex","Gussin");
        Employee e9 = new Employee(9,15,"F","Neetu","Singh");
        Employee e10 = new Employee(10,45,"M","Naveen","Jain");

        List<Employee> employees = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);

    /*List<Employee> maped = employees.stream()
            .map(e -> {
                e.setAge(e.getAge() + 1);
                e.setGender(e.getGender().equals("M")?"male":"female");
                return e;
            }).collect(Collectors.toList());*/

        List<Employee> maped = employees.stream()
                .peek(e -> {
                    e.setAge(e.getAge() + 1);
                    e.setGender(e.getGender().equals("M")?"male":"female");
                }).collect(Collectors.toList());

        System.out.println(maped);
    }


    /**
     * flatMap多维数组处理
     */
    public void demo3(){
        List<String> words = Arrays.asList("hello", "word");
        words.stream()
                .map(w -> Arrays.stream(w.split("")))    //[[h,e,l,l,o],[w,o,r,l,d]]
                .forEach(System.out::println);


        words.stream()
                .flatMap(w -> Arrays.stream(w.split(""))) // [h,e,l,l,o,w,o,r,l,d]
                .forEach(System.out::println);
    }
}

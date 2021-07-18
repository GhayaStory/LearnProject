package ghaya.learn.lambda.BV1sE411P7C1.demo;


import ghaya.learn.lambda.BV1sE411P7C1.entity.Employee;
import org.junit.Test;

import java.util.*;

/**
 * 集合排序
 *
 */
public class StreamDemo6 {


    /**
     * 字符串List
     */
    @Test
    public void demo1() {


        List<String> cities = Arrays.asList(
                "Milan",
                "london",
                "San Francisco",
                "Tokyo",
                "New Delhi"
        );

//        Collections.sort(cities);
//        Collections.sort(cities,new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                // 返回值为int类型，大于0表示正序，小于0表示逆序
//                return o1.length()-o2.length();
//            }
//        });

        System.out.println(cities);
        //[Milan, london, San Francisco, Tokyo, New Delhi]
        //自带了一些排序规则
        cities.sort(String.CASE_INSENSITIVE_ORDER);//大小写不敏感
        System.out.println(cities);
        //[london, Milan, New Delhi, San Francisco, Tokyo]

        cities.sort(Comparator.naturalOrder());//自然顺序
        System.out.println(cities);
        //[Milan, New Delhi, San Francisco, Tokyo, london]

    }

    /**
     * 对象List
     */
    @Test
    public void demo2() {
        //select * from emp order by age desc;


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

        System.out.println("原顺------------------");
        employees.forEach(System.out::println);

        //如果我们希望List按照年龄age的倒序排序
        //
        employees.sort(Comparator.comparing(Employee::getAge).reversed());
        System.out.println("倒序------------------");
        employees.forEach(System.out::println);


        //都是正序 ，不加reversed
        //都是倒序，最后面加一个reserved
        //先是倒序（加reserved），然后正序
        //先是正序（加reserved），然后倒序（加reserved）
        employees.sort(
                Comparator.comparing(Employee::getGender)//性别
                        .thenComparing(Employee::getAge)//年龄
                        .reversed());
        System.out.println("性别 年龄倒序------------------");
        employees.forEach(System.out::println);

    }


    /**
     *
     */
    public void demo3() {

    }

}

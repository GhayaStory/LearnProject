package ghaya.learn.lambda.qianfeng.exercise;



import ghaya.learn.lambda.qianfeng.data.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.TreeSet;

public class Exercise1 {

    //集合牌型
    //ArrayList<>
    public static void main(String[] args) {
        lambdaDemoThread();
    }
    public static void lambdaDemoThread(){
        //线程
        Thread thread = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
        });
        thread.start();
    }
    public static void lambdaDemoList(){
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("bag2qgaw",10));
        list.add(new Person("mljdfkg2qgaw",25));
        list.add(new Person("plpl2qgaw",43));
        list.add(new Person("boo2qgaw",34));
        list.add(new Person("e3tqgaw",25));
        list.add(new Person("abna2qgaw",79));
        list.add(new Person("grgeqgaw",18));
        list.add(new Person("wqrwgaw",45));
        list.add(new Person("basdfgaw",15));

        list.sort((o1,o2)-> o2.age-o1.age);
        System.out.println(list);


    }
    public static void lambdaDemoSet(){
        //TreeSet
        //需要实现Comparator接口，并实例化一个TreeSet对象
//        TreeSet<Person> set = new TreeSet<>();
        //会根据实现的方法排序并且去重
//        TreeSet<Person> set = new TreeSet<>((o1,o2)->o2.age-o1.age);
        //不要去重的
        TreeSet<Person> set = new TreeSet<>((o1, o2)->{
            if(o1.age >= o2.age){
                return -1;
            }else{
                return 1;//只要不返回0就不会去重
            }
        });


        set.add(new Person("bag2qgaw",10));
        set.add(new Person("mljdfkg2qgaw",25));
        set.add(new Person("plpl2qgaw",15));
        set.add(new Person("boo2qgaw",34));
        set.add(new Person("e3tqgaw",25));
        set.add(new Person("abna2qgaw",79));
        set.add(new Person("grgeqgaw",18));
        set.add(new Person("wqrwgaw",45));
        set.add(new Person("basdfgaw",15));

        System.out.println(set);
    }
    public static void lambdaDemoRemoveIf(){

        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("bag2qgaw",10));
        list.add(new Person("mljdfkg2qgaw",25));
        list.add(new Person("plpl2qgaw",43));
        list.add(new Person("boo2qgaw",34));
        list.add(new Person("e3tqgaw",25));
        list.add(new Person("abna2qgaw",79));
        list.add(new Person("grgeqgaw",18));
        list.add(new Person("wqrwgaw",45));
        list.add(new Person("basdfgaw",15));

        //删除集合中年龄>10岁的元素
        /*
        ListIterator<Person> it = list.listIterator();
        while(it.hasNext()){
            Person ele = it.next();
            if (ele.age>20){
                it.remove();
            }
        }
        */

        //Lambda实现
        list.removeIf(ele->ele.age>20);


        System.out.println(list);
    }
    public static void lambdaDemoForEach(){
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list,1,2,3,4,5,6,7,8,9,0);

        //将集合中的每一个元素都带入到方法accept中
        list.forEach(System.out::println);

        //输出集合中的所有的偶数
        list.forEach(ele->{
            if(ele%2==0){
                System.out.println(ele);
            }
        });
    }
}

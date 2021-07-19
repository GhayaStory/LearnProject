package ghaya.learn.lambda.BV1sE411P7C1.demo;


import ghaya.learn.lambda.BV1sE411P7C1.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream Api
 * 用filter 筛选list集合demo
 */
public class StreamDemo2 {

    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Name1",18,"M","地址","电话1"));
        users.add(new User("Name2",999,"W","地址","电话2"));
        users.add(new User("Name3",30,"W","地址","电话3"));
        users.add(new User("Name4",40,"M","地址","电话4"));
        users.add(new User("Name5",72,"M","地址","电话5"));
        users.add(new User("Name6",75,"W","地址","电话6"));
        users.add(new User("Name7",70,"M","地址","电话7"));

        /*
        List<User> collect = users.stream()
                .filter(x -> x.getAge() > 70 && x.getGender().equals("M"))  //age大于70  男性
                .collect(Collectors.toList());
                */


        //谓词逻辑   就是将主体进行条件筛选

        //谓词条件
        List<User> collect = users.stream()
//                .filter(User.ageDayu70.and(User.genderM))
                .filter(
                        User.ageDayu70
                        .or(User.genderM)
                        .negate()//取反  不大于70  不是男性
                )

                .collect(Collectors.toList());
        for (User user : collect) {
            System.out.println(user);
        }


    }

}

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
        /*
        List<User> collect = users.stream()
                .filter(x -> x.getAge() > 70 && x.getGender().equals("M"))  //age大于70  男性
                .collect(Collectors.toList());
                */


        //谓词条件
        List<User> collect = users.stream()
//                .filter(User.ageDayu70.and(User.genderM))
                .filter(
                        User.ageDayu70
                        .or(User.genderM)
                        .negate()//取反  不大于70  不是男性
                )

                .collect(Collectors.toList());




    }

}

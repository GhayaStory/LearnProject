package ghaya.learn.lambda.BV1sE411P7C1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
public class User {
    private String name;
    private Integer age;
    private String gender;
    private String address;
    private String telephone;

    //谓词条件
    public static Predicate<User> ageDayu70 = x -> x.getAge() > 70;
    public static Predicate<User> genderM = x -> x.getGender().equals("M");

}



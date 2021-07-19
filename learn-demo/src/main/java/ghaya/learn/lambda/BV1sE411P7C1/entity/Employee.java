package ghaya.learn.lambda.BV1sE411P7C1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {

    private Integer id;
    private Integer age;   //年龄
    private String gender;  //性别
    private String firstName;
    private String lastName;
}

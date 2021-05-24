package com.ghaya.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private int id;  //id
    private String name;   //姓名
    private Integer age;   //年龄
    private String email;   //邮箱


}

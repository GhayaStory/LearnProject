package com.ghaya.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private int id;  //id
    private String name;   //姓名
    private Integer age;   //年龄
    private String email;   //邮箱
    private String phoneNumber; //电话
    private List<Account> account;  //账号
    private Map map;  //


}

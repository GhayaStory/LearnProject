package com.ghaya.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDesc implements Serializable {

    private int id;  //id
    private Integer userId;   //姓名
    private String desc;   //邮箱


}

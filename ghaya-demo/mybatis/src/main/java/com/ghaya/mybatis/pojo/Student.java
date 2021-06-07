package com.ghaya.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  private long id;
  private String name;
  private String sex;
  private String phone;
  private String brithplace;
  private long grade;


}

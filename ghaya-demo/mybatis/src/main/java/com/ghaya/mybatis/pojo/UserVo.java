package com.ghaya.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends User {

    private String userId;
    private UserDesc userDesc;
    private String desc;
}

package com.ghaya.mybatis.pojo;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Account {
    public String nickname;
    public String account;
    public String password;
}

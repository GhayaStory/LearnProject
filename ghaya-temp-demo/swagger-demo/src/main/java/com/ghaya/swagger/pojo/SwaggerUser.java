package com.ghaya.swagger.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("sw用户")
public class SwaggerUser {

    @ApiModelProperty("用户名")
    public String name;
    @ApiModelProperty("密码")
    public String pw;

    public String getName() {
        return name;
    }

    public SwaggerUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getPw() {
        return pw;
    }

    public SwaggerUser setPw(String pw) {
        this.pw = pw;
        return this;
    }
}

package com.ghaya.swagger.controller;


import com.alibaba.fastjson.JSONObject;
import com.common.entity.UserInfo;
import com.ghaya.swagger.pojo.SwaggerUser;
import com.ghaya.swagger.utils.CloseableHttpClientToInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;


@RestController
public class HelloController {


    // /error
    // /hello
    @GetMapping("hello")
    @ApiOperation("hello方法")
    public String hello() {
        return "hello222";
    }


    @PostMapping("user")
    public SwaggerUser user() {
        return user2("默认用户名","默认密码");
    }

    @GetMapping("user2")
    public SwaggerUser user2(@RequestParam @ApiParam(name = "username",value="输入用户名") String username,
                             @RequestParam @ApiParam(name = "pw",value="输入密码") String pw) {
        System.out.println(username);
        return new SwaggerUser().setName(username+"啊啊啊").setPw( pw + "啊啊啊");
    }

    @PostMapping("user3")
    public SwaggerUser user3(@ApiParam("用户") SwaggerUser user) {
        return user;
    }

    @PostMapping("ht")
    @ApiOperation("hello方法")
    public String testHttp(String name){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setUser_id(1);
        userInfo.setNicoName("Name");
        userInfo.setAddress("dizhi");
        userInfo.setCreTime(new Date());
        userInfo.setCre("cre");
        userInfo.setModiTime(new Date());
        userInfo.setModi("modi");
        userInfo.setOther1("other");
        userInfo.setTime(new Date());
        String s = CloseableHttpClientToInterface.doPost("http://localhost:8081/testHttp", (JSONObject) JSONObject.toJSON(userInfo));
        System.out.println(s);
        return s;

    }



}

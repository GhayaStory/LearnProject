package com.ghaya.springaop.controller;

import com.common.entity.UserInfo;
import com.ghaya.springaop.common.ResultCode;
import com.ghaya.springaop.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 异常捕获测试
 */
@RestController
public class testController {

    @GetMapping("/hello")
    public String hello() {
        return "hello laowan!";
    }

    @GetMapping("/testGet")
    public String testGet(String name) throws Exception {
        if (name == null) {
            throw new BusinessException(ResultCode.PAPAM_IS_BLANK);
        }
        return "laowan!";
    }

    @PostMapping("/testPost")
    public String testPost() {
        return "post laowan!";
    }


    @PostMapping("/testHttp")
    public HashMap<String, Object> testHttp(UserInfo userInfo) {
        System.out.println(userInfo);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "10000");
        map.put("msg", "操作成功");

        return map;
    }


}

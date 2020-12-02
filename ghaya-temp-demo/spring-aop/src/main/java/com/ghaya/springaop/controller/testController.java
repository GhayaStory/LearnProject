package com.ghaya.springaop.controller;

import com.ghaya.springaop.common.ResultCode;
import com.ghaya.springaop.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 异常捕获测试
 */
@RestController
public class testController {

    @GetMapping("/hello")
    public String hello(){
        return "hello laowan!";
    }

    @GetMapping("/testGet")
    public String testGet(String name) throws Exception {
        if (name==null) {
            throw new BusinessException(ResultCode.PAPAM_IS_BLANK);
        }
        return "laowan!";
    }

    @PostMapping("/testPost")
    public String testPost(){
        return "post laowan!";
    }
}

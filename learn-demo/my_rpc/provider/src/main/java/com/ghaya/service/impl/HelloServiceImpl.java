package com.ghaya.service.impl;

import com.ghaya.service.HelloService;

import java.net.URL;
import java.util.Map;


public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return name+"调用了myRPC的服务";
    }

}

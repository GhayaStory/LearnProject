package com.ghaya.provider;

import com.ghaya.pojo.URL;
import com.ghaya.registry.NativeRegistry;
import com.ghaya.service.HelloService;
import com.ghaya.service.impl.HelloServiceImpl;
import com.ghaya.tomcat.HttpServer;

public class ServiceProvider {
    public static void main(String[] args) {
        //真正的注册服务  Ctrl+Q 查看方法参数
        URL url = new URL("localhost", 8080);
        NativeRegistry.regist(HelloService.class.getName(),url,HelloServiceImpl.class);

        //启动tomcat 暴露服务
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(),url.getPort());


    }
}

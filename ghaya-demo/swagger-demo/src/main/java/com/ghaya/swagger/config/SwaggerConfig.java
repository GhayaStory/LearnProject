package com.ghaya.swagger.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2    //开启swagger2
public class SwaggerConfig {
    //http://localhost:8080/swagger-ui.html

    //配置swagger的 Docket属性
    //这个对象负责配置一些信息，具体看docket对象
    @Bean
    public Docket docket(Environment environment) {

        //环境配置
        boolean ifStart = environment.acceptsProfiles(Profiles.of("dev", "test"));
        System.out.println(ifStart);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(ifStart)//是否启用swagger
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
//                .apis(RequestHandlerSelectors.basePackage("com.kuang.swagger.controller"))//包扫描
//                .apis(RequestHandlerSelectors.any())//扫描全部
//                .apis(RequestHandlerSelectors.none())//不扫描
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))//类注解扫描
//                .apis(RequestHandlerSelectors.withMethodAnnotation(GetMapping.class))//方法注解扫描
//                .paths(PathSelectors.ant("/ghaya/**"))//过滤  正则
                .build();
    }

    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("联系人名字", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱");
        return new ApiInfo(
                "Swagger学习", // 标题
                "学习演示如何配置Swagger", // 描述
                "v1.0", // 版本
                "http://terms.service.url/组织链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}

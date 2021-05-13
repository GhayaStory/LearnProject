package com.ghaya.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 20201021 出问题了  有空再修
 */
@SpringBootApplication
//加载mybatis的mapper借口所在的路径
@MapperScan("com.ghaya.mybatisplus.dao")
//@MapperScan(basePackages = {"com.ghaya.mybatisplus.dao","com.ghaya.mybatisplus.dao2"})//扫描多个包
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

}

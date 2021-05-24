package com.ghaya.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//加载mybatis的mapper借口所在的路径  不扫描要每个dao上加@Mapper注解
//@MapperScan("com.ghaya.mybatis.dao")
public class MybatisApplication {
	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class,args);
	}
}

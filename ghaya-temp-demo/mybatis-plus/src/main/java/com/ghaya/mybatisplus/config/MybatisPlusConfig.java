package com.ghaya.mybatisplus.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//标记该类是一个配置类
@Configuration
//开启事务管理
@EnableTransactionManagement
//加载mapper接口所在的包
@MapperScan("com.ghaya.mybatisplus.dao")
public class MybatisPlusConfig {


    /**
     * 注入分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }


    //<bean id="paginationInterceptor" class="com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor"/>
}

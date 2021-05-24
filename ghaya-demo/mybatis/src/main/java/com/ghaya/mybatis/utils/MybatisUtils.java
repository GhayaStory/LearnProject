package com.ghaya.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
//
//    private static SqlSessionFactory sqlSessionFactory;
//
//    static {
//        System.out.println("000");
//        try {
//            String resource = "mybatis-config.xml";
//            InputStream inputStream = Resources.getResourceAsStream(resource);
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    //获取SqlSession连接
//    public static SqlSession getSession() {
//        return sqlSessionFactory.openSession();
//    }
//
//    public static void main(String[] args) {
//        new MybatisUtils();
//    }

}
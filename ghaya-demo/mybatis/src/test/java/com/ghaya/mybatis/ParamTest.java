package com.ghaya.mybatis;

import com.ghaya.mybatis.dao.UserDao;
import com.ghaya.mybatis.pojo.User;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class ParamTest {


    private Configuration configuration;
    private Connection connection;
    private JdbcTransaction jdbcTransaction;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;

    @Before
    public void init() throws SQLException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        factory = sqlSessionFactoryBuilder.build(ExecutorTest.class.getResourceAsStream("/mybatis-config.xml"));
        configuration = factory.getConfiguration();
//		SqlSession sqlSession = sqlSessionFactory.openSession(false);//自动提交
        sqlSession = factory.openSession(true);//自动提交
        connection = sqlSession.getConnection();
        jdbcTransaction = new JdbcTransaction(connection);

    }


    @Test
    public void test1() throws SQLException {
        UserDao mapper = sqlSession.getMapper(UserDao.class);
//        User user2 = mapper.selectByEveryThing2(3);
//        System.out.println(user2);
        HashMap hashMap = new HashMap();
        hashMap.put("this is key","这是值");
        User user3 = mapper.selectByEveryThing3("1","2",hashMap);
        System.out.println(user3);
//        User user4 = mapper.selectByEveryThing4("1","2",null);
//        System.out.println(user4);
    }
}

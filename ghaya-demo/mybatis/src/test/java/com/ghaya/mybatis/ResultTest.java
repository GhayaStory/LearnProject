package com.ghaya.mybatis;

import com.ghaya.mybatis.dao.StudentDao;
import com.ghaya.mybatis.dao.UserDao;
import com.ghaya.mybatis.pojo.Student;
import com.ghaya.mybatis.pojo.User;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 结果集
 */
@MapperScan("com.ghaya.mybatis.dao")
public class ResultTest {

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


}

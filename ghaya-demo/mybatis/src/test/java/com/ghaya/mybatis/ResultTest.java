package com.ghaya.mybatis;

import com.ghaya.mybatis.dao.StudentDao;
import com.ghaya.mybatis.dao.UserDao;
import com.ghaya.mybatis.pojo.Student;
import com.ghaya.mybatis.pojo.User;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        sqlSession = factory.openSession(true);//自动提交

    }

    @After
    public void over(){
        sqlSession.close();
    }

    @Test
    public void test(){
        List<Object> list = new ArrayList<>();
        ResultHandler handler = new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
                if(resultContext.getResultCount()>=3){
                    resultContext.stop();
                }
                list.add(resultContext.getResultObject());
            }
        };
        sqlSession.select("com.ghaya.mybatis.dao.UserDao.listUser",handler);
        System.out.println(list);
    }



}

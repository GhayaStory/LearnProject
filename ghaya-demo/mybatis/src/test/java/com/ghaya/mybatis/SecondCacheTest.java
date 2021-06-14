package com.ghaya.mybatis;

import com.ghaya.mybatis.dao.StudentDao;
import com.ghaya.mybatis.dao.UserDao;
import com.ghaya.mybatis.pojo.Student;
import com.ghaya.mybatis.pojo.User;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 二级缓存
 */
@MapperScan("com.ghaya.mybatis.dao")
public class SecondCacheTest {

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

    //
    @Test
    public void cacheTest1() throws SQLException {
        Cache cache = configuration.getCache("com.ghaya.mybatis.dao.UserDao");
        User user = new User();
        user.setId(1);
        cache.putObject("ghaya",user);//设置
        cache.getObject("ghaya");
    }


    /**
     * 命中条件
     * 1.会话手动提交之后()
     * 2.Sql语句，参数相同
     * 3.相同的statementID
     * 4.RowBounds相同
     * @throws SQLException
     */
    @Test
    public void cacheTest2() throws SQLException {
        SqlSession sqlSession = factory.openSession(true);//自动提交
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = mapper.selectByid4(1);
        sqlSession.commit();


        SqlSession sqlSession2 = factory.openSession(true);//自动提交
        UserDao mapper2 = sqlSession2.getMapper(UserDao.class);
        User user2 = mapper2.selectByid4(1);
        System.out.println(user==user2);
    }


    /**
     * 命中条件
     *
     */
    @Test
    public void cacheTest3() throws SQLException {
        SqlSession sqlSession = factory.openSession(true);//自动提交
        UserDao mapper1= sqlSession.getMapper(UserDao.class);
        StudentDao mapper2 = sqlSession.getMapper(StudentDao.class);
        User user = mapper1.selectByid4(1);
        Student student = mapper2.selectByid4(2);
        System.out.println(user);
        System.out.println(student);
        sqlSession.commit();
    }

}

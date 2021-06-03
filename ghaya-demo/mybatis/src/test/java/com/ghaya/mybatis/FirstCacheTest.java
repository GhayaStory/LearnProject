package com.ghaya.mybatis;

import com.ghaya.mybatis.dao.UserDao;
import com.ghaya.mybatis.pojo.User;
import org.apache.ibatis.executor.*;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstCacheTest {


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

    //简单执行器
    @Test
    public void simpleTest() throws SQLException {
        SimpleExecutor executor = new SimpleExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement("com.ghaya.mybatis.dao.UserDao.queryUserById");
        BoundSql boundSql = ms.getBoundSql(10);
        //1.sql声明映射2.参数3.行范围（分页）4.结果处理器5.动态SQL语句
        List<Object> list = executor.doQuery(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(1));
        List<Object> list2 = executor.doQuery(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(1));
        System.out.println(list);
    }

    //可重用执行器
    @Test
    public void ReuseTest() throws SQLException {
        ReuseExecutor executor = new ReuseExecutor(configuration, jdbcTransaction);//--------------区别在这
        MappedStatement ms = configuration.getMappedStatement("com.ghaya.mybatis.dao.UserDao.queryUserById");
        BoundSql boundSql = ms.getBoundSql(10);
        System.out.println(boundSql);
        //1.sql声明映射2.参数3.行范围（分页）4.结果处理器5.动态SQL语句
        List<Object> list = executor.doQuery(ms, 6, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));
        List<Object> list2 = executor.doQuery(ms, 7, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));
        System.out.println(list);
        System.out.println(list2);
    }

    //批处理执行器
    //只针对修改操作
    //批处理操作必须手动刷新
    @Test
    public void BatchTest() throws SQLException {
        BatchExecutor executor = new BatchExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement("com.ghaya.mybatis.dao.UserDao.setName");
        Map map = new HashMap<>();
        map.put("arg0",6);
        map.put("arg1","mybatis4");
        executor.doUpdate(ms, map);
        map.put("arg0",7);
        map.put("arg1","mybatis5");
        executor.doUpdate(ms, map);
        executor.doFlushStatements(false);
//		connection.commit();//可以设置自动提交
    }

    //baseExecutor

    @Test
    public void BaseExecutorTest() throws SQLException {
        BaseExecutor executor = new ReuseExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement("com.ghaya.mybatis.dao.UserDao.queryUserById");
        List<Object> query = executor.query(ms, 1, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        System.out.println(query);
        List<Object> query1 = executor.query(ms, 1, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        System.out.println(query1);
    }


    /**
     * 二级缓存
     * @throws SQLException
     */
    @Test
    public void cacheExecutorTest() throws SQLException {
        SimpleExecutor executor = new SimpleExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement("com.ghaya.mybatis.dao.UserDao.queryUserById");
        //装饰着模式
        CachingExecutor cachingExecutor = new CachingExecutor(executor);//二级缓存
        cachingExecutor.query(ms, 1, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        cachingExecutor.commit(true);//先走二级缓存
        cachingExecutor.query(ms, 1, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
    }

    /**
     * 基础sqlsession测试
     */
    @Test
    public void sessionTest(){
//		SqlSession sqlSession = factory.openSession(true);//普通sqlsession
        SqlSession sqlSession = factory.openSession(ExecutorType.REUSE,true);//指定可重用的执行器
        List<Object> list = sqlSession.selectList("com.ghaya.mybatis.dao.UserDao.queryUserById", 1);
        System.out.println(list.get(0));
    }

    /**
     * 一级缓存命中测试
     * 1.sql与参数相同
     * 2.必须是相同的statementID(DAO方法是同一个)
     * 3.sqlSession必须一样（会话级缓存）
     * 4.RowBounds 分页行范围必须相同
     */
    @Test
    public void firstCacheTest(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user1 = mapper.selectByid(1);
        User user2 = mapper.selectByid2(1);
        System.out.println(user1==user2);
//		RowBounds rowBounds = new RowBounds(5, 10);//自定义分页值不走缓存
        RowBounds aDefault = RowBounds.DEFAULT;//默认分页可以走缓存
        List<Object> list = sqlSession.selectList("com.ghaya.mybatis.dao.UserDao.queryUserById", "1", aDefault);
        System.out.println(user1==list.get(0));
        //会话级测试
        User user3 = factory.openSession().getMapper(UserDao.class).queryUserById("1");//新建一个会话则不走缓存
        System.out.println(user1==user3);
    }

    /**
     * 清空缓存的都搞不了
     * 1.未手动清空
     * 2.未调用flushCache=true的查询
     * 3.mapper执行update，commit，rollback方法也会清空缓存
     * 4.localCacheScope未设置成STATEMENT(将一级缓存的作用域改为嵌套查询子查询等查询，普通查询不走一级缓存)
     */
    @Test
    public void firstCacheTest2(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = mapper.selectByid(1);
//		sqlSession.clearCache();//手动清除缓存
        User user2 = mapper.selectByid(1);
        //配置设置为重用执行器，只会编译一次，在一定程度上提高性能
        System.out.println(user==user2);

        //注解设置刷新缓存
        User user3 = mapper.selectByid3(1);
        System.out.println(user==user3);
    }


    @Test
    public void firstCacheTest3(){
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setName("1");
        user.setAge(1);
        user.setEmail("111");
        mapper.insertUser(user);
        user.setName("2");
        user.setAge(2);
        user.setEmail("222");
        mapper.insertUser(user);

    }
}

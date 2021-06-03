package com.ghaya.mybatis;

import com.ghaya.mybatis.dao.UserDao;
import com.ghaya.mybatis.pojo.User;
import org.apache.ibatis.cache.CacheException;
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

/**
 * mybatis执行器
 */
public class ExecutorTest {

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
		map.put("arg0",1);
		map.put("arg1","mybatis1");
		executor.doUpdate(ms, map);
		map.put("arg0",2);
		map.put("arg1","mybatis2");
		executor.doUpdate(ms, map);
		executor.doFlushStatements(false);
		connection.commit();//可以设置自动提交
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

}

package com.ghaya.mybatis.dao;


import com.ghaya.mybatis.pojo.User;
import com.ghaya.mybatis.pojo.query.PageObj;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //告诉springboot这事一个mybatis的mapper类
//@Repository // 将userdao交给spring容器管理
@CacheNamespace(blocking = true)
@CacheNamespaceRef(User2Dao.class)
public interface User2Dao {
	//查所有
	List<User> listUser();

	//分页
	List<User> pageUser(PageObj pageObj);

	User queryUserById(String id);

	int insertUser(User user);

	int updateUserById(User user);

	int deleteUserById(User user);

	//----------------------------mybatis-----------------

	@Update("update `user` set name = #{arg1} where id = #{arg0} ")
	int setName(Integer id, String name);

	@Select("select * from user where id = #{id}")
	User selectByid(Integer id);

	@Select("select * from user where id = #{id}")
	User selectByid2(Integer id);

	@Select("select * from user where id = #{id}")
	@Options(flushCache = Options.FlushCachePolicy.TRUE)//设置刷新缓存
	User selectByid3(Integer id);


	@Select("select * from user where id = #{id}")
	@Options(useCache = false)//设置二级缓存是否命中
	User selectByid4(Integer id);






}

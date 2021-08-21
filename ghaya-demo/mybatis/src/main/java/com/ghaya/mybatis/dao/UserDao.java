package com.ghaya.mybatis.dao;


import com.ghaya.mybatis.pojo.User;
import com.ghaya.mybatis.pojo.query.PageObj;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper //告诉springboot这事一个mybatis的mapper类
//@Repository // 将userdao交给spring容器管理
@CacheNamespace(blocking = true)
//@CacheNamespaceRef(UserDao.class)
public interface UserDao {
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
//	@Options(useCache = false)//设置二级缓存是否命中
	User selectByid4(Integer id);


	@Select("select * from user where id = #{id}")
	User selectByEveryThing1(Integer id);

	@Select("select * from user where id = #{pId}")
	User selectByEveryThing2(@Param("pId") Integer id);


	@Select("select * from user where name = #{arg0} and email = #{param2}")
	User selectByEveryThing3(String name,String email,@Param("map")Map map);

	@Select("select * from user where name = #{name} and email = #{email}")
	User selectByEveryThing4(@Param("name")String name,@Param("email")String email,@Param("map")Map map);

	@Select("select * from user where name = #{name} and email = #{arg1}")
	User selectByEveryThing5(@Param("name")String name,String email,Map map);



}

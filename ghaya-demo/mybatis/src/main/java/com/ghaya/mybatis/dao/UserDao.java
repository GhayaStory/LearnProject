package com.ghaya.mybatis.dao;


import com.ghaya.mybatis.pojo.User;
import com.ghaya.mybatis.pojo.query.PageObj;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //告诉springboot这事一个mybatis的mapper类
//@Repository // 将userdao交给spring容器管理
@CacheNamespace(blocking = true)
public interface UserDao {
	//查所有
	List<User> listUser();

	//分页
	List<User> pageUser(PageObj pageObj);

//	@CacheNamespace
	User queryUserById(String id);

	int insertUser(User user);

	int updateUserById(User user);

	int deleteUserById(User user);

	//----------------------------mybatis-----------------

	@Update("update `user` set name = #{arg1} where id = #{arg0} ")
	int setName(Integer id, String name);



}

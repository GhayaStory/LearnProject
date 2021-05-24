package com.ghaya.mybatis.dao;


import com.ghaya.mybatis.pojo.User;
import com.ghaya.mybatis.pojo.query.PageObj;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //告诉springboot这事一个mybatis的mapper类
//@Repository // 将userdao交给spring容器管理
public interface UserDao {
	//查所有
	List<User> listUser();

	//分页
	List<User> pageUser(PageObj pageObj);

	User queryUserById(String id);

	int insertUser(User user);

	int updateUserById(User user);

	int deleteUserById(User user);

}

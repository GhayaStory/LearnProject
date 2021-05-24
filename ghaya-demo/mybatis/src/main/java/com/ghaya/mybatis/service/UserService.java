package com.ghaya.mybatis.service;

import com.ghaya.mybatis.pojo.User;
import com.ghaya.mybatis.pojo.query.PageObj;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

	//查所有
	List<User> listUser();

	//分页
	PageInfo<User> pageUser(PageObj pageObj);

	//根据id查
	User queryUserById(String id);

	//新增
	int insertUser(User user);

	//修改
	int updateUserById(User user);

	//删除
	int deleteUserById(User user);

}

package com.ghaya.mybatis.service.impl;

import com.ghaya.mybatis.dao.UserDao;
import com.ghaya.mybatis.pojo.User;
import com.ghaya.mybatis.pojo.query.PageObj;
import com.ghaya.mybatis.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> listUser() {
		return userDao.listUser();
	}

	@Override
	public PageInfo<User> pageUser(PageObj pageObj) {
		PageHelper.startPage(pageObj.getPageNum(),pageObj.getPageSize());
		return new PageInfo<User>(userDao.pageUser(pageObj));
	}

	@Override
	public User queryUserById(String id) {
		User user = userDao.queryUserById(id);
		return user;
	}

	@Override
	public int insertUser(User user) {
		int i = userDao.insertUser(user);
		return i;
	}

	@Override
	public int updateUserById(User user) {
		int i = userDao.updateUserById(user);
		return i;
	}

	@Override
	public int deleteUserById(User user) {
		int i = userDao.deleteUserById(user);
		return i;
	}
}

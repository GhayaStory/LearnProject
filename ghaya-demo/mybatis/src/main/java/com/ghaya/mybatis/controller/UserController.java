package com.ghaya.mybatis.controller;


import com.ghaya.mybatis.pojo.User;
import com.ghaya.mybatis.pojo.query.PageObj;
import com.ghaya.mybatis.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;


	@PostMapping("/listUser")
	public List<User> listUser(){
		return userService.listUser();
	}


	@PostMapping("/pageUser")
	public PageInfo<User> pageUser(@RequestBody PageObj pageObj){
		return userService.pageUser(pageObj);
	}

	@GetMapping("/queryUserById/{id}")
	public User queryUserById(@PathVariable String id){
		return userService.queryUserById(id);
	}

	@PostMapping("/insertUser")
	public int insertUser(@RequestBody User user){
		return userService.insertUser(user);
	}

	@PostMapping("/updateUserById")
	public int updateUserById(@RequestBody User user){
		return userService.updateUserById(user);
	}

	@PostMapping("/deleteUserById")
	public int deleteUserById(@RequestBody User user){
		return userService.deleteUserById(user);
	}
}

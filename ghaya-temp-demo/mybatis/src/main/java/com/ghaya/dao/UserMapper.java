package com.ghaya.dao;

import com.ghaya.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //查询全部用户
    List<User> selectUser();
    //根据id查询用户
    User selectUserById(int id);

    //添加一个用户
    int addUser(User user);
    //修改一个用户
    int updateUser(User user);
    //根据id删除用户
    int deleteUser(int id);

    //多参数
    int addUserForMap(Map<String,Object> map);
}

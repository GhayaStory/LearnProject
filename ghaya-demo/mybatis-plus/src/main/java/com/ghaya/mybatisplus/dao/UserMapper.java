package com.ghaya.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ghaya.mybatisplus.entity.User;


/**
 * 自定义UserMapper接口，
 * 若想使用MP提供CRUD功能，需要继承BaseMapper<T>
 */
public interface UserMapper extends BaseMapper<User> {

}

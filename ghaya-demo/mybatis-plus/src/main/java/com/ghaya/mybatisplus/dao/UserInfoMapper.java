package com.ghaya.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ghaya.mybatisplus.entity.User_Info;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * 自定义UserMapper接口，
 * 若想使用MP提供CRUD功能，需要继承BaseMapper<T>
 */
public interface UserInfoMapper extends BaseMapper<User_Info> {

    @Select("select * from t_user_info")
    List<User_Info> findAllUserInfo();

    List<User_Info> findAllUserInfo2();

}

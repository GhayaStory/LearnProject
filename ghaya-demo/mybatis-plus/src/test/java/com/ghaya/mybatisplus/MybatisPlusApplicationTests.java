package com.ghaya.mybatisplus;

import com.ghaya.mybatisplus.dao.UserInfoMapper;
import com.ghaya.mybatisplus.dao.UserMapper;
import com.ghaya.mybatisplus.entity.User;
import com.ghaya.mybatisplus.entity.User_Info;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Test
    public void contextLoads() {
        User user = userMapper.selectById(1);
        System.out.println(user);

    }

    @Test
    public void testInsert() {
        User_Info user_info = new User_Info();
        user_info.setUser_id(1);
        user_info.setNicoName("Ghaya");
        user_info.setAddress("1#201");
        user_info.setCre("Ghaya");
        user_info.setCreTime(new Date());
        user_info.setModi("Ghaya");
        user_info.setModiTime(new Date());
        user_info.setOther1("other1");
        int count = userInfoMapper.insert(user_info);
        System.out.println(count);
        System.out.println(user_info);
        System.out.println(user_info.getId());
    }

    @Test
    public void testCustom() {
        //注解型自定义
//        List<User_Info> allUserInfo = userInfoMapper.findAllUserInfo();
//        System.out.println(allUserInfo);
//        System.out.println(allUserInfo.size());

        //xml型自定义
        List<User_Info> allUserInfo2 = userInfoMapper.findAllUserInfo2();
        System.out.println(allUserInfo2);
        System.out.println(allUserInfo2.size());


    }
}

package com.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//自动getset
@Data
//无参构造
@NoArgsConstructor
//全参构造
@AllArgsConstructor
@TableName("t_user")
public class User {

    private Integer id;//id
    private String name;//姓名
    private Integer age;//年龄
    private String mobile;//电话
    private Date creTime;//创建时间
    private String cre;//创建人
    private Date modiTime;//修改时间
    private String modi;//修改人

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}

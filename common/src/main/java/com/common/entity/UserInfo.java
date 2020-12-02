package com.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("t_user_info")
public class UserInfo {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;//id
    private Integer user_id;//用户id
    @TableField(value = "nicoName")
    private String nicoName;//昵称
    private String address;//地址
    @TableField(value = "creTime")
    private Date creTime;//创建时间
    private String cre;//创建人
    @TableField(value = "modiTime")
    private Date modiTime;//修改时间
    private String modi;//修改人


    //是否与数据库表关联 false表示不匹配数据库列名，提交时设置了也没关系
    @TableField(exist = false)
    private String other1;
    @TableField(exist = false)

    //测试时间
    @JsonFormat(pattern = "yyyy-MM", timezone = "GMT+8")
    private String time;


    public UserInfo(Integer user_id, Date creTime) {
        this.user_id = user_id;
        this.time = time;
    }
}

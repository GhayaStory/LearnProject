package com.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//自动getset
@Data
//无参构造
@NoArgsConstructor
//全参构造
@AllArgsConstructor
@ToString
public class UserInfo {

    private Integer id;//id
    private Integer user_id;//用户id
    private String nicoName;//昵称
    private String address;//地址
    private Date creTime;//创建时间
    private String cre;//创建人
    private Date modiTime;//修改时间
    private String modi;//修改人

    private String other1;


    //测试时间
    @JsonFormat(pattern = "yyyy-MM", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM")
    private Date time;


    public UserInfo(Integer user_id, Date time) {
        this.user_id = user_id;
        this.time = time;
    }
}

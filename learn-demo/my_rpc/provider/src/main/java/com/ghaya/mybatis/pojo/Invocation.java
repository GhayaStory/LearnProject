package com.ghaya.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invocation {

    private String interfaceName; //接口名
    private String methodName; //方法名
    private Object[] params; //参数值列表
    private Class[] paramTypes; //参数类型列表

}

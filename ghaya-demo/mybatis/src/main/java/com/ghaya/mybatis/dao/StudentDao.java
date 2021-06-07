package com.ghaya.mybatis.dao;


import com.ghaya.mybatis.pojo.Student;
import com.ghaya.mybatis.pojo.User;
import com.ghaya.mybatis.pojo.query.PageObj;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //告诉springboot这事一个mybatis的mapper类
@CacheNamespace(blocking = true)
//@CacheNamespaceRef(StudentDao.class)
public interface StudentDao {

	@Update("update `student` set name = #{arg1} where id = #{arg0} ")
	int setName(Integer id, String name);

	@Select("select * from student where id = #{id}")
	Student selectByid(Integer id);

	@Select("select * from student where id = #{id}")
	Student selectByid2(Integer id);

	@Select("select * from student where id = #{id}")
	@Options(flushCache = Options.FlushCachePolicy.TRUE)//设置刷新缓存
	Student selectByid3(Integer id);


	@Select("select * from student where id = #{id}")
	@Options(useCache = false)//设置二级缓存是否命中
	Student selectByid4(Integer id);






}

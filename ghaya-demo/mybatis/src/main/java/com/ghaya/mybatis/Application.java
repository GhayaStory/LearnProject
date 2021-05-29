package com.ghaya.mybatis;


import com.ghaya.mybatis.pojo.User;
import com.google.common.collect.Maps;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.sql.SQLOutput;
import java.util.*;


/**
 * 模拟mybatis流程
 */
public class Application {
	public static void main(String[] args) {
		UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(Application.class.getClassLoader(), new Class[]{UserMapper.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Select annotation = method.getAnnotation(Select.class);
				Map<String, Object> nameArgMap = buildMethodArgNameMap(method, args);
				if(annotation != null){
					String[] value = annotation.value();
					System.out.println(Arrays.toString(value));
				}

				return null;
			}
		});
		List<User> users = userMapper.queryUserById(1,"Ghaya");
		System.out.println(users);

	}


	/**
	 * 完成sql的#{id}等参数的替换
	 * @param SQL
	 * @param nameArgMap
	 * @return
	 */
	public static String parseSQL(String SQL,Map<String, Object> nameArgMap){
		StringBuilder stringBuilder = new StringBuilder();
//		String[] keyStrings = (String[]) nameArgMap.keySet().toArray();
//		for (int i = 0; i < keyStrings.length; i++) {
//			keyStrings[i]
//		}


		//拆成字符串循环找#和$
		char[] chars = SQL.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char aChar = chars[i];
			if(aChar=='#' || aChar == '$'){

			}
		}
		//然后进行替换返回SQL
		return stringBuilder.toString();
	}

	/**
	 * 拿出参数列表
	 * @param method
	 * @param args
	 * @return
	 */
	public static Map<String,Object> buildMethodArgNameMap(Method method,Object[] args){
		System.out.println("--------------------");
		//匿名内部类引用要求用final,可以用数组来跳过虚拟机的机制
		int index[] = {0};
		//数据量大用stream并行处理，需要用Maps.newConcurrentMap()
		Map<String, Object> nameArgMap = Maps.newHashMap();
		Parameter[] parameters = method.getParameters();
		Arrays.asList(parameters).forEach(parameter -> {
			String name = parameter.getName();
			System.out.println(name);
			nameArgMap.put(name,args[index[0]]);
			index[0]++;
		});
		Set<String> strings = nameArgMap.keySet();
		System.out.println(strings);
		return nameArgMap;
	}
}


/*public */ @interface ABC{

	public String value() default "test";
}


interface UserMapper{

	@Select("SELECT * FROM USER WHERE id = #{id}")
	List<User> queryUserById(Integer id,String name);

	@ABC
	int insertUser(User user);
}
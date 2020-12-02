package ghaya.controller;

import com.common.entity.User;
import com.common.entity.UserInfo;
import ghaya.common.ResultCode;
import ghaya.exception.BusinessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 异常捕获测试
 */
@RestController
public class testController {

    @GetMapping("/hello")
    public String hello(){
        return "hello laowan!";
    }

    @GetMapping("/testGet")
    public String testGet(String name) throws Exception {
        if (name==null) {
            throw new BusinessException(ResultCode.PAPAM_IS_BLANK);
        }
        return "laowan!";
    }

    @PostMapping("/testPost")
    public String testPost(){
        return "post laowan!";
    }


    /**
     * restFul风格介绍
     */

    /**
     * 获取所有用户
     * @return
     */
    @GetMapping("users")
    public List<User> listUser(){
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1,"Ghaya1"));
        list.add(new User(2,"Ghaya2"));
        return list;
    }

    /**
     * 获取某个员工的信息（路径传参）
     * 1.确定资源/employees/{id}使用路径占位符
     * 2.确定请求方式 GET
     * 3.确定返回结果（类型，头信息，状态码）员工对象，content-type=application/json，201
     * 如果Pathvariable注解没有设置value，黑默认就是去路径上）/找相同名称的参数
     */
    @GetMapping("users/{id}/{name}")
    public User getUserBySome(@PathVariable("id") Integer id,@PathVariable("name") String name){
        return new User(id,name);
    }

    /**
     * 删除一个用户
     * 1.确定资源/employees/{id）
     * 2.确定请求方式 DELETE
     * 3.确定返回结果类型，头信息，状态码）空文档，204
     */
    @DeleteMapping("users/{id}")
    public void delUserBySome(@PathVariable Long id, HttpServletResponse response){
        System.out.println("删除员工为"+id);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    /**
     * 获取某个用户某个月的用户信息
     * 1.确定资源/employees/（employeeId）/salaries/{month）
     * 2.确定请求方式 GET
     * 3.确定返回结果（类型，头信息，状态码）薪资对象，content-type=application/json，200
     * eDatepimeFormat 前台传日期参数到后台接收时使用的注解
     * @usonFormat 后台返回json数据给前台时使用的注解
     */
    @GetMapping("users/{id}/{date}")
    public UserInfo getUserInfoByUser(@PathVariable Integer id,
                                      @PathVariable @DateTimeFormat(pattern = "yyyy-MM") Date month){

        return new UserInfo(id,month);
    }






}

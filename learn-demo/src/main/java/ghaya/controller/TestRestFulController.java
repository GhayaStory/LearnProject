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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基本应用测试
 * restful
 * @PathVariable 与 @RequestParam 区别  后者必须要传参数，值可以空
 */
@RestController
//@RequestMapping("users")
public class TestRestFulController {

    @GetMapping("/hello")
    public String hello() {
        return "hello laowan!";
    }

    @GetMapping("/testGet")
    public String testGet(String name) throws Exception {
        if (name == null) {
            throw new BusinessException(ResultCode.PAPAM_IS_BLANK);
        }
        return "laowan!";
    }

    @PostMapping("/testPost")
    public String testPost() {
        return "post laowan!";
    }


    /**
     * restFul风格介绍
     */

    /**
     * 获取所有用户
     *
     * @return
     */
    @GetMapping("users")
    public List<User> listUser() {
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1, "Ghaya1"));
        list.add(new User(2, "Ghaya2"));
        return list;
    }

    /**
     * 获取某个员工的信息（路径传参）
     * 1.确定资源/employees/{id}使用路径占位符
     * 2.确定请求方式 GET
     * 3.确定返回结果（类型，头信息，状态码）员工对象，content-type=application/json，201
     * 如果RequestParam注解没有设置value，黑默认就是去路径上）/找相同名称的参数
     */
//    @GetMapping("users/{id}/{name}")
    public User getUserBySome(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        return new User(id, name);
    }

    /**
     * 删除一个用户
     * 1.确定资源/employees/{id）
     * 2.确定请求方式 DELETE
     * 3.确定返回结果类型，头信息，状态码）空文档，204
     */
    @DeleteMapping("users/{id}")
    public void delUserBySome(@RequestParam Long id, HttpServletResponse response) {
        System.out.println("删除员工为" + id);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    /**
     * 获取某个用户某个月的用户信息
     * 1.确定资源/employees/（employeeId）/salaries/{month）
     * 2.确定请求方式 GET
     * 3.确定返回结果（类型，头信息，状态码）薪资对象，content-type=application/json，200
     *
     * @DatetimeFormat 前台传日期参数到后台接收时使用的注解
     * @JsonFormat 后台返回json数据给前台时使用的注解
     */
    @GetMapping("users/{id}/{time}")
    public UserInfo getUserInfoByUser(@RequestParam Integer id,
                                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM") Date time) {
        System.out.println(id);
        System.out.println(time);

//        return null;
        return new UserInfo(id, time);
    }

    /**
     * 给某个员工添加一条薪资记录
     * 1.确定资源/employees/femployeeId）/salaries
     * 2.确定请求方式 posT
     * 3.确定返回结果（类型，头信息，状态码）薪资对象，content-type=application/ison，201
     * 路径占位符中的参数，可以自动封装到自定义对象中的同名属性上
     */
    @PostMapping("users/{id}/{time}")
    public UserInfo saveUserInfo(UserInfo userinfo) {
        System.out.println(userinfo);
        return userinfo;
    }


    @RequestMapping(value = {"aaa", "bbb"})//多个路径  method 也可以多个
    public String test1() {
        System.out.println("多路径方法");
        return "多路径方法";
    }

    /**
     * params是规定请求时必须
     * 代表请求时，必须带有name参数，并且值也必须是admin
     */
    @RequestMapping(params = "name=admin")
    public void test2(){
        System.out.println("name=admin方法.…");
    }

    /**
     * 规定请求时，必须带指定的头信息
     * 其他类似headers还有
     * comsumes参数 消费者
     * produces参数 生产者
     *
     * @return
     */
    @RequestMapping(value="headers",headers="content-type=text/html")
    public String test3(){
        return "headers方法  content-type=text/html";
    }
    @RequestMapping(value="headers",headers="content-type=text/xml")
    public String test4(){
        return "headers方法  content-type=text/xml";
    }



}

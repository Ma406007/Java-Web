package org.example.Controller;

import cn.hutool.core.io.IoUtil;
import org.example.Service.Impl.UserServiceImpl;
import org.example.Service.UserService;
import org.example.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/*
  controller方法中的return的结果可以使用@ResponseBody响应给浏览器
  @ResponseBody注解：
   - 类型:方法注解和类注解
   - 位置:书写在Controller方法上或类上
   - 作用:将方法返回值直接响应给浏览器,如果返回值类型是实体对象/集合,将会转换为JSON格式后在响应给浏览器
  但是在我们所书写的Controller中,只在类上添加了@RestController注解,方法添加了@RequestMapping注解,并没有使用@ResponseBody注解,怎么给浏览器响应呢?
  这是因为我们在类上加了@RestController注解,而这个注解是由两个注解组合起来的,分别是:@Controller和@ResponseBody
  那也就意味着我们在类上已经添加了@ResponseBody注解了,而一旦在类上加了@ResponseBody注解,就相当于该类所有的方法中都已经添加了@ResponseBody注解
  所以在前后端分离的项目中,一般直接在请求处理类上加@RestController注解,就无需在方法上加@ResponseBody注解了
*/
@RestController
public class UserController {

    /*@RequestMapping("/list")
    public List<User> list(){
        //1.加载并读取文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());

        //2.解析数据，封装成对象 --> 集合
        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            return new User(id, username, password, name, age, updateTime);
        }).toList();

        //3.响应数据
        //return JSONUtil.toJsonStr(userList, JSONConfig.create().setDateFormat("yyyy-MM-dd HH:mm:ss"));
        return userList;
    }*/

    /*
      @Autowired进行依赖注入,常见的方式有如下三种:
        1.属性注入
          - 优点:代码简洁,方便快速开发
          - 缺点:隐藏了类之间的依赖关系,可能会破坏类的封装性
        2.构造方法注入
          - 优点:能清晰地看到类的依赖关系,提高了代码的安全性
          - 缺点:代码繁琐,如果构造参数过多可能会导致构造函数臃肿
          - 注意:如果只有一个构造函数,@Autowired注解可以省略(通常来说也只有一个构造函数)
        3.setter注入
          - 优点:保持了类的封装性,依赖关系更清晰
          - 缺点:需要额外编写setter方法,增加了代码量

          在项目开发中基于@Autowired进行依赖注入时,基本都是第一种和第二种方式(官方推荐第二种方式,因为会更加规范)
          但是在企业项目开发中,很多的项目中也会选择第一种方式因为更加简洁,高效(在规范性方面进行了妥协)
    */
    //为Controller及Service注入运行时所依赖的对象
    //A.UserController
    //1.属性注入
    @Autowired//注入依赖
    private UserService userService;

    //2.构造方法注入
//    private final UserService userService;
//
//    @Autowired //如果当前类中只存在一个构造函数, @Autowired可以省略
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    //3.setter注入
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/list")
    public List<User> list(){
        //1.调用Service,获取数据
        List<User> userList = userService.findAll();



        //2..响应数据
        //return JSONUtil.toJsonStr(userList, JSONConfig.create().setDateFormat("yyyy-MM-dd HH:mm:ss"));
        return userList;
    }

}
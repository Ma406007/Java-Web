package Springboot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
  需求:基于SpringBoot的方式开发一个web应用,浏览器发起请求/hello后,给浏览器返回字符串"Hello xxx~"
*/

@RestController//标识当前类是一个请求处理类
public class HelloController {
    @RequestMapping("/hello")//标识请求路径
    public String Hello(String name) {
        System.out.println("name:" + name);
        return "Hello " + name + "~";
    }
}

//为什么一个main方法就可以将Web应用启动了?
/*
  答:
    因为我们在创建springboot项目的时候选择了web开发的起步依赖spring-boot-starter-web
    而spring-boot-starter-web又依赖了spring-boot-starter-tomcat,由于maven的依赖传递特性,在我们创建的springboot项目中也就已经有了tomcat的依赖
    这个其实就是springboot中内嵌的tomcat
    而我们运行引导类中的main方法其实启动的就是springboot中内嵌的Tomcat服务器
    而我们所开发的项目也会自动的部署在该tomcat服务器中并占用8080端口号
*/


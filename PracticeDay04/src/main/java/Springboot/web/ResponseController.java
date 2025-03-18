package Springboot.web;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/*
  获取请求数据:
    Web服务器(Tomcat)对HTTP协议的请求数据进行解析,并进行了封装(HttpServletResponse类),并在调用Controller方法的时候传递给了该方法
    这样就使得程序员不必直接对协议进行操作,让Web开发更加便捷
*/

@RestController
public class ResponseController {

    @RequestMapping("/response")
    public void response(HttpServletResponse response) throws IOException {
        //1.设置响应状态码
        response.setStatus(401);
        //2.设置响应头
        response.setHeader("name","itcast");
        /*
          注意:
            响应状态码和响应头如果没有特殊要求的话,通常不手动设定
            服务器会根据请求处理的逻辑自动设置响应状态码和响应头
        */
        //3.设置响应体
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("<h1>hello response</h1>");
    }

    @RequestMapping("/response2")
    public ResponseEntity<String> response2(HttpServletResponse response) throws IOException {
        return ResponseEntity
                .status(401)
                .header("name","itcast")
                .body("<h1>hello response</h1>");
    }
}

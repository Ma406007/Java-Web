package org.example.config;

import org.example.interceptor.DemoInterceptor;
import org.example.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //拦截器对象
    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Autowired
    private DemoInterceptor demoInterceptor;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册自定义拦截器对象
//        registry.addInterceptor(demoInterceptor).addPathPatterns("/**");//设置拦截器拦截的请求路径（ /** 表示拦截所有请求）
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器对象
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册自定义拦截器对象
//        registry.addInterceptor(demoInterceptor)
//                .addPathPatterns("/**")//设置拦截器拦截的请求路径（ /** 表示拦截所有请求）
//                .excludePathPatterns("/login");//设置不拦截的请求路径
//    }
}

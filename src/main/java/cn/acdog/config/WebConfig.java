package cn.acdog.config;

import cn.acdog.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    public void addInterceptors(InterceptorRegistry registry) {
        //登录和注册接口不拦截
       registry.addInterceptor(loginInterceptor).excludePathPatterns("/getJob/getJobList","/postJob/getPostJob","/user/login","/user/register","/");
    }
}

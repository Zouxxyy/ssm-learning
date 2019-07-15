package com.zouxxyy.springboot.config;

import com.zouxxyy.springboot.component.LoginHandlerInterceptor;
import com.zouxxyy.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    // 浏览器发送 / 请求会来到templates/login.html
    public  void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    // 用@Bean将组件注入
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
        // 静态资源做了静态资源映射，不需要处理
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html", "/", "/user/login");
    }
}

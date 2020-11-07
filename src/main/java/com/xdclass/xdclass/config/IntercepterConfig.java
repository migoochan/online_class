package com.xdclass.xdclass.config;

import com.xdclass.xdclass.intercepter.LoginIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 * 不需要权限就可以访问的:/api/v1/pub/
 * 需要权限就可以访问的:/api/v1/pri/
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
    @Bean
    public LoginIntercepter loginIntercepter() {
        return new LoginIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截全部
        registry.addInterceptor(loginIntercepter()).addPathPatterns("/api/v1/pri/*/*/**")
                //不拦截部分
                .excludePathPatterns("/api/v1/pri/user/login", "/api/v1/pri/user/register");
        WebMvcConfigurer.super.addInterceptors(registry);

    }
}

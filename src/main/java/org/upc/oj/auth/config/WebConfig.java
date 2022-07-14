package org.upc.oj.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.upc.oj.auth.interceptor.GateWayInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //在注册中心添加拦截器
        registry.addInterceptor(new GateWayInterceptor());
    }
}

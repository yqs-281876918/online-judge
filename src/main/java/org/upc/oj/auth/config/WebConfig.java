package org.upc.oj.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.upc.oj.auth.interceptor.GateWayFilter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST","DELETE","PATCH","PUT")
                .allowCredentials(true)
                .allowedOrigins("*")
                .maxAge(Integer.MAX_VALUE);
    }
}

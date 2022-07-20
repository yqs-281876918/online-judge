package org.upc.oj.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.upc.oj.auth.interceptor.GateWayFilter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
}

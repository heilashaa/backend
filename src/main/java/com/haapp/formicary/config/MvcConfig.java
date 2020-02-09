package com.haapp.formicary.config;

import com.haapp.formicary.infrastructure.CommonData;
import com.haapp.formicary.infrastructure.CommonDataInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    final private CommonData commonData;

    @Override
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommonDataInterceptor(commonData))/*todo .order(Ordered.HIGHEST_PRECEDENCE)*/;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
                .allowCredentials(false);
    }
}

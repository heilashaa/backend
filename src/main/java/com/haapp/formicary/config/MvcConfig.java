package com.haapp.formicary.config;

import com.haapp.formicary.infrastructure.CommonData;
import com.haapp.formicary.infrastructure.CommonDataInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Configuration
@AllArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final CommonData commonData;

    private static final Integer MAX_AGE = 3600;

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
                .allowCredentials(false).maxAge(MAX_AGE);
    }

//    @Bean
//    public MultipartResolver multipartResolver() {
//        return new StandardServletMultipartResolver() {
//            @Override
//            public boolean isMultipart(HttpServletRequest request) {
//                String method = request.getMethod().toLowerCase();
//                //By default, only POST is allowed. Since this is an 'update' we should accept PUT.
//                if (!Arrays.asList("put", "post").contains(method)) {
//                    return false;
//                }
//                String contentType = request.getContentType();
//                return (contentType != null &&contentType.toLowerCase().startsWith("multipart/"));
//            }
//        };
//    }
}

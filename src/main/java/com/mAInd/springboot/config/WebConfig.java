package com.mAInd.springboot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final long MAX_AGE_SECS = 3600;
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
//        argumentResolvers.add(loginUserArgumentResolver);
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4443")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
    }

}

package com.mAInd.springboot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final long MAX_AGE_SECS = 3600;

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
//        argumentResolvers.add(loginUserArgumentResolver);
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://maind.site:8080",
                        "http://localhost:3000",
                        "https://localhost:3000",
                        "https://127.0.0.1:3000"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .exposedHeaders("Authorization", "Authorization-Refresh", "Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowedHeaders("Authorization", "Authorization-Refresh", "Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
    }




}

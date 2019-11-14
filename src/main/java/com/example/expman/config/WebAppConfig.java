package com.example.expman.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebAppConfig
 * @Description TODO
 * @Author 程方园
 * @Date 2019/11/2 13:58
 * @Version 1.0
 */

@Configuration
public class WebAppConfig{

    @Value("${excelPath}")
    private String excelPath;


    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/excel/**").addResourceLocations("file:"+excelPath);
            }
        };
    }

}


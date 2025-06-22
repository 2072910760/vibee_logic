package org.example.vibee;

import org.example.vibee.web.converter.SqlDateConverter;
import org.example.vibee.web.converter.SqlTimeConverter;
import org.example.vibee.web.converter.SqlTimestampConverter;
import org.example.vibee.web.converter.UtilDateConverter;
import org.example.vibee.web.Intereptor.LoginCheckedInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
public class VibeeApplication implements WebMvcConfigurer {
    
    public static void main(String[] args) {
        SpringApplication.run(VibeeApplication.class, args);
    }
    
    /**
     * 注册数据类型转换器
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SqlDateConverter());
        registry.addConverter(new SqlTimeConverter());
        registry.addConverter(new SqlTimestampConverter());
        registry.addConverter(new UtilDateConverter());
    }
    
    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckedInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register", "/error", "/", "/test", "/index.html", "/static/**", "/css/**", "/js/**", "/images/**", "/doc.html");
    }
}
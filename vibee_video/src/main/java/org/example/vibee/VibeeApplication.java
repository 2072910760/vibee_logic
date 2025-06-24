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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
@Configuration
@MapperScan("org.example.vibee.dao")
@EnableTransactionManagement
public class VibeeApplication implements WebMvcConfigurer {
    
    @Value("${file.upload-dir}")
    private String uploadDir;
    
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
                .excludePathPatterns(
                        "/user/login", "/user/logout", "/user/register", "/user/sendVerificationCode", "/user/resetPassword",
                        "/api/user/login", "/api/user/logout", "/api/user/register", "/api/user/sendVerificationCode", "/api/user/resetPassword",
                        "/uploads/**", "/login", "/register", "/error", "/", "/test", "/index.html",
                        "/static/**", "/css/**", "/js/**", "/images/**", "/visitor/**", "/doc.html",
                        "/favicon.ico", "/swagger-ui/**", "/v3/api-docs/**"
                );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadDir + "/");
    }

    /**
     * 全局CORS配置，允许前端携带cookie跨域访问
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
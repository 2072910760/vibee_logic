package org.example.vibee.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 插件配置
 */

@Configuration
public class PluginConfig {

    @Bean
    public MybatisPlusInterceptor getMybatisPlusInterceptor(){
        MybatisPlusInterceptor  mybatisPlusInterceptor=new MybatisPlusInterceptor();
        List<InnerInterceptor> interceptors=new ArrayList<>();
        
        // 分页插件配置，支持SQL Server
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setDbType(com.baomidou.mybatisplus.annotation.DbType.SQL_SERVER);
        paginationInnerInterceptor.setMaxLimit(500L);
        
        interceptors.add(paginationInnerInterceptor);
        mybatisPlusInterceptor.setInterceptors(interceptors);
        return mybatisPlusInterceptor;
    }
}

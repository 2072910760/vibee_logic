package org.example.vibee.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.example.vibee.web.Intereptor.LoginCheckedInterceptor;
import org.example.vibee.web.converter.SqlDateConverter;
import org.example.vibee.web.converter.UtilDateConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;

@Configuration
@Log4j2
public class SpringBootWebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //注册spring mvc拦截器
        LoginCheckedInterceptor loginCheckedInterceptor = new LoginCheckedInterceptor();
        //配置拦截的URL及无需要拦截的URL
        //registry.addInterceptor(loginCheckedInterceptor).addPathPatterns("/view/**", "/pet/**", "/customer/**", "/user/**", "/role/**", "/menu/**").excludePathPatterns("/user/login", "/view/login_out", "/view/login_out_modal");
    }

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        //注册数据类型转换器 作用:将前端页面传值的字符格式日期转成java中java.util.Date对象
        SqlDateConverter sqlDateConverter = new SqlDateConverter();
        UtilDateConverter utilDateConverter = new UtilDateConverter();
        registry.addConverter(sqlDateConverter);
        registry.addConverter(utilDateConverter);
    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //配置消息转换器---将控制器方法转成指定的格式---要加responseBody
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        //设置序列化
        fastJsonConfig.setSerializerFeatures(
                WriteNullBooleanAsFalse,
                WriteNullListAsEmpty,
                WriteNullStringAsEmpty,
                WriteNullNumberAsZero,
                WriteDateUseDateFormat,
                WriteMapNullValue,
                PrettyFormat,
                DisableCircularReferenceDetect,
                BrowserCompatible
        );
        List<MediaType> mediaTypes = new ArrayList<>();
        //设置数mediaType媒体据格式
        mediaTypes.add(MediaType.APPLICATION_JSON);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        fastJsonHttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        converters.add(0, fastJsonHttpMessageConverter);
    }

    /***
     * 配置静态资源目录及访问的格式
     */

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        /**配置图片访问前缀**/
        registry.addResourceHandler("/xinxin/images/**").addResourceLocations("file:///D:/xinxi/res/images");
        /**视频访问前缀**/
        registry.addResourceHandler("/xinxin/videos/**").addResourceLocations("file:///D:/xinxin/res/videos");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//设置允许跨域的路径
                .allowedOriginPatterns("*")//设置允许跨域请求的域名
                .allowCredentials(true)//是否允许证书 不再默认开启
                .allowedMethods("GET", "POST", "PUT", "DELETE")//设置允许的方法
                .maxAge(3600);//跨域允许时间
    }

}
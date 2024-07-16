package com.muxingzhe.check.config;

import com.muxingzhe.check.config.intercepter.CheckInterceptor;
import com.muxingzhe.check.config.intercepter.CorsInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域请求控制合并拦截器控制
 * @author kampf
 * @date 2019/7/22 08:50
 */
@Configuration
@AllArgsConstructor
public class CorsConfig implements WebMvcConfigurer {

    CorsInterceptor corsInterceptor;

    CheckInterceptor checkInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 注册拦截器，自定义需要在此注册生效
     * @return
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
        /**
         * swagger需要过滤，将其添置在自定义
         */
        registry.addInterceptor(checkInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg", "/*.html", "/**/*.html","/swagger-resources/**");
    }
}

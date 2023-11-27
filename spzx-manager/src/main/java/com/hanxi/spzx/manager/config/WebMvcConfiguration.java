package com.hanxi.spzx.manager.config;

import com.hanxi.spzx.manager.config.properties.UserAuthProperties;
import com.hanxi.spzx.manager.handler.LoginAuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@EnableConfigurationProperties(value = {UserAuthProperties.class})
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {


    private final LoginAuthInterceptor loginAuthInterceptor ;
    private final UserAuthProperties userAuthProperties ;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
//                .excludePathPatterns("/doc.html","/v3/api-docs/**","/admin/system/index/login" , "/admin/system/index/generateValidateCode")
                .excludePathPatterns(userAuthProperties.getNoAuthUrls())
                .addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
            	.allowedMethods("*")
                .allowedHeaders("*") ;                // 允许所有的请求头
    }

}

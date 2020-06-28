package com.example.backstage.config;

import com.example.backstage.interecptor.LoginInterecptor;
import com.example.backstage.interecptor.TeacherInterecptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.filter.CorsFilter;
/**
 * 拦截器
 * cros过滤器
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private TeacherInterecptor teacherInterecptor;
    @Autowired
    private LoginInterecptor loginInterecptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterecptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login");

        registry.addInterceptor(teacherInterecptor)
                .addPathPatterns("/api/teacher/**");
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("*");
//        config.addAllowedMethod("*");
//        config.addAllowedHeader("*");
//        config.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource configSourse
//                = new UrlBasedCorsConfigurationSource();
//        configSourse.registerCorsConfiguration("/api/**", config);
//        return new CorsFilter(configSourse);
//    }
}

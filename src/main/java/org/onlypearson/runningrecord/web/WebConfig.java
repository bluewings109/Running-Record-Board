package org.onlypearson.runningrecord.web;

import org.onlypearson.runningrecord.web.login.argumentresolver.LoginMemberArgumentResolver;
import org.onlypearson.runningrecord.web.login.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .order(1)
                .addPathPatterns("/records/**")
                .excludePathPatterns("/assets/**","/css/**","/js/**","/templates/**","/login","/members/join","/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }
}

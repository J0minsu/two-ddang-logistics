package com.two_ddang.logistics.core.config;

import com.two_ddang.logistics.core.util.PassportArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final PassportArgumentResolver passPortArgumentResolver;

    public WebConfig(PassportArgumentResolver passPortArgumentResolver) {
        this.passPortArgumentResolver = passPortArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(passPortArgumentResolver);
    }
}

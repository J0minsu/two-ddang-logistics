package com.two_ddang.logistics.gateway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/orders/swagger-ui.html").and().method(HttpMethod.GET).uri("lb://orders"))
                .route(r -> r.path("/companies/swagger-ui.html").and().method(HttpMethod.GET).uri("lb://companies"))
                .route(r -> r.path("/hubs/swagger-ui.html").and().method(HttpMethod.GET).uri("lb://hubs"))
                .build();
    }
}

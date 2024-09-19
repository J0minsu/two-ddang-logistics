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
                .route(r -> r.path("ais/swagger-ui.html").and().method(HttpMethod.GET).uri("lb://ai"))
                .route(r -> r.path("/auths/swagger-ui.html").and().method(HttpMethod.GET).uri("lb://auth"))
                .route(r -> r.path("/orders/swagger-ui.html").and().method(HttpMethod.GET).uri("lb://order"))
                .route(r -> r.path("/companies/swagger-ui.html").and().method(HttpMethod.GET).uri("lb://company"))
                .route(r -> r.path("/hubs/swagger-ui.html").and().method(HttpMethod.GET).uri("lb://hub"))
                .route(r -> r.path("/deliveries/swagger-ui.html").and().method(HttpMethod.GET).uri("lb://delivery"))
                .build();
    }
}

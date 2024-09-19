package com.two_ddang.logistics.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/**")
                        .uri("lb://auth"))
                .route("hub", r -> r.path("/api/v1/hubs/**", "/api/v1/users/**")
                        .uri("lb://hub"))
                .route("orders", r -> r.path("/api/v1/orders/**")
                        .uri("lb://orders"))
                .route("delivery", r -> r.path("/api/v1/deliveries/**", "/api/v1/transits/**")
                        .uri("lb://delivery"))
                .route("companies", r -> r.path("/api/v1/companies/**")
                        .uri("lb://companies"))
                .route("products", r -> r.path("/api/v1/products/**")
                        .uri("lb://products"))
                .route("ais", r -> r.path("/api/v1/ais/**")
                        .uri("lb://ais"))
                .build();
    }

}

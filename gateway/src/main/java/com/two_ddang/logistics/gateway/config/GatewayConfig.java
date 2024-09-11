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
                .route("users", r -> r.path("/users/**")
                        .uri("lb://users"))
                .route("hubs", r -> r.path("/hubs/**")
                        .uri("lb://hubs"))
                .route("orders", r -> r.path("/orders/**")
                        .uri("lb://orders"))
                .route("deliveries", r -> r.path("/deliveries/**")
                        .uri("lb://deliveries"))
                .route("transits", r -> r.path("/transits/**")
                        .uri("lb://transits"))
                .route("companies", r -> r.path("/companies/**")
                        .uri("lb://companies"))
                .route("products", r -> r.path("/products/**")
                        .uri("lb://products"))
                .route("ais", r -> r.path("/ais/**")
                        .uri("lb://ais"))
                .build();
    }

}

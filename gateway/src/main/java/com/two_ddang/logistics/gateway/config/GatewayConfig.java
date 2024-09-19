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
                .route("hubs", r -> r.path("/api/v1/hubs/**", "/api/v1/users/**", "/hubs/v3/api-docs")
                        .uri("lb://hubs"))
                .route("orders", r -> r.path("/api/v1/orders/**", "/orders/v3/api-docs")
                        .uri("lb://order"))
                .route("deliveries", r -> r.path("/api/v1/deliveries/**")
                        .uri("lb://deliveries"))
                .route("transits", r -> r.path("/api/v1/transits/**")
                        .uri("lb://transits"))
                .route("companies", r -> r.path("/api/v1/companies/**", "/api/v1/products/**", "/companies/v3/api-docs")
                        .uri("lb://company"))
                .route("ais", r -> r.path("/api/v1/ais/**")
                        .uri("lb://ais"))
                .build();
    }

}

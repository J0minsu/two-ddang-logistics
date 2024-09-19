package com.two_ddang.logistics.gateway.config;

import com.two_ddang.logistics.gateway.infrastructure.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private static final String[] RESOURCE_WHITELIST = {
            "/v3/**", // v3 : SpringBoot 3(없으면 swagger 예시 api 목록 제공)
            "/swagger-ui**",
            "/swagger-resources/**",
            "/webjars/**",
            "*/v3/api-docs",
    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .authorizeExchange(exchange ->
                        exchange.pathMatchers("/auth/sign-in", "/auth/sign-up", "/api/v1/ais").permitAll()
                                .pathMatchers(RESOURCE_WHITELIST).permitAll()
                                .anyExchange().permitAll())
                .addFilterBefore(jwtAuthenticationFilter, SecurityWebFiltersOrder.HTTP_BASIC)
                .build();
    }

}

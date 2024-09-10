package com.two_ddang.logistics.gateway.config;

import com.two_ddang.logistics.gateway.infrastructure.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange ->
                        exchange.pathMatchers("/auth/sign-in", "auth/sign-up").permitAll()
                                .anyExchange().authenticated())
                .addFilterAt(jwtAuthenticationWebFilter(), SecurityWebFiltersOrder.HTTP_BASIC)
                .build();
    }

    @Bean
    public AuthenticationWebFilter jwtAuthenticationWebFilter() {
        AuthenticationWebFilter webFilter = new AuthenticationWebFilter(jwtReactiveAuthenticationManager());
        webFilter.setServerAuthenticationConverter(jwtAuthenticationConverter());
        return  webFilter;
    }

    @Bean
    public ReactiveAuthenticationManager jwtReactiveAuthenticationManager() {
        return (authentication -> Mono.empty());
    }

    @Bean
    public ServerAuthenticationConverter jwtAuthenticationConverter() {
        return (ServerWebExchange exchange) -> {
            String token = jwtAuthenticationFilter.extractToken(exchange);
            if (token != null) {
                return Mono.just(new UsernamePasswordAuthenticationToken(token, null));
            }
            return Mono.empty();
        };
    }

}

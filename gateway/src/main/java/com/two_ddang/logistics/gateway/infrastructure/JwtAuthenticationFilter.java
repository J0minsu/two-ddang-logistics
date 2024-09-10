package com.two_ddang.logistics.gateway.infrastructure;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class JwtAuthenticationFilter implements GlobalFilter {

    @Value("${service.jwt.secret-key}")
    private String secretKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        if (path.equals("/auth/sign-in") || path.equals("/auth/sign-up")) {
            return chain.filter(exchange);
        }

        String token = extractToken(exchange);
        if (token == null || !validateTokenAndToPassport(token, exchange)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return chain.filter(exchange);
        }

        return chain.filter(exchange);
    }

    private String extractToken(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        //공통 모듈 적용 후 예외로 넘기기
        return null;
    }

    private boolean validateTokenAndToPassport(String token, ServerWebExchange exchange) {

        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(key)
                    .build().parseSignedClaims(token);

            Claims claims = claimsJws.getPayload();

            Date expiration = claims.getExpiration();
            if (expiration.before(new Date())) {
                log.warn("Token has expired");
                return false;
            }

            PassPort passPort = jwtToPassport(claims);
            exchange.getAttributes().put("passport", passPort);

            return true;

        } catch (Exception e) {
            log.error("Invalid token", e);
            return false;
        }
    }

    private PassPort jwtToPassport(Claims claims) {
        String username = claims.getSubject();
        LocalDateTime expirationTime = claims.getExpiration()
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        return new PassPort(username, expirationTime);
    }

}

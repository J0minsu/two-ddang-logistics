package com.two_ddang.logistics.gateway.infrastructure;

import com.two_ddang.logistics.core.entity.UserType;
import com.two_ddang.logistics.core.util.PassPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class JwtAuthenticationFilter implements WebFilter {

    @Value("${server.jwt.secret-key}")
    private String secretKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        if (path.equals("/auth/sign-in") || path.equals("/auth/sign-up")) {
            return chain.filter(exchange);
        }

        String token = extractToken(exchange);
        if (token == null || !validateTokenAndToPassport(token, exchange)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    public String extractToken(ServerWebExchange exchange) {
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
        Integer userId = claims.get("userId", Integer.class);
        String username = claims.get("username", String.class);
        String userType = claims.get("userType", String.class);

        LocalDateTime expirationTime = claims.getExpiration()
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        return new PassPort(userId, username, expirationTime, UserType.valueOf(userType));
    }

}

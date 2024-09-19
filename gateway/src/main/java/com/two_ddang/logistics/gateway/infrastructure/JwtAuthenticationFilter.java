package com.two_ddang.logistics.gateway.infrastructure;

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
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Component
public class JwtAuthenticationFilter implements WebFilter {

    private final String externalSecretKey;

    private final Long expirationTime;

    private final SecretKey internalKey;

    private static final String[] RESOURCE_WHITELIST = {
            "v3/api-docs", // v3 : SpringBoot 3(없으면 swagger 예시 api 목록 제공)
            "/swagger-ui",
            "/swagger-resources/",
            "/webjars/",
    };

    public JwtAuthenticationFilter(@Value("${server.jwt.secret-key}") String externalSecretKey,
                                   @Value("${server.jwt.access-expiration}") Long expirationTime,
                                   @Value("${server.jwt.internal-secret-key}") String internalKey) {
        this.externalSecretKey = externalSecretKey;
        this.expirationTime = expirationTime;
        this.internalKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(internalKey));
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        log.info("path :: {}", path);
        if (path.equals("/auth/sign-in") || path.equals("/auth/sign-up") || Arrays.stream(RESOURCE_WHITELIST).anyMatch(path::contains) || path.equals("/")) {
            return chain.filter(exchange);
        }

        String token = extractToken(exchange);
        if (token == null || !validateExternalToken(token, exchange)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        log.info("패스임?");

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

    private boolean validateExternalToken(String token, ServerWebExchange exchange) {


        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(externalSecretKey));
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(key)
                    .build().parseSignedClaims(token);

            Claims claims = claimsJws.getPayload();

            Date expiration = claims.getExpiration();
            if (expiration.before(new Date())) {
                log.warn("Token has expired");
                return false;
            }

            String internalToken = createInternalToken(claims);
            log.info("jwt Filter Internal Token :: {}", internalToken);

            exchange.getRequest().mutate()
                    .header("InternalToken", "Bearer " + internalToken)
                    .build();

            return true;

        } catch (Exception e) {
            log.error("Invalid token", e);
            return false;
        }
    }

    private String createInternalToken(Claims claims) {
        return Jwts.builder()
                .claim("userId", claims.get("userId"))
                .claim("email", claims.get("email"))
                .claim("username", claims.get("username"))
                .claim("userType", claims.get("userType"))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(internalKey)
                .compact();
    }

}

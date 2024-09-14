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
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class JwtAuthenticationFilter implements WebFilter {

    @Value("${server.jwt.secret-key}")
    private String externalSecretKey;

    @Value("${server.jwt.access-expiration}")
    private Long expirationTime;

    private final SecretKey internalKey;

    public JwtAuthenticationFilter(@Value("${server.jwt}") String internalKey) {
        this.internalKey = new SecretKeySpec(internalKey.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        if (path.equals("/auth/sign-in") || path.equals("/auth/sign-up")) {
            return chain.filter(exchange);
        }

        String token = extractToken(exchange);
        if (token == null || !validateExternalToken(token, exchange)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
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

            exchange.getRequest().mutate()
                    .header("InternalToken", "Bearer" + internalToken)
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
                .expiration(new Date(System.currentTimeMillis()+expirationTime))
                .signWith(internalKey)
                .compact();
    }

}

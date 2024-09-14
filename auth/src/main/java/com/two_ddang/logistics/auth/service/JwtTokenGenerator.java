package com.two_ddang.logistics.auth.service;

import com.two_ddang.logistics.core.entity.UserType;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenGenerator {

    private final SecretKey key;

    private final Long expirationTime;

    public JwtTokenGenerator(@Value("${sever.jwt.secret-key}") String key,
                             @Value("${sever.jwt.access-expiration}") Long expirationTime) {
        this.key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
        this.expirationTime = expirationTime;
    }

    public String createJwtToken(Long userId, String email,
                                 String username, UserType userType) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("email", email)
                .claim("username", username)
                .claim("userType", userType)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expirationTime))
                .signWith(key)
                .compact();
    }


}

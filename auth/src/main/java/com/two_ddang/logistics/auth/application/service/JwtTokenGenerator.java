package com.two_ddang.logistics.auth.application.service;

import com.two_ddang.logistics.core.entity.UserType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenGenerator {

    private final SecretKey key;

    private final Long expirationTime;

    public JwtTokenGenerator(@Value("${sever.jwt.secret-key}") String key,
                             @Value("${sever.jwt.access-expiration}") Long expirationTime) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(key));
        this.expirationTime = expirationTime;
    }

    public String createJwtToken(Integer userId, String email,
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

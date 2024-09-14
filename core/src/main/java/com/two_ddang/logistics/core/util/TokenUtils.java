package com.two_ddang.logistics.core.util;

import com.two_ddang.logistics.core.entity.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class TokenUtils {
    
    //도메인 서버에서 security filter를 사용하는 경우
    public Claims getClaimsByInternalToken(String internalToken, String internalSecretKey) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(internalSecretKey));
        return Jwts.parser()
                .verifyWith(key).build()
                .parseSignedClaims(internalToken)
                .getPayload();
    }
    
    //security filter를 사용하지 않는 경우 - 바로 Passport 객체로 변환
    public Passport toPassport(String internalToken, String internalSecretKey) {
        Claims claims = getClaimsByInternalToken(internalToken, internalSecretKey);
        return new Passport(claims.get("userId", Integer.class), claims.get("email", String.class),
                claims.get("username", String.class), claims.getExpiration(), claims.get("userType", UserType.class));
    }
}

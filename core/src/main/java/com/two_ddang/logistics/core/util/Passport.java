package com.two_ddang.logistics.core.util;

import com.two_ddang.logistics.core.entity.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.crypto.SecretKey;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Passport {

    private Integer userId;

    private String email;

    private String userName;

    private Date expirationTime;
    private UserType userType;

    public Passport(Integer userId, String userName, String email, Date expirationTime,
                    UserType userType) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.expirationTime = expirationTime;
        this.userType=userType;
    }

    public static Passport toPassport(String internalToken, String internalSecretKey) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(internalSecretKey));
        Claims claims = Jwts.parser()
                .verifyWith(key).build()
                .parseSignedClaims(internalToken)
                .getPayload();

        return new Passport(claims.get("userId", Integer.class), claims.get("email", String.class),
                claims.get("username", String.class), claims.getExpiration(), claims.get("userType", UserType.class));
    }

}

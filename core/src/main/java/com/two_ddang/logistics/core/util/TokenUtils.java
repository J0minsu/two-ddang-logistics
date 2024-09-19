package com.two_ddang.logistics.core.util;

import com.two_ddang.logistics.core.entity.UserType;
import com.two_ddang.logistics.core.exception.ClaimsNullException;
import com.two_ddang.logistics.core.exception.ErrorCode;
import com.two_ddang.logistics.core.exception.ExpiredInternalTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
@Slf4j
public class TokenUtils {

    //도메인 서버에서 security filter를 사용하는 경우
    public Claims getClaimsByInternalToken(String internalToken, String internalSecretKey) {

        try {
            log.info("Util internalToken", internalToken);
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(internalSecretKey));
            return Jwts.parser()
                    .verifyWith(key).build()
                    .parseSignedClaims(internalToken)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new ExpiredInternalTokenException();
        }
    }

    //security filter를 사용하지 않는 경우 - 바로 Passport 객체로 변환
    public Passport toPassport(String internalToken, String internalSecretKey) {
        Claims claims = getClaimsByInternalToken(internalToken, internalSecretKey);
        validateClaims(claims);

        return new Passport(claims.get("userId", Integer.class), claims.get("email", String.class),
                claims.get("username", String.class), claims.getExpiration(), UserType.valueOf(claims.get("userType", String.class)));
    }

    private void validateClaims(Claims claims) {
        if (claims.get("userId", Integer.class) == null) {
            throw new ClaimsNullException(ErrorCode.USERID_IS_NULL);
        }

        if (claims.get("email", String.class) == null) {
            throw new ClaimsNullException(ErrorCode.EMAIL_IS_NULL);
        }

        if (claims.get("username", String.class) == null) {
            throw new ClaimsNullException(ErrorCode.USERNAME_IS_NULL);
        }
        String userType = claims.get("userType", String.class);
        if (userType == null) {
            throw new ClaimsNullException(ErrorCode.USERTYPE_IS_NULL);
        }

        try {
            UserType.valueOf(userType);
        } catch (Exception e) {
            throw new ClaimsNullException(ErrorCode.INVALID_USERTYPE);
        }
    }

}

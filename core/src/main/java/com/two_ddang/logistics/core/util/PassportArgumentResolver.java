package com.two_ddang.logistics.core.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class PassportArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenUtils tokenUtils;
    private final String internalSecretKey;

    public PassportArgumentResolver(TokenUtils tokenUtils, @Value("${server.jwt.internal-secret-key}") String internalSecretKey) {
        this.tokenUtils = tokenUtils;
        this.internalSecretKey = internalSecretKey;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentPassport.class) && parameter.getParameterType().equals(Passport.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception
    {
        String passportToken = extractToken(webRequest);

        return tokenUtils.toPassport(passportToken, internalSecretKey);
    }

    private String extractToken(NativeWebRequest request) {
        String authHeader = request.getHeader("InternalToken");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

}

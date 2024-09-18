package com.two_ddang.logistics.core.util;

import com.two_ddang.logistics.core.exception.CustomAccessDeniedException;
import com.two_ddang.logistics.core.exception.ErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RoleCheckAspect {

    @Around("@annotation(passportRoleCheck)")
    public Object checkRole(ProceedingJoinPoint joinPoint, PassportRoleCheck passportRoleCheck) throws Throwable {
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof Passport) {
                Passport passport = (Passport) arg;
                boolean hasRole = passport.getUserType().equals(passportRoleCheck.role());

                if (!hasRole) {
                    throw new CustomAccessDeniedException(ErrorCode.CAN_NOT_ACTION_ROLE);
                }
            }
        }
        return joinPoint.proceed();
    }


}

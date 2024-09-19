package com.two_ddang.logistics.core.util;

import com.two_ddang.logistics.core.entity.UserType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PassportRoleCheck {
    UserType role();
}

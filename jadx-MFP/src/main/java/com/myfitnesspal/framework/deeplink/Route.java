package com.myfitnesspal.framework.deeplink;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Route {
    int order() default 0;

    String pattern();

    boolean requiresAuthentication() default false;
}

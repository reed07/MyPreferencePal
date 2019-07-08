package com.google.ads.interactivemedia.v3.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: IMASDK */
public @interface xm {
    Class<?> a();

    boolean b() default true;
}

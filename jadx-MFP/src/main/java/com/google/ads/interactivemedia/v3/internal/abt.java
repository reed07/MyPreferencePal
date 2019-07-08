package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* compiled from: IMASDK */
public final class abt<T> {
    private final Class<? super T> a;
    private final Type b;
    private final int c;

    protected abt() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            this.b = xq.a(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
            this.a = xq.b(this.b);
            this.c = this.b.hashCode();
            return;
        }
        throw new RuntimeException("Missing type parameter.");
    }

    private abt(Type type) {
        this.b = xq.a((Type) tt.a(type));
        this.a = xq.b(this.b);
        this.c = this.b.hashCode();
    }

    public final Class<? super T> a() {
        return this.a;
    }

    public final Type b() {
        return this.b;
    }

    public final int hashCode() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof abt) && xq.a(this.b, ((abt) obj).b);
    }

    public final String toString() {
        return xq.c(this.b);
    }

    public static abt<?> a(Type type) {
        return new abt<>(type);
    }

    public static <T> abt<T> a(Class<T> cls) {
        return new abt<>(cls);
    }
}

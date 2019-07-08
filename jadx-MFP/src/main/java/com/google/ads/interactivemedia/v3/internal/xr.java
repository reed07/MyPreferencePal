package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* compiled from: IMASDK */
final class xr implements Serializable, GenericArrayType {
    private static final long serialVersionUID = 0;
    private final Type a;

    public xr(Type type) {
        this.a = xq.a(type);
    }

    public final Type getGenericComponentType() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof GenericArrayType) && xq.a((Type) this, (Type) (GenericArrayType) obj);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(xq.c(this.a));
        sb.append("[]");
        return sb.toString();
    }
}

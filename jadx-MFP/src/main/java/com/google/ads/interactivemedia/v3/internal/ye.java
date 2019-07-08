package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;

/* compiled from: IMASDK */
final class ye implements ys<T> {
    private final /* synthetic */ Type a;

    ye(xu xuVar, Type type) {
        this.a = type;
    }

    public final T a() {
        Type type = this.a;
        if (type instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return EnumSet.noneOf((Class) type2);
            }
            StringBuilder sb = new StringBuilder("Invalid EnumSet type: ");
            sb.append(this.a.toString());
            throw new xa(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder("Invalid EnumSet type: ");
        sb2.append(this.a.toString());
        throw new xa(sb2.toString());
    }
}

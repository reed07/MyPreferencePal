package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* compiled from: IMASDK */
final class zd implements xl {
    zd() {
    }

    public final <T> xj<T> a(wo woVar, abt<T> abt) {
        Type type;
        Type b = abt.b();
        boolean z = b instanceof GenericArrayType;
        if (!z && (!(b instanceof Class) || !((Class) b).isArray())) {
            return null;
        }
        if (z) {
            type = ((GenericArrayType) b).getGenericComponentType();
        } else {
            type = ((Class) b).getComponentType();
        }
        return new zc(woVar, woVar.a(abt.a(type)), xq.b(type));
    }
}

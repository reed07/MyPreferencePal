package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Type;
import java.util.Collection;

/* compiled from: IMASDK */
public final class ze implements xl {
    private final xu a;

    public ze(xu xuVar) {
        this.a = xuVar;
    }

    public final <T> xj<T> a(wo woVar, abt<T> abt) {
        Type b = abt.b();
        Class a2 = abt.a();
        if (!Collection.class.isAssignableFrom(a2)) {
            return null;
        }
        Type a3 = xq.a(b, a2);
        return new zf(woVar, a3, woVar.a(abt.a(a3)), this.a.a(abt));
    }
}

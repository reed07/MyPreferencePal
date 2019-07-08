package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Type;
import java.util.Map;

/* compiled from: IMASDK */
public final class zn implements xl {
    final boolean a;
    private final xu b;

    public zn(xu xuVar, boolean z) {
        this.b = xuVar;
        this.a = z;
    }

    public final <T> xj<T> a(wo woVar, abt<T> abt) {
        xj xjVar;
        Type b2 = abt.b();
        if (!Map.class.isAssignableFrom(abt.a())) {
            return null;
        }
        Type[] b3 = xq.b(b2, xq.b(b2));
        Type type = b3[0];
        if (type == Boolean.TYPE || type == Boolean.class) {
            xjVar = aac.c;
        } else {
            xjVar = woVar.a(abt.a(type));
        }
        wo woVar2 = woVar;
        zo zoVar = new zo(this, woVar2, b3[0], xjVar, b3[1], woVar.a(abt.a(b3[1])), this.b.a(abt));
        return zoVar;
    }
}

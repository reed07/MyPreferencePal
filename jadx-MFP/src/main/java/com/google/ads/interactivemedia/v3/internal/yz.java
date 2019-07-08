package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Method;

/* compiled from: IMASDK */
final class yz extends yx {
    private final /* synthetic */ Method a;
    private final /* synthetic */ int b;

    yz(Method method, int i) {
        this.a = method;
        this.b = i;
    }

    public final <T> T a(Class<T> cls) throws Exception {
        b(cls);
        return this.a.invoke(null, new Object[]{cls, Integer.valueOf(this.b)});
    }
}

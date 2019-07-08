package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Method;

/* compiled from: IMASDK */
final class za extends yx {
    private final /* synthetic */ Method a;

    za(Method method) {
        this.a = method;
    }

    public final <T> T a(Class<T> cls) throws Exception {
        b(cls);
        return this.a.invoke(null, new Object[]{cls, Object.class});
    }
}

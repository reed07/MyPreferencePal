package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Method;

/* compiled from: IMASDK */
final class yy extends yx {
    private final /* synthetic */ Method a;
    private final /* synthetic */ Object b;

    yy(Method method, Object obj) {
        this.a = method;
        this.b = obj;
    }

    public final <T> T a(Class<T> cls) throws Exception {
        b(cls);
        return this.a.invoke(this.b, new Object[]{cls});
    }
}

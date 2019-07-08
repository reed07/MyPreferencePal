package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Type;

/* compiled from: IMASDK */
final class ya implements ys<T> {
    private final yx a = yx.a();
    private final /* synthetic */ Class b;
    private final /* synthetic */ Type c;

    ya(xu xuVar, Class cls, Type type) {
        this.b = cls;
        this.c = type;
    }

    public final T a() {
        try {
            return this.a.a(this.b);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Unable to invoke no-args constructor for ");
            sb.append(this.c);
            sb.append(". Registering an InstanceCreator with Gson for this type may fix this problem.");
            throw new RuntimeException(sb.toString(), e);
        }
    }
}

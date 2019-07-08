package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* compiled from: IMASDK */
final class yc implements ys<T> {
    private final /* synthetic */ Constructor a;

    yc(xu xuVar, Constructor constructor) {
        this.a = constructor;
    }

    public final T a() {
        try {
            return this.a.newInstance(null);
        } catch (InstantiationException e) {
            StringBuilder sb = new StringBuilder("Failed to invoke ");
            sb.append(this.a);
            sb.append(" with no args");
            throw new RuntimeException(sb.toString(), e);
        } catch (InvocationTargetException e2) {
            StringBuilder sb2 = new StringBuilder("Failed to invoke ");
            sb2.append(this.a);
            sb2.append(" with no args");
            throw new RuntimeException(sb2.toString(), e2.getTargetException());
        } catch (IllegalAccessException e3) {
            throw new AssertionError(e3);
        }
    }
}

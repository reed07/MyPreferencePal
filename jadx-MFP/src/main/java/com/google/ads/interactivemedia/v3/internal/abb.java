package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class abb implements xl {
    abb() {
    }

    public final <T> xj<T> a(wo woVar, abt<T> abt) {
        Class<Enum> a = abt.a();
        if (!Enum.class.isAssignableFrom(a) || a == Enum.class) {
            return null;
        }
        if (!a.isEnum()) {
            a = a.getSuperclass();
        }
        return new abo(a);
    }
}

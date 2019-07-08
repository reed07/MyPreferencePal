package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class aft implements xl {
    public final <T> xj<T> a(wo woVar, abt<T> abt) {
        Class a = abt.a();
        afs afs = (afs) a.getAnnotation(afs.class);
        if (afs == null || a == afs.a()) {
            return null;
        }
        return woVar.a(afs.a());
    }
}

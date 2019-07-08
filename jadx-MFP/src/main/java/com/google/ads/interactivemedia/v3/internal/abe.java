package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class abe implements xl {
    private final /* synthetic */ Class a;
    private final /* synthetic */ Class b;
    private final /* synthetic */ xj c;

    abe(Class cls, Class cls2, xj xjVar) {
        this.a = cls;
        this.b = cls2;
        this.c = xjVar;
    }

    public final <T> xj<T> a(wo woVar, abt<T> abt) {
        Class a2 = abt.a();
        if (a2 == this.a || a2 == this.b) {
            return this.c;
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Factory[type=");
        sb.append(this.b.getName());
        sb.append("+");
        sb.append(this.a.getName());
        sb.append(",adapter=");
        sb.append(this.c);
        sb.append("]");
        return sb.toString();
    }
}

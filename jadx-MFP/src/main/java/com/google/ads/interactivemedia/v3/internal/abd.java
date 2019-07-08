package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class abd implements xl {
    private final /* synthetic */ Class a;
    private final /* synthetic */ xj b;

    abd(Class cls, xj xjVar) {
        this.a = cls;
        this.b = xjVar;
    }

    public final <T> xj<T> a(wo woVar, abt<T> abt) {
        if (abt.a() == this.a) {
            return this.b;
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Factory[type=");
        sb.append(this.a.getName());
        sb.append(",adapter=");
        sb.append(this.b);
        sb.append("]");
        return sb.toString();
    }
}

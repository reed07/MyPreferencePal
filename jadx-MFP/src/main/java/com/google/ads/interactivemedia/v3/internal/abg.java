package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class abg implements xl {
    final /* synthetic */ xj a;
    private final /* synthetic */ Class b;

    abg(Class cls, xj xjVar) {
        this.b = cls;
        this.a = xjVar;
    }

    public final <T2> xj<T2> a(wo woVar, abt<T2> abt) {
        Class a2 = abt.a();
        if (!this.b.isAssignableFrom(a2)) {
            return null;
        }
        return new abh(this, a2);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Factory[typeHierarchy=");
        sb.append(this.b.getName());
        sb.append(",adapter=");
        sb.append(this.a);
        sb.append("]");
        return sb.toString();
    }
}

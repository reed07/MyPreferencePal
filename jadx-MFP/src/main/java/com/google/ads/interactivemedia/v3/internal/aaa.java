package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class aaa implements xl {
    private final abt<?> a;
    private final boolean b;
    private final Class<?> c;
    private final xg<?> d;
    private final wy<?> e;

    aaa(Object obj, abt<?> abt, boolean z, Class<?> cls) {
        this.d = obj instanceof xg ? (xg) obj : null;
        this.e = obj instanceof wy ? (wy) obj : null;
        tt.a((this.d == null && this.e == null) ? false : true);
        this.a = abt;
        this.b = z;
        this.c = null;
    }

    public final <T> xj<T> a(wo woVar, abt<T> abt) {
        abt<?> abt2 = this.a;
        boolean z = abt2 != null ? abt2.equals(abt) || (this.b && this.a.b() == abt.a()) : this.c.isAssignableFrom(abt.a());
        if (!z) {
            return null;
        }
        zy zyVar = new zy(this.d, this.e, woVar, abt, this);
        return zyVar;
    }
}

package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class agq extends agb<Object> {
    private final transient Object[] a;
    private final transient int b;
    private final transient int c;

    agq(Object[] objArr, int i, int i2) {
        this.a = objArr;
        this.b = i;
        this.c = i2;
    }

    /* access modifiers changed from: 0000 */
    public final boolean f() {
        return true;
    }

    public final Object get(int i) {
        afx.a(i, this.c);
        return this.a[(i * 2) + this.b];
    }

    public final int size() {
        return this.c;
    }
}

package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public class et {
    private int a;

    public void a() {
        this.a = 0;
    }

    public final boolean b() {
        return c(Integer.MIN_VALUE);
    }

    public final boolean c() {
        return c(4);
    }

    public final boolean d() {
        return c(1);
    }

    public final void a(int i) {
        this.a = i;
    }

    public final void b(int i) {
        this.a = i | this.a;
    }

    /* access modifiers changed from: protected */
    public final boolean c(int i) {
        return (this.a & i) == i;
    }
}

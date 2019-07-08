package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class ht {
    public hd a;
    public long b;
    public long c;
    public int d;
    public int e;
    public long[] f;
    public int[] g;
    public int[] h;
    public int[] i;
    public long[] j;
    public boolean[] k;
    public boolean l;
    public boolean[] m;
    public hs n;
    public int o;
    public ut p;
    public boolean q;
    public long r;

    ht() {
    }

    public final void a(int i2) {
        ut utVar = this.p;
        if (utVar == null || utVar.c() < i2) {
            this.p = new ut(i2);
        }
        this.o = i2;
        this.l = true;
        this.q = true;
    }

    public final long b(int i2) {
        return this.j[i2] + ((long) this.i[i2]);
    }

    public final boolean c(int i2) {
        return this.l && this.m[i2];
    }
}

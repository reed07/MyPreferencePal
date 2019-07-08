package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class cs {
    public Object a;
    public int b;
    public long c;
    private long d;
    private nc e = nc.a;

    public final cs a(Object obj, Object obj2, int i, long j, long j2) {
        nc ncVar = nc.a;
        this.a = obj2;
        this.b = 0;
        this.c = j;
        this.d = j2;
        this.e = ncVar;
        return this;
    }

    public final long a() {
        return at.a(this.d);
    }

    public final long b() {
        return this.d;
    }

    public final int c() {
        return this.e.b;
    }

    public final long a(int i) {
        return this.e.c[i];
    }

    public final int b(int i) {
        return this.e.d[i].a(-1);
    }

    public final int a(int i, int i2) {
        return this.e.d[i].a(i2);
    }

    public final int a(long j) {
        return this.e.a(j);
    }

    public final int b(long j) {
        nc ncVar = this.e;
        long j2 = this.c;
        if (j == Long.MIN_VALUE || (j2 != -9223372036854775807L && j >= j2)) {
            return -1;
        }
        int i = 0;
        while (i < ncVar.c.length && ncVar.c[i] != Long.MIN_VALUE && (j >= ncVar.c[i] || !ncVar.d[i].a())) {
            i++;
        }
        if (i < ncVar.c.length) {
            return i;
        }
        return -1;
    }

    public final int c(int i) {
        return this.e.d[i].a;
    }

    public final boolean b(int i, int i2) {
        nd ndVar = this.e.d[i];
        return (ndVar.a == -1 || ndVar.b[i2] == 0) ? false : true;
    }

    public final long c(int i, int i2) {
        nd ndVar = this.e.d[i];
        if (ndVar.a != -1) {
            return ndVar.c[i2];
        }
        return -9223372036854775807L;
    }

    public final long d() {
        return this.e.e;
    }
}

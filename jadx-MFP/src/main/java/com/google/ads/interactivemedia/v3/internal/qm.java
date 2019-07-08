package com.google.ads.interactivemedia.v3.internal;

import java.util.List;

/* compiled from: IMASDK */
public class qm implements ok, qu {
    private final qu a;
    private final List<lc> b;
    private final fn c;
    private final long d;

    public final tv<qr> a() {
        return new lb(this.a.a(), this.b);
    }

    public boolean b() {
        return true;
    }

    public long b_() {
        return 0;
    }

    public final tv<qr> a(qn qnVar) {
        return new lb(this.a.a(qnVar), this.b);
    }

    public qm(fn fnVar, long j) {
        this.c = fnVar;
        this.d = j;
    }

    public int c(long j) {
        return this.c.a;
    }

    public long a(long j) {
        return this.c.e[(int) j] - this.d;
    }

    public long b(long j, long j2) {
        return this.c.d[(int) j];
    }

    public ox b(long j) {
        int i = (int) j;
        ox oxVar = new ox(null, this.c.c[i], (long) this.c.b[i]);
        return oxVar;
    }

    public long a(long j, long j2) {
        return (long) this.c.b(j + this.d);
    }
}

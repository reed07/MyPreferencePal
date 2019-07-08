package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class vb implements um {
    private final ua a;
    private boolean b;
    private long c;
    private long d;
    private cc e = cc.a;

    public vb(ua uaVar) {
        this.a = uaVar;
    }

    public final void a() {
        if (!this.b) {
            this.d = this.a.a();
            this.b = true;
        }
    }

    public final void b() {
        if (this.b) {
            a(d());
            this.b = false;
        }
    }

    public final void a(long j) {
        this.c = j;
        if (this.b) {
            this.d = this.a.a();
        }
    }

    public final long d() {
        long j = this.c;
        if (!this.b) {
            return j;
        }
        long a2 = this.a.a() - this.d;
        if (this.e.b == 1.0f) {
            return j + at.b(a2);
        }
        return j + this.e.a(a2);
    }

    public final cc a(cc ccVar) {
        if (this.b) {
            a(d());
        }
        this.e = ccVar;
        return ccVar;
    }

    public final cc e() {
        return this.e;
    }
}

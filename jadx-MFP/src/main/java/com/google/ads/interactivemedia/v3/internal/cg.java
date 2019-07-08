package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;

/* compiled from: IMASDK */
public final class cg {
    private final ci a;
    private final ch b;
    private final cq c;
    private int d;
    private Object e;
    private Handler f;
    private int g;
    private long h = -9223372036854775807L;
    private boolean i = true;
    private boolean j;
    private boolean k;
    private boolean l;

    public cg(ch chVar, ci ciVar, cq cqVar, int i2, Handler handler) {
        this.b = chVar;
        this.a = ciVar;
        this.c = cqVar;
        this.f = handler;
        this.g = i2;
    }

    public final cq a() {
        return this.c;
    }

    public final ci b() {
        return this.a;
    }

    public final cg a(int i2) {
        qi.c(!this.j);
        this.d = i2;
        return this;
    }

    public final int c() {
        return this.d;
    }

    public final cg a(Object obj) {
        qi.c(!this.j);
        this.e = obj;
        return this;
    }

    public final Object d() {
        return this.e;
    }

    public final Handler e() {
        return this.f;
    }

    public final long f() {
        return this.h;
    }

    public final int g() {
        return this.g;
    }

    public final boolean h() {
        return this.i;
    }

    public final cg i() {
        qi.c(!this.j);
        if (this.h == -9223372036854775807L) {
            qi.b(this.i);
        }
        this.j = true;
        this.b.a(this);
        return this;
    }

    public final synchronized boolean j() {
        return false;
    }

    public final synchronized boolean k() throws InterruptedException {
        qi.c(this.j);
        qi.c(this.f.getLooper().getThread() != Thread.currentThread());
        while (!this.l) {
            wait();
        }
        return this.k;
    }

    public final synchronized void a(boolean z) {
        this.k = z | this.k;
        this.l = true;
        notifyAll();
    }
}

package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.io.IOException;

/* compiled from: IMASDK */
public class lk implements to {
    final /* synthetic */ me a;
    private final Uri b;
    /* access modifiers changed from: private */
    public final ty c;
    private final mh d;
    private final fs e;
    private final uo f;
    private final fx g = new fx();
    private volatile boolean h;
    private boolean i = true;
    /* access modifiers changed from: private */
    public long j;
    /* access modifiers changed from: private */
    public sr k = a(0);
    /* access modifiers changed from: private */
    public long l = -1;
    private gc m;
    private boolean n;

    public void a(ut utVar) {
        long max = !this.n ? this.j : Math.max(this.a.r(), this.j);
        int b2 = utVar.b();
        gc gcVar = (gc) qi.a(this.m);
        gcVar.a(utVar, b2);
        gcVar.a(max, 1, b2, 0, null);
        this.n = true;
    }

    public lk(me meVar, Uri uri, sn snVar, mh mhVar, fs fsVar, uo uoVar) {
        this.a = meVar;
        this.b = uri;
        this.c = new ty(snVar);
        this.d = mhVar;
        this.e = fsVar;
        this.f = uoVar;
    }

    public void a() {
        this.h = true;
    }

    public void b() throws IOException, InterruptedException {
        sn snVar;
        int i2 = 0;
        while (i2 == 0 && !this.h) {
            fr frVar = null;
            try {
                long j2 = this.g.a;
                this.k = a(j2);
                this.l = this.c.a(this.k);
                if (this.l != -1) {
                    this.l += j2;
                }
                Uri uri = (Uri) qi.a(this.c.a());
                this.a.r = jy.a(this.c.b());
                sn snVar2 = this.c;
                if (this.a.r == null || this.a.r.b == -1) {
                    snVar = snVar2;
                } else {
                    lj ljVar = new lj(this.c, this.a.r.b, this);
                    this.m = this.a.i();
                    this.m.a(me.a);
                    snVar = ljVar;
                }
                fr frVar2 = new fr(snVar, j2, this.l);
                try {
                    fq a2 = this.d.a(frVar2, this.e, uri);
                    if (this.i) {
                        a2.a(j2, this.j);
                        this.i = false;
                    }
                    while (i2 == 0 && !this.h) {
                        this.f.c();
                        i2 = a2.a(frVar2, this.g);
                        if (frVar2.c() > this.a.i + j2) {
                            j2 = frVar2.c();
                            this.f.b();
                            this.a.o.post(this.a.n);
                        }
                    }
                    if (i2 == 1) {
                        i2 = 0;
                    } else {
                        this.g.a = frVar2.c();
                    }
                    vf.a((sn) this.c);
                } catch (Throwable th) {
                    th = th;
                    frVar = frVar2;
                    if (!(i2 == 1 || frVar == null)) {
                        this.g.a = frVar.c();
                    }
                    vf.a((sn) this.c);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                this.g.a = frVar.c();
                vf.a((sn) this.c);
                throw th;
            }
        }
    }

    private sr a(long j2) {
        sr srVar = new sr(this.b, j2, -1, this.a.h, 22);
        return srVar;
    }

    /* access modifiers changed from: private */
    public void a(long j2, long j3) {
        this.g.a = j2;
        this.j = j3;
        this.i = true;
        this.n = false;
    }
}

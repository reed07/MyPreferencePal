package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceHolder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: IMASDK */
public final class cn extends aq implements cd {
    private final ci[] b;
    private final ay c;
    private final Handler d;
    private final cp e;
    /* access modifiers changed from: private */
    public final CopyOnWriteArraySet<vt> f;
    /* access modifiers changed from: private */
    public final CopyOnWriteArraySet<di> g;
    private final CopyOnWriteArraySet<Object> h;
    /* access modifiers changed from: private */
    public final CopyOnWriteArraySet<vu> i;
    /* access modifiers changed from: private */
    public final CopyOnWriteArraySet<dl> j;
    private final sh k;
    private final cu l;
    private final df m;
    /* access modifiers changed from: private */
    public Surface n;
    private boolean o;
    private SurfaceHolder p;
    private int q;
    private int r;
    /* access modifiers changed from: private */
    public int s;
    private float t;
    private ln u;
    private boolean v;

    protected cn(Context context, cl clVar, rz rzVar, bw bwVar, fe<fg> feVar, sh shVar, cv cvVar, Looper looper) {
        this(context, clVar, rzVar, bwVar, feVar, shVar, cvVar, ua.a, looper);
    }

    private cn(Context context, cl clVar, rz rzVar, bw bwVar, fe<fg> feVar, sh shVar, cv cvVar, ua uaVar, Looper looper) {
        sh shVar2 = shVar;
        this.k = shVar2;
        this.e = new cp(this, 0);
        this.f = new CopyOnWriteArraySet<>();
        this.g = new CopyOnWriteArraySet<>();
        new CopyOnWriteArraySet();
        this.h = new CopyOnWriteArraySet<>();
        this.i = new CopyOnWriteArraySet<>();
        this.j = new CopyOnWriteArraySet<>();
        Looper looper2 = looper;
        this.d = new Handler(looper2);
        Handler handler = this.d;
        cp cpVar = this.e;
        cl clVar2 = clVar;
        this.b = clVar.a(handler, cpVar, cpVar);
        this.t = 1.0f;
        this.s = 0;
        Collections.emptyList();
        ay ayVar = new ay(this.b, rzVar, bwVar, shVar, uaVar, looper2);
        this.c = ayVar;
        this.l = cv.a(this.c, uaVar);
        a((cf) this.l);
        this.i.add(this.l);
        this.f.add(this.l);
        this.j.add(this.l);
        this.g.add(this.l);
        this.h.add(this.l);
        shVar2.a(this.d, this.l);
        if (!(feVar instanceof ey)) {
            Context context2 = context;
            this.m = new df(context, this.e);
            return;
        }
        throw new NoSuchMethodError();
    }

    public final void a(Surface surface) {
        n();
        f();
        a((Surface) null, false);
        a(0, 0);
    }

    public final void a(SurfaceHolder surfaceHolder) {
        n();
        f();
        this.p = surfaceHolder;
        if (surfaceHolder != null) {
            surfaceHolder.addCallback(this.e);
            Surface surface = surfaceHolder.getSurface();
            if (surface != null && surface.isValid()) {
                a(surface, false);
                Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
                a(surfaceFrame.width(), surfaceFrame.height());
                return;
            }
        }
        a((Surface) null, false);
        a(0, 0);
    }

    public final void b(SurfaceHolder surfaceHolder) {
        n();
        if (surfaceHolder != null && surfaceHolder == this.p) {
            a((SurfaceHolder) null);
        }
    }

    public final void a(vt vtVar) {
        this.f.add(vtVar);
    }

    public final void b(vt vtVar) {
        this.f.remove(vtVar);
    }

    public final void a(cf cfVar) {
        n();
        this.c.a(cfVar);
    }

    public final void b(cf cfVar) {
        n();
        this.c.b(cfVar);
    }

    public final int a() {
        n();
        return this.c.b();
    }

    public final void a(ln lnVar) {
        n();
        ln lnVar2 = this.u;
        if (lnVar2 != null) {
            lnVar2.a((lq) this.l);
            this.l.c();
        }
        this.u = lnVar;
        lnVar.a(this.d, (lq) this.l);
        a(b(), this.m.a(b()));
        this.c.a(lnVar, true, true);
    }

    public final void b(boolean z) {
        n();
        a(z, this.m.a(z, a()));
    }

    public final boolean b() {
        n();
        return this.c.c();
    }

    public final void a(int i2, long j2) {
        n();
        this.l.b();
        this.c.a(i2, j2);
    }

    public final void a(boolean z) {
        n();
        this.c.a(z);
        ln lnVar = this.u;
        if (lnVar != null) {
            lnVar.a((lq) this.l);
            this.l.c();
            if (z) {
                this.u = null;
            }
        }
        this.m.b();
        Collections.emptyList();
    }

    public final void c() {
        n();
        this.m.b();
        this.c.d();
        f();
        Surface surface = this.n;
        if (surface != null) {
            if (this.o) {
                surface.release();
            }
            this.n = null;
        }
        ln lnVar = this.u;
        if (lnVar != null) {
            lnVar.a((lq) this.l);
            this.u = null;
        }
        this.k.a(this.l);
        Collections.emptyList();
    }

    public final cq l() {
        n();
        return this.c.l();
    }

    public final int e() {
        n();
        return this.c.e();
    }

    public final long d() {
        n();
        return this.c.f();
    }

    public final long g() {
        n();
        return this.c.g();
    }

    public final long h() {
        n();
        return this.c.h();
    }

    public final int i() {
        n();
        return this.c.i();
    }

    public final int j() {
        n();
        return this.c.j();
    }

    public final long k() {
        n();
        return this.c.k();
    }

    private final void f() {
        SurfaceHolder surfaceHolder = this.p;
        if (surfaceHolder != null) {
            surfaceHolder.removeCallback(this.e);
            this.p = null;
        }
    }

    /* access modifiers changed from: private */
    public final void a(Surface surface, boolean z) {
        ci[] ciVarArr;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (ci ciVar : this.b) {
            if (ciVar.a() == 2) {
                arrayList.add(this.c.a(ciVar).a(1).a((Object) surface).i());
            }
        }
        Surface surface2 = this.n;
        if (!(surface2 == null || surface2 == surface)) {
            try {
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                while (i2 < size) {
                    Object obj = arrayList2.get(i2);
                    i2++;
                    ((cg) obj).k();
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
            if (this.o) {
                this.n.release();
            }
        }
        this.n = surface;
        this.o = z;
    }

    /* access modifiers changed from: private */
    public final void a(int i2, int i3) {
        if (i2 != this.q || i3 != this.r) {
            this.q = i2;
            this.r = i3;
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                ((vt) it.next()).a(i2, i3);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void m() {
        ci[] ciVarArr;
        float a = this.t * this.m.a();
        for (ci ciVar : this.b) {
            if (ciVar.a() == 1) {
                this.c.a(ciVar).a(2).a((Object) Float.valueOf(a)).i();
            }
        }
    }

    /* access modifiers changed from: private */
    public final void a(boolean z, int i2) {
        ay ayVar = this.c;
        boolean z2 = false;
        boolean z3 = z && i2 != -1;
        if (i2 != 1) {
            z2 = true;
        }
        ayVar.a(z3, z2);
    }

    private final void n() {
        if (Looper.myLooper() != this.c.a()) {
            uk.a("SimpleExoPlayer", "Player is accessed on the wrong thread. See https://google.github.io/ExoPlayer/faqs.html#what-do-player-is-accessed-on-the-wrong-thread-warnings-mean", this.v ? null : new IllegalStateException());
            this.v = true;
        }
    }
}

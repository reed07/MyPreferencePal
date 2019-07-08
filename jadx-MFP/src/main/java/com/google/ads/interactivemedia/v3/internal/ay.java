package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: IMASDK */
final class ay extends aq implements cd {
    private final sb b;
    private final rz c;
    private final Handler d;
    private final bk e;
    private final Handler f;
    private final CopyOnWriteArrayList<ar> g;
    private final cs h;
    private final ArrayDeque<Runnable> i;
    private boolean j;
    private boolean k;
    private int l;
    private boolean m;
    private int n;
    private boolean o;
    private boolean p;
    private cc q;
    private cb r;
    private int s;
    private int t;
    private long u;

    @SuppressLint({"HandlerLeak"})
    public ay(ci[] ciVarArr, rz rzVar, bw bwVar, sh shVar, ua uaVar, Looper looper) {
        ci[] ciVarArr2 = ciVarArr;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        String str = vf.e;
        StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + 29 + String.valueOf(str).length());
        sb.append("Init ");
        sb.append(hexString);
        sb.append(" [ExoPlayerLib/2.9.6");
        sb.append("] [");
        sb.append(str);
        sb.append("]");
        Log.i("ExoPlayerImpl", sb.toString());
        qi.c(ciVarArr2.length > 0);
        qi.a(ciVarArr);
        this.c = (rz) qi.a(rzVar);
        this.j = false;
        this.l = 0;
        this.m = false;
        this.g = new CopyOnWriteArrayList<>();
        this.b = new sb(new ck[ciVarArr2.length], new rt[ciVarArr2.length], null);
        this.h = new cs();
        this.q = cc.a;
        this.d = new bc(this, looper);
        this.r = cb.a(0, this.b);
        this.i = new ArrayDeque<>();
        bk bkVar = new bk(ciVarArr, rzVar, this.b, bwVar, shVar, this.j, 0, false, this.d, uaVar);
        this.e = bkVar;
        this.f = new Handler(this.e.b());
    }

    public final Looper a() {
        return this.d.getLooper();
    }

    public final void a(cf cfVar) {
        this.g.addIfAbsent(new ar(cfVar));
    }

    public final void b(cf cfVar) {
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            ar arVar = (ar) it.next();
            if (arVar.a.equals(cfVar)) {
                arVar.a();
                this.g.remove(arVar);
            }
        }
    }

    public final int b() {
        return this.r.f;
    }

    public final void a(ln lnVar, boolean z, boolean z2) {
        cb a = a(z, z2, 2);
        this.o = true;
        this.n++;
        this.e.a(lnVar, z, z2);
        a(a, false, 4, 1, false);
    }

    public final void a(boolean z, boolean z2) {
        boolean z3 = z && !z2;
        if (this.k != z3) {
            this.k = z3;
            this.e.a(z3);
        }
        if (this.j != z) {
            this.j = z;
            a((as) new bm(z, this.r.f));
        }
    }

    public final boolean c() {
        return this.j;
    }

    public final void a(int i2, long j2) {
        long j3;
        cq cqVar = this.r.a;
        if (i2 < 0 || (!cqVar.a() && i2 >= cqVar.b())) {
            throw new bv(cqVar, i2, j2);
        }
        this.p = true;
        this.n++;
        if (m()) {
            Log.w("ExoPlayerImpl", "seekTo ignored because an ad is playing");
            this.d.obtainMessage(0, 1, -1, this.r).sendToTarget();
            return;
        }
        this.s = i2;
        if (cqVar.a()) {
            this.u = j2 == -9223372036854775807L ? 0 : j2;
            this.t = 0;
        } else {
            if (j2 == -9223372036854775807L) {
                j3 = cqVar.a(i2, this.a, false, 0).d;
            } else {
                j3 = at.b(j2);
            }
            long j4 = j3;
            Pair a = cqVar.a(this.a, this.h, i2, j4);
            this.u = at.a(j4);
            this.t = cqVar.a(a.first);
        }
        this.e.a(cqVar, i2, at.b(j2));
        a(az.a);
    }

    public final void a(boolean z) {
        cb a = a(z, z, 1);
        this.n++;
        this.e.b(z);
        a(a, false, 4, 1, false);
    }

    public final void d() {
        String hexString = Integer.toHexString(System.identityHashCode(this));
        String str = vf.e;
        String a = br.a();
        StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + 35 + String.valueOf(str).length() + String.valueOf(a).length());
        sb.append("Release ");
        sb.append(hexString);
        sb.append(" [ExoPlayerLib/2.9.6");
        sb.append("] [");
        sb.append(str);
        sb.append("] [");
        sb.append(a);
        sb.append("]");
        Log.i("ExoPlayerImpl", sb.toString());
        this.e.a();
        this.d.removeCallbacksAndMessages(null);
    }

    public final cg a(ci ciVar) {
        cg cgVar = new cg(this.e, ciVar, this.r.a, e(), this.f);
        return cgVar;
    }

    public final int e() {
        if (n()) {
            return this.s;
        }
        return this.r.a.a(this.r.c.a, this.h).b;
    }

    public final long f() {
        if (m()) {
            lo loVar = this.r.c;
            this.r.a.a(loVar.a, this.h);
            return at.a(this.h.c(loVar.b, loVar.c));
        }
        cq l2 = l();
        if (l2.a()) {
            return -9223372036854775807L;
        }
        return at.a(l2.a(e(), this.a, false, 0).e);
    }

    public final long g() {
        if (n()) {
            return this.u;
        }
        if (this.r.c.a()) {
            return at.a(this.r.m);
        }
        lo loVar = this.r.c;
        long a = at.a(this.r.m);
        this.r.a.a(loVar.a, this.h);
        return a + this.h.a();
    }

    public final long h() {
        return Math.max(0, at.a(this.r.l));
    }

    private final boolean m() {
        return !n() && this.r.c.a();
    }

    public final int i() {
        if (m()) {
            return this.r.c.b;
        }
        return -1;
    }

    public final int j() {
        if (m()) {
            return this.r.c.c;
        }
        return -1;
    }

    public final long k() {
        if (!m()) {
            return g();
        }
        this.r.a.a(this.r.c.a, this.h);
        return this.h.a() + at.a(this.r.e);
    }

    public final cq l() {
        return this.r.a;
    }

    /* access modifiers changed from: 0000 */
    public final void a(Message message) {
        switch (message.what) {
            case 0:
                cb cbVar = (cb) message.obj;
                int i2 = message.arg1;
                boolean z = message.arg2 != -1;
                int i3 = message.arg2;
                this.n -= i2;
                if (this.n == 0) {
                    cb a = cbVar.d == -9223372036854775807L ? cbVar.a(cbVar.c, 0, cbVar.e) : cbVar;
                    if ((!this.r.a.a() || this.o) && a.a.a()) {
                        this.t = 0;
                        this.s = 0;
                        this.u = 0;
                    }
                    int i4 = this.o ? 0 : 2;
                    boolean z2 = this.p;
                    this.o = false;
                    this.p = false;
                    a(a, z, i3, i4, z2);
                }
                return;
            case 1:
                cc ccVar = (cc) message.obj;
                if (!this.q.equals(ccVar)) {
                    this.q = ccVar;
                    a((as) new co(ccVar));
                    return;
                }
                return;
            case 2:
                a((as) new ba((aw) message.obj));
                return;
            default:
                throw new IllegalStateException();
        }
    }

    private final cb a(boolean z, boolean z2, int i2) {
        lo loVar;
        long j2;
        Object obj;
        int i3;
        long j3 = 0;
        if (z) {
            this.s = 0;
            this.t = 0;
            this.u = 0;
        } else {
            this.s = e();
            if (n()) {
                i3 = this.t;
            } else {
                i3 = this.r.a.a(this.r.c.a);
            }
            this.t = i3;
            this.u = g();
        }
        boolean z3 = z || z2;
        if (z3) {
            loVar = this.r.a(false, this.a);
        } else {
            loVar = this.r.c;
        }
        if (!z3) {
            j3 = this.r.m;
        }
        long j4 = j3;
        if (z3) {
            j2 = -9223372036854775807L;
        } else {
            j2 = this.r.e;
        }
        long j5 = j2;
        cq cqVar = z2 ? cq.a : this.r.a;
        if (z2) {
            obj = null;
        } else {
            obj = this.r.b;
        }
        cb cbVar = new cb(cqVar, obj, loVar, j4, j5, i2, false, z2 ? mz.a : this.r.h, z2 ? this.b : this.r.i, loVar, j4, 0, j4);
        return cbVar;
    }

    private final void a(cb cbVar, boolean z, int i2, int i3, boolean z2) {
        cb cbVar2 = this.r;
        cb cbVar3 = cbVar;
        this.r = cbVar3;
        bd bdVar = new bd(cbVar3, cbVar2, this.g, this.c, z, i2, i3, z2, this.j);
        a((Runnable) bdVar);
    }

    private final void a(as asVar) {
        a((Runnable) new bb(new CopyOnWriteArrayList(this.g), asVar));
    }

    private final void a(Runnable runnable) {
        boolean z = !this.i.isEmpty();
        this.i.addLast(runnable);
        if (!z) {
            while (!this.i.isEmpty()) {
                ((Runnable) this.i.peekFirst()).run();
                this.i.removeFirst();
            }
        }
    }

    private final boolean n() {
        return this.r.a.a() || this.n > 0;
    }

    /* access modifiers changed from: private */
    public static void c(CopyOnWriteArrayList<ar> copyOnWriteArrayList, as asVar) {
        Iterator it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            ((ar) it.next()).a(asVar);
        }
    }
}

package com.google.ads.interactivemedia.v3.internal;

import android.view.Surface;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: IMASDK */
public final class cu implements cf, di, dl, lq, si, vt, vu {
    private final CopyOnWriteArraySet<cy> a;
    private final ua b;
    private final ct c;
    private final cx d;
    private cd e;

    protected cu(cd cdVar, ua uaVar) {
        if (cdVar != null) {
            this.e = cdVar;
        }
        this.b = (ua) qi.a(uaVar);
        this.a = new CopyOnWriteArraySet<>();
        this.d = new cx();
        this.c = new ct();
    }

    public final void d() {
    }

    public final void b() {
        if (!this.d.e()) {
            f();
            this.d.g();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((cy) it.next()).d();
            }
        }
    }

    public final void c() {
        ArrayList arrayList = new ArrayList(this.d.a);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            cw cwVar = (cw) obj;
            b(cwVar.c, cwVar.a);
        }
    }

    public final void c(ew ewVar) {
        f();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).u();
        }
    }

    public final void b(String str, long j, long j2) {
        g();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).v();
        }
    }

    public final void b(bs bsVar) {
        g();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).w();
        }
    }

    public final void a(int i, long j, long j2) {
        g();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).z();
        }
    }

    public final void d(ew ewVar) {
        e();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).x();
        }
    }

    public final void a(int i) {
        g();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).y();
        }
    }

    public final void a(ew ewVar) {
        f();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).u();
        }
    }

    public final void a(String str, long j, long j2) {
        g();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).v();
        }
    }

    public final void a(bs bsVar) {
        g();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).w();
        }
    }

    public final void a(int i, long j) {
        e();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).A();
        }
    }

    public final void b(ew ewVar) {
        e();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).x();
        }
    }

    public final void a(Surface surface) {
        g();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).C();
        }
    }

    public final void a(int i, int i2, int i3, float f) {
        g();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).B();
        }
    }

    public final void a(int i, int i2) {
        g();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).t();
        }
    }

    public final void a(int i, lo loVar) {
        this.d.a(i, loVar);
        d(i, loVar);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).p();
        }
    }

    public final void b(int i, lo loVar) {
        d(i, loVar);
        if (this.d.b(loVar)) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((cy) it.next()).q();
            }
        }
    }

    public final void a(int i, lo loVar, mc mcVar, md mdVar) {
        d(i, loVar);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).j();
        }
    }

    public final void b(int i, lo loVar, mc mcVar, md mdVar) {
        d(i, loVar);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).k();
        }
    }

    public final void c(int i, lo loVar, mc mcVar, md mdVar) {
        d(i, loVar);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).l();
        }
    }

    public final void a(int i, lo loVar, mc mcVar, md mdVar, IOException iOException, boolean z) {
        d(i, loVar);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).m();
        }
    }

    public final void c(int i, lo loVar) {
        this.d.c(loVar);
        d(i, loVar);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).r();
        }
    }

    public final void a(int i, lo loVar, md mdVar) {
        d(i, loVar);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).o();
        }
    }

    public final void b(int i, lo loVar, md mdVar) {
        d(i, loVar);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).n();
        }
    }

    public final void a(cq cqVar, Object obj, int i) {
        this.d.a(cqVar);
        f();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).b();
        }
    }

    public final void a(mz mzVar, rw rwVar) {
        f();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).i();
        }
    }

    public final void a(boolean z) {
        f();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).g();
        }
    }

    public final void a(boolean z, int i) {
        f();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).a();
        }
    }

    public final void a(aw awVar) {
        if (awVar.a == 0) {
            h();
        } else {
            f();
        }
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).h();
        }
    }

    public final void a_(int i) {
        this.d.f();
        f();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).c();
        }
    }

    public final void a(cc ccVar) {
        f();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).f();
        }
    }

    public final void a() {
        if (this.d.e()) {
            this.d.h();
            f();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((cy) it.next()).e();
            }
        }
    }

    public final void b(int i, long j, long j2) {
        h();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((cy) it.next()).s();
        }
    }

    private final cz a(cq cqVar, int i, lo loVar) {
        lo loVar2 = cqVar.a() ? null : loVar;
        long a2 = this.b.a();
        boolean z = true;
        boolean z2 = cqVar == this.e.l() && i == this.e.e();
        long j = 0;
        if (loVar2 != null && loVar2.a()) {
            if (!(z2 && this.e.i() == loVar2.b && this.e.j() == loVar2.c)) {
                z = false;
            }
            if (z) {
                j = this.e.g();
            }
        } else if (z2) {
            j = this.e.k();
        } else if (!cqVar.a()) {
            j = at.a(cqVar.a(i, this.c).d);
        }
        cz czVar = new cz(a2, cqVar, i, loVar2, j, this.e.g(), this.e.h());
        return czVar;
    }

    private final cz a(cw cwVar) {
        qi.a(this.e);
        if (cwVar == null) {
            int e2 = this.e.e();
            cw a2 = this.d.a(e2);
            if (a2 == null) {
                cq l = this.e.l();
                if (!(e2 < l.b())) {
                    l = cq.a;
                }
                return a(l, e2, (lo) null);
            }
            cwVar = a2;
        }
        return a(cwVar.b, cwVar.c, cwVar.a);
    }

    private final cz e() {
        return a(this.d.b());
    }

    private final cz f() {
        return a(this.d.a());
    }

    private final cz g() {
        return a(this.d.c());
    }

    private final cz h() {
        return a(this.d.d());
    }

    private final cz d(int i, lo loVar) {
        qi.a(this.e);
        if (loVar != null) {
            cw a2 = this.d.a(loVar);
            if (a2 != null) {
                return a(a2);
            }
            return a(cq.a, i, loVar);
        }
        cq l = this.e.l();
        if (!(i < l.b())) {
            l = cq.a;
        }
        return a(l, i, (lo) null);
    }
}

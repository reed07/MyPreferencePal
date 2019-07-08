package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: IMASDK */
final class cx {
    /* access modifiers changed from: private */
    public final ArrayList<cw> a = new ArrayList<>();
    private final HashMap<lo, cw> b = new HashMap<>();
    private final cs c = new cs();
    private cw d;
    private cw e;
    private cq f = cq.a;
    private boolean g;

    public final cw a() {
        if (this.a.isEmpty() || this.f.a() || this.g) {
            return null;
        }
        return (cw) this.a.get(0);
    }

    public final cw b() {
        return this.d;
    }

    public final cw c() {
        return this.e;
    }

    public final cw d() {
        if (this.a.isEmpty()) {
            return null;
        }
        ArrayList<cw> arrayList = this.a;
        return (cw) arrayList.get(arrayList.size() - 1);
    }

    public final cw a(lo loVar) {
        return (cw) this.b.get(loVar);
    }

    public final boolean e() {
        return this.g;
    }

    public final cw a(int i) {
        cw cwVar = null;
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            cw cwVar2 = (cw) this.a.get(i2);
            int a2 = this.f.a(cwVar2.a.a);
            if (a2 != -1 && this.f.a(a2, this.c, false).b == i) {
                if (cwVar != null) {
                    return null;
                }
                cwVar = cwVar2;
            }
        }
        return cwVar;
    }

    public final void f() {
        i();
    }

    public final void a(cq cqVar) {
        for (int i = 0; i < this.a.size(); i++) {
            cw a2 = a((cw) this.a.get(i), cqVar);
            this.a.set(i, a2);
            this.b.put(a2.a, a2);
        }
        cw cwVar = this.e;
        if (cwVar != null) {
            this.e = a(cwVar, cqVar);
        }
        this.f = cqVar;
        i();
    }

    public final void g() {
        this.g = true;
    }

    public final void h() {
        this.g = false;
        i();
    }

    public final void a(int i, lo loVar) {
        cw cwVar = new cw(loVar, this.f.a(loVar.a) != -1 ? this.f : cq.a, i);
        this.a.add(cwVar);
        this.b.put(loVar, cwVar);
        if (this.a.size() == 1 && !this.f.a()) {
            i();
        }
    }

    public final boolean b(lo loVar) {
        cw cwVar = (cw) this.b.remove(loVar);
        if (cwVar == null) {
            return false;
        }
        this.a.remove(cwVar);
        cw cwVar2 = this.e;
        if (cwVar2 != null && loVar.equals(cwVar2.a)) {
            this.e = this.a.isEmpty() ? null : (cw) this.a.get(0);
        }
        return true;
    }

    public final void c(lo loVar) {
        this.e = (cw) this.b.get(loVar);
    }

    private final void i() {
        if (!this.a.isEmpty()) {
            this.d = (cw) this.a.get(0);
        }
    }

    private final cw a(cw cwVar, cq cqVar) {
        int a2 = cqVar.a(cwVar.a.a);
        if (a2 == -1) {
            return cwVar;
        }
        return new cw(cwVar.a, cqVar, cqVar.a(a2, this.c, false).b);
    }
}

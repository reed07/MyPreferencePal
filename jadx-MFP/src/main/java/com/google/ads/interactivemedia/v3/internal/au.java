package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class au implements um {
    private final vb a;
    private final av b;
    private ci c;
    private um d;

    public au(av avVar, ua uaVar) {
        this.b = avVar;
        this.a = new vb(uaVar);
    }

    public final void a() {
        this.a.a();
    }

    public final void b() {
        this.a.b();
    }

    public final void a(long j) {
        this.a.a(j);
    }

    public final void a(ci ciVar) throws aw {
        um c2 = ciVar.c();
        if (c2 != null) {
            um umVar = this.d;
            if (c2 == umVar) {
                return;
            }
            if (umVar == null) {
                this.d = c2;
                this.c = ciVar;
                this.d.a(this.a.e());
                f();
                return;
            }
            throw aw.a((RuntimeException) new IllegalStateException("Multiple renderer media clocks enabled."));
        }
    }

    public final void b(ci ciVar) {
        if (ciVar == this.c) {
            this.d = null;
            this.c = null;
        }
    }

    public final long c() {
        if (!g()) {
            return this.a.d();
        }
        f();
        return this.d.d();
    }

    public final long d() {
        if (g()) {
            return this.d.d();
        }
        return this.a.d();
    }

    public final cc a(cc ccVar) {
        um umVar = this.d;
        if (umVar != null) {
            ccVar = umVar.a(ccVar);
        }
        this.a.a(ccVar);
        this.b.a(ccVar);
        return ccVar;
    }

    public final cc e() {
        um umVar = this.d;
        if (umVar != null) {
            return umVar.e();
        }
        return this.a.e();
    }

    private final void f() {
        this.a.a(this.d.d());
        cc e = this.d.e();
        if (!e.equals(this.a.e())) {
            this.a.a(e);
            this.b.a(e);
        }
    }

    private final boolean g() {
        ci ciVar = this.c;
        return ciVar != null && !ciVar.o() && (this.c.n() || !this.c.i());
    }
}

package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
class he {
    public final int a;
    public int b;
    public int c;
    public long d;
    private final boolean e;
    private final ut f;
    private final ut g;
    private int h;
    private int i;

    public he(ut utVar, ut utVar2, boolean z) {
        this.g = utVar;
        this.f = utVar2;
        this.e = z;
        utVar2.c(12);
        this.a = utVar2.p();
        utVar.c(12);
        this.i = utVar.p();
        boolean z2 = true;
        if (utVar.l() != 1) {
            z2 = false;
        }
        qi.b(z2, (Object) "first_chunk must be 1");
        this.b = -1;
    }

    public boolean a() {
        long j;
        int i2 = this.b + 1;
        this.b = i2;
        if (i2 == this.a) {
            return false;
        }
        if (this.e) {
            j = this.f.q();
        } else {
            j = this.f.j();
        }
        this.d = j;
        if (this.b == this.h) {
            this.c = this.g.p();
            this.g.d(4);
            int i3 = this.i - 1;
            this.i = i3;
            this.h = i3 > 0 ? this.g.p() - 1 : -1;
        }
        return true;
    }
}

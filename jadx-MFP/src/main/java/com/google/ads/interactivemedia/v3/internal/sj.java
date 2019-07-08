package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.Map;

/* compiled from: IMASDK */
public abstract class sj implements sn {
    private final boolean a;
    private final ArrayList<tz> b = new ArrayList<>(1);
    private int c;
    private sr d;

    protected sj(boolean z) {
        this.a = z;
    }

    public Map b() {
        return qi.c();
    }

    public final void a(tz tzVar) {
        if (!this.b.contains(tzVar)) {
            this.b.add(tzVar);
            this.c++;
        }
    }

    /* access modifiers changed from: protected */
    public final void b(sr srVar) {
        for (int i = 0; i < this.c; i++) {
            ((tz) this.b.get(i)).c();
        }
    }

    /* access modifiers changed from: protected */
    public final void c(sr srVar) {
        this.d = srVar;
        for (int i = 0; i < this.c; i++) {
            ((tz) this.b.get(i)).a(this.a);
        }
    }

    /* access modifiers changed from: protected */
    public final void a(int i) {
        vf.a(this.d);
        for (int i2 = 0; i2 < this.c; i2++) {
            ((tz) this.b.get(i2)).a(this.a, i);
        }
    }

    /* access modifiers changed from: protected */
    public final void d() {
        vf.a(this.d);
        for (int i = 0; i < this.c; i++) {
            ((tz) this.b.get(i)).b(this.a);
        }
        this.d = null;
    }
}

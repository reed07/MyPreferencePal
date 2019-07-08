package com.google.ads.interactivemedia.v3.internal;

import android.util.SparseArray;

/* compiled from: IMASDK */
public final class nh implements fs {
    public final fq a;
    private final int b;
    private final bs c;
    private final SparseArray<ni> d = new SparseArray<>();
    private boolean e;
    private nj f;
    private long g;
    private fy h;
    private bs[] i;

    public nh(fq fqVar, int i2, bs bsVar) {
        this.a = fqVar;
        this.b = i2;
        this.c = bsVar;
    }

    public final fy b() {
        return this.h;
    }

    public final bs[] c() {
        return this.i;
    }

    public final void a(nj njVar, long j, long j2) {
        this.f = njVar;
        this.g = j2;
        if (!this.e) {
            this.a.a((fs) this);
            if (j != -9223372036854775807L) {
                this.a.a(0, j);
            }
            this.e = true;
            return;
        }
        fq fqVar = this.a;
        if (j == -9223372036854775807L) {
            j = 0;
        }
        fqVar.a(0, j);
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            ((ni) this.d.valueAt(i2)).a(njVar, j2);
        }
    }

    public final gc a(int i2, int i3) {
        ni niVar = (ni) this.d.get(i2);
        if (niVar == null) {
            qi.c(this.i == null);
            niVar = new ni(i2, i3, i3 == this.b ? this.c : null);
            niVar.a(this.f, this.g);
            this.d.put(i2, niVar);
        }
        return niVar;
    }

    public final void a() {
        bs[] bsVarArr = new bs[this.d.size()];
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            bsVarArr[i2] = ((ni) this.d.valueAt(i2)).a;
        }
        this.i = bsVarArr;
    }

    public final void a(fy fyVar) {
        this.h = fyVar;
    }
}

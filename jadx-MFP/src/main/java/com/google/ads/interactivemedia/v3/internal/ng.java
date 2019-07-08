package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
public abstract class ng implements to {
    public final sr c;
    public final int d;
    public final bs e;
    public final int f;
    public final Object g;
    public final long h;
    public final long i;
    protected final ty j;

    public ng(sn snVar, sr srVar, int i2, bs bsVar, int i3, Object obj, long j2, long j3) {
        this.j = new ty(snVar);
        this.c = (sr) qi.a(srVar);
        this.d = i2;
        this.e = bsVar;
        this.f = i3;
        this.g = obj;
        this.h = j2;
        this.i = j3;
    }

    public final long d() {
        return this.j.e();
    }

    public final Uri e() {
        return this.j.f();
    }

    public final Map<String, List<String>> f() {
        return this.j.g();
    }
}

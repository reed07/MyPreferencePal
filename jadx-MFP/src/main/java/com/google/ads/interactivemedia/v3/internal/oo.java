package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class oo implements mt {
    private final bs a;
    private final jx b = new jx();
    private long[] c;
    private boolean d;
    private yu e;
    private boolean f;
    private int g;
    private long h = -9223372036854775807L;

    public oo(yu yuVar, bs bsVar, boolean z) {
        this.a = bsVar;
        this.e = yuVar;
        this.c = yuVar.b;
        a(yuVar, z);
    }

    public final boolean b() {
        return true;
    }

    public final void c() throws IOException {
    }

    public final String a() {
        return this.e.a();
    }

    public final void a(yu yuVar, boolean z) {
        int i = this.g;
        long j = i == 0 ? -9223372036854775807L : this.c[i - 1];
        this.d = z;
        this.e = yuVar;
        this.c = yuVar.b;
        long j2 = this.h;
        if (j2 != -9223372036854775807L) {
            b(j2);
            return;
        }
        if (j != -9223372036854775807L) {
            this.g = vf.b(this.c, j, false, false);
        }
    }

    public final void b(long j) {
        boolean z = false;
        this.g = vf.b(this.c, j, true, false);
        if (this.d && this.g == this.c.length) {
            z = true;
        }
        if (!z) {
            j = -9223372036854775807L;
        }
        this.h = j;
    }

    public final int a(bu buVar, ex exVar, boolean z) {
        if (z || !this.f) {
            buVar.a = this.a;
            this.f = true;
            return -5;
        }
        int i = this.g;
        if (i != this.c.length) {
            this.g = i + 1;
            byte[] a2 = this.b.a(this.e.a[i]);
            if (a2 == null) {
                return -3;
            }
            exVar.d(a2.length);
            exVar.a(1);
            exVar.b.put(a2);
            exVar.c = this.c[i];
            return -4;
        } else if (this.d) {
            return -3;
        } else {
            exVar.a(4);
            return -4;
        }
    }

    public final int a_(long j) {
        int max = Math.max(this.g, vf.b(this.c, j, true, false));
        int i = max - this.g;
        this.g = max;
        return i;
    }
}

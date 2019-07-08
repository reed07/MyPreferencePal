package com.google.ads.interactivemedia.v3.internal;

import android.os.SystemClock;
import java.util.Arrays;
import java.util.List;

/* compiled from: IMASDK */
public abstract class rg implements rt {
    protected final int a;
    private final mx b;
    private final int[] c;
    private final bs[] d;
    private final long[] e;
    private int f;

    public rg(mx mxVar, int... iArr) {
        int i = 0;
        qi.c(iArr.length > 0);
        this.b = (mx) qi.a(mxVar);
        this.a = iArr.length;
        this.d = new bs[this.a];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.d[i2] = mxVar.a(iArr[i2]);
        }
        Arrays.sort(this.d, new rh(0));
        this.c = new int[this.a];
        while (true) {
            int i3 = this.a;
            if (i < i3) {
                this.c[i] = mxVar.a(this.d[i]);
                i++;
            } else {
                this.e = new long[i3];
                return;
            }
        }
    }

    public void a(float f2) {
    }

    public final void a(long j, long j2, long j3) {
        qi.b();
    }

    public void a(long j, long j2, long j3, List list, nt[] ntVarArr) {
        qi.a(this, j, j2, j3);
    }

    public void d() {
    }

    public final void e() {
    }

    public final void j() {
    }

    public final mx f() {
        return this.b;
    }

    public final int g() {
        return this.c.length;
    }

    public final bs a(int i) {
        return this.d[i];
    }

    public final int b(int i) {
        return this.c[i];
    }

    public final int a(bs bsVar) {
        for (int i = 0; i < this.a; i++) {
            if (this.d[i] == bsVar) {
                return i;
            }
        }
        return -1;
    }

    public final int c(int i) {
        for (int i2 = 0; i2 < this.a; i2++) {
            if (this.c[i2] == i) {
                return i2;
            }
        }
        return -1;
    }

    public final bs h() {
        return this.d[a()];
    }

    public final int i() {
        return this.c[a()];
    }

    public int a(long j, List<? extends ns> list) {
        return list.size();
    }

    public final boolean a(int i, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean b2 = b(i, elapsedRealtime);
        int i2 = 0;
        while (i2 < this.a && !b2) {
            b2 = i2 != i && !b(i2, elapsedRealtime);
            i2++;
        }
        if (!b2) {
            return false;
        }
        long[] jArr = this.e;
        jArr[i] = Math.max(jArr[i], vf.b(elapsedRealtime, j, Long.MAX_VALUE));
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean b(int i, long j) {
        return this.e[i] > j;
    }

    public int hashCode() {
        if (this.f == 0) {
            this.f = (System.identityHashCode(this.b) * 31) + Arrays.hashCode(this.c);
        }
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        rg rgVar = (rg) obj;
        return this.b == rgVar.b && Arrays.equals(this.c, rgVar.c);
    }
}

package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
final class mo {
    private int a = 1000;
    private int[] b;
    private long[] c;
    private int[] d;
    private int[] e;
    private long[] f;
    private gd[] g;
    private bs[] h;
    private int i;
    private int j;
    private int k;
    private int l;
    private long m;
    private long n;
    private boolean o;
    private boolean p;
    private boolean q;
    private bs r;
    private int s;

    public mo() {
        int i2 = this.a;
        this.b = new int[i2];
        this.c = new long[i2];
        this.f = new long[i2];
        this.e = new int[i2];
        this.d = new int[i2];
        this.g = new gd[i2];
        this.h = new bs[i2];
        this.m = Long.MIN_VALUE;
        this.n = Long.MIN_VALUE;
        this.q = true;
        this.p = true;
    }

    public final void a(boolean z) {
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.p = true;
        this.m = Long.MIN_VALUE;
        this.n = Long.MIN_VALUE;
        this.o = false;
        if (z) {
            this.r = null;
            this.q = true;
        }
    }

    public final int a() {
        return this.j + this.i;
    }

    public final long a(int i2) {
        int a2 = a() - i2;
        boolean z = false;
        qi.b(a2 >= 0 && a2 <= this.i - this.l);
        this.i -= a2;
        this.n = Math.max(this.m, e(this.i));
        if (a2 == 0 && this.o) {
            z = true;
        }
        this.o = z;
        int i3 = this.i;
        if (i3 == 0) {
            return 0;
        }
        int f2 = f(i3 - 1);
        return this.c[f2] + ((long) this.d[f2]);
    }

    public final void b(int i2) {
        this.s = i2;
    }

    public final int b() {
        return this.j;
    }

    public final int c() {
        return this.j + this.l;
    }

    public final int d() {
        return e() ? this.b[f(this.l)] : this.s;
    }

    public final synchronized boolean e() {
        return this.l != this.i;
    }

    public final synchronized bs f() {
        if (this.q) {
            return null;
        }
        return this.r;
    }

    public final synchronized long g() {
        return this.n;
    }

    public final synchronized boolean h() {
        return this.o;
    }

    public final synchronized long i() {
        if (this.i == 0) {
            return Long.MIN_VALUE;
        }
        return this.f[this.k];
    }

    public final synchronized void j() {
        this.l = 0;
    }

    public final synchronized int a(bu buVar, ex exVar, boolean z, boolean z2, bs bsVar, mp mpVar) {
        if (!e()) {
            if (!z2) {
                if (!this.o) {
                    if (this.r == null || (!z && this.r == bsVar)) {
                        return -3;
                    }
                    buVar.a = this.r;
                    return -5;
                }
            }
            exVar.a(4);
            return -4;
        }
        int f2 = f(this.l);
        if (!z) {
            if (this.h[f2] == bsVar) {
                exVar.a(this.e[f2]);
                exVar.c = this.f[f2];
                if (exVar.e()) {
                    return -4;
                }
                mpVar.a = this.d[f2];
                mpVar.b = this.c[f2];
                mpVar.c = this.g[f2];
                this.l++;
                return -4;
            }
        }
        buVar.a = this.h[f2];
        return -5;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0038, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized int a(long r9, boolean r11, boolean r12) {
        /*
            r8 = this;
            monitor-enter(r8)
            int r0 = r8.l     // Catch:{ all -> 0x0039 }
            int r2 = r8.f(r0)     // Catch:{ all -> 0x0039 }
            boolean r0 = r8.e()     // Catch:{ all -> 0x0039 }
            r7 = -1
            if (r0 == 0) goto L_0x0037
            long[] r0 = r8.f     // Catch:{ all -> 0x0039 }
            r3 = r0[r2]     // Catch:{ all -> 0x0039 }
            int r0 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r0 < 0) goto L_0x0037
            long r0 = r8.n     // Catch:{ all -> 0x0039 }
            int r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r3 <= 0) goto L_0x001f
            if (r12 != 0) goto L_0x001f
            goto L_0x0037
        L_0x001f:
            int r12 = r8.i     // Catch:{ all -> 0x0039 }
            int r0 = r8.l     // Catch:{ all -> 0x0039 }
            int r3 = r12 - r0
            r1 = r8
            r4 = r9
            r6 = r11
            int r9 = r1.a(r2, r3, r4, r6)     // Catch:{ all -> 0x0039 }
            if (r9 != r7) goto L_0x0030
            monitor-exit(r8)
            return r7
        L_0x0030:
            int r10 = r8.l     // Catch:{ all -> 0x0039 }
            int r10 = r10 + r9
            r8.l = r10     // Catch:{ all -> 0x0039 }
            monitor-exit(r8)
            return r9
        L_0x0037:
            monitor-exit(r8)
            return r7
        L_0x0039:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.mo.a(long, boolean, boolean):int");
    }

    public final synchronized int k() {
        int i2;
        i2 = this.i - this.l;
        this.l = this.i;
        return i2;
    }

    public final synchronized boolean c(int i2) {
        if (this.j > i2 || i2 > this.j + this.i) {
            return false;
        }
        this.l = i2 - this.j;
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0037, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized long b(long r10, boolean r12, boolean r13) {
        /*
            r9 = this;
            monitor-enter(r9)
            int r0 = r9.i     // Catch:{ all -> 0x0038 }
            r1 = -1
            if (r0 == 0) goto L_0x0036
            long[] r0 = r9.f     // Catch:{ all -> 0x0038 }
            int r3 = r9.k     // Catch:{ all -> 0x0038 }
            r3 = r0[r3]     // Catch:{ all -> 0x0038 }
            int r0 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x0012
            goto L_0x0036
        L_0x0012:
            if (r13 == 0) goto L_0x001f
            int r13 = r9.l     // Catch:{ all -> 0x0038 }
            int r0 = r9.i     // Catch:{ all -> 0x0038 }
            if (r13 == r0) goto L_0x001f
            int r13 = r9.l     // Catch:{ all -> 0x0038 }
            int r13 = r13 + 1
            goto L_0x0021
        L_0x001f:
            int r13 = r9.i     // Catch:{ all -> 0x0038 }
        L_0x0021:
            r5 = r13
            int r4 = r9.k     // Catch:{ all -> 0x0038 }
            r3 = r9
            r6 = r10
            r8 = r12
            int r10 = r3.a(r4, r5, r6, r8)     // Catch:{ all -> 0x0038 }
            r11 = -1
            if (r10 != r11) goto L_0x0030
            monitor-exit(r9)
            return r1
        L_0x0030:
            long r10 = r9.d(r10)     // Catch:{ all -> 0x0038 }
            monitor-exit(r9)
            return r10
        L_0x0036:
            monitor-exit(r9)
            return r1
        L_0x0038:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.mo.b(long, boolean, boolean):long");
    }

    public final synchronized long l() {
        if (this.l == 0) {
            return -1;
        }
        return d(this.l);
    }

    public final synchronized long m() {
        if (this.i == 0) {
            return -1;
        }
        return d(this.i);
    }

    public final synchronized boolean a(bs bsVar) {
        if (bsVar == null) {
            this.q = true;
            return false;
        }
        this.q = false;
        if (vf.a((Object) bsVar, (Object) this.r)) {
            return false;
        }
        this.r = bsVar;
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00e0, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(long r6, int r8, long r9, int r11, com.google.ads.interactivemedia.v3.internal.gd r12) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.p     // Catch:{ all -> 0x00e1 }
            r1 = 0
            if (r0 == 0) goto L_0x000e
            r0 = r8 & 1
            if (r0 != 0) goto L_0x000c
            monitor-exit(r5)
            return
        L_0x000c:
            r5.p = r1     // Catch:{ all -> 0x00e1 }
        L_0x000e:
            boolean r0 = r5.q     // Catch:{ all -> 0x00e1 }
            r2 = 1
            if (r0 != 0) goto L_0x0015
            r0 = 1
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            com.google.ads.interactivemedia.v3.internal.qi.c(r0)     // Catch:{ all -> 0x00e1 }
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r8
            if (r0 == 0) goto L_0x0020
            r0 = 1
            goto L_0x0021
        L_0x0020:
            r0 = 0
        L_0x0021:
            r5.o = r0     // Catch:{ all -> 0x00e1 }
            long r3 = r5.n     // Catch:{ all -> 0x00e1 }
            long r3 = java.lang.Math.max(r3, r6)     // Catch:{ all -> 0x00e1 }
            r5.n = r3     // Catch:{ all -> 0x00e1 }
            int r0 = r5.i     // Catch:{ all -> 0x00e1 }
            int r0 = r5.f(r0)     // Catch:{ all -> 0x00e1 }
            long[] r3 = r5.f     // Catch:{ all -> 0x00e1 }
            r3[r0] = r6     // Catch:{ all -> 0x00e1 }
            long[] r6 = r5.c     // Catch:{ all -> 0x00e1 }
            r6[r0] = r9     // Catch:{ all -> 0x00e1 }
            int[] r6 = r5.d     // Catch:{ all -> 0x00e1 }
            r6[r0] = r11     // Catch:{ all -> 0x00e1 }
            int[] r6 = r5.e     // Catch:{ all -> 0x00e1 }
            r6[r0] = r8     // Catch:{ all -> 0x00e1 }
            com.google.ads.interactivemedia.v3.internal.gd[] r6 = r5.g     // Catch:{ all -> 0x00e1 }
            r6[r0] = r12     // Catch:{ all -> 0x00e1 }
            com.google.ads.interactivemedia.v3.internal.bs[] r6 = r5.h     // Catch:{ all -> 0x00e1 }
            com.google.ads.interactivemedia.v3.internal.bs r7 = r5.r     // Catch:{ all -> 0x00e1 }
            r6[r0] = r7     // Catch:{ all -> 0x00e1 }
            int[] r6 = r5.b     // Catch:{ all -> 0x00e1 }
            int r7 = r5.s     // Catch:{ all -> 0x00e1 }
            r6[r0] = r7     // Catch:{ all -> 0x00e1 }
            int r6 = r5.i     // Catch:{ all -> 0x00e1 }
            int r6 = r6 + r2
            r5.i = r6     // Catch:{ all -> 0x00e1 }
            int r6 = r5.i     // Catch:{ all -> 0x00e1 }
            int r7 = r5.a     // Catch:{ all -> 0x00e1 }
            if (r6 != r7) goto L_0x00df
            int r6 = r5.a     // Catch:{ all -> 0x00e1 }
            int r6 = r6 + 1000
            int[] r7 = new int[r6]     // Catch:{ all -> 0x00e1 }
            long[] r8 = new long[r6]     // Catch:{ all -> 0x00e1 }
            long[] r9 = new long[r6]     // Catch:{ all -> 0x00e1 }
            int[] r10 = new int[r6]     // Catch:{ all -> 0x00e1 }
            int[] r11 = new int[r6]     // Catch:{ all -> 0x00e1 }
            com.google.ads.interactivemedia.v3.internal.gd[] r12 = new com.google.ads.interactivemedia.v3.internal.gd[r6]     // Catch:{ all -> 0x00e1 }
            com.google.ads.interactivemedia.v3.internal.bs[] r0 = new com.google.ads.interactivemedia.v3.internal.bs[r6]     // Catch:{ all -> 0x00e1 }
            int r2 = r5.a     // Catch:{ all -> 0x00e1 }
            int r3 = r5.k     // Catch:{ all -> 0x00e1 }
            int r2 = r2 - r3
            long[] r3 = r5.c     // Catch:{ all -> 0x00e1 }
            int r4 = r5.k     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r3, r4, r8, r1, r2)     // Catch:{ all -> 0x00e1 }
            long[] r3 = r5.f     // Catch:{ all -> 0x00e1 }
            int r4 = r5.k     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r3, r4, r9, r1, r2)     // Catch:{ all -> 0x00e1 }
            int[] r3 = r5.e     // Catch:{ all -> 0x00e1 }
            int r4 = r5.k     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r3, r4, r10, r1, r2)     // Catch:{ all -> 0x00e1 }
            int[] r3 = r5.d     // Catch:{ all -> 0x00e1 }
            int r4 = r5.k     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r3, r4, r11, r1, r2)     // Catch:{ all -> 0x00e1 }
            com.google.ads.interactivemedia.v3.internal.gd[] r3 = r5.g     // Catch:{ all -> 0x00e1 }
            int r4 = r5.k     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r3, r4, r12, r1, r2)     // Catch:{ all -> 0x00e1 }
            com.google.ads.interactivemedia.v3.internal.bs[] r3 = r5.h     // Catch:{ all -> 0x00e1 }
            int r4 = r5.k     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r3, r4, r0, r1, r2)     // Catch:{ all -> 0x00e1 }
            int[] r3 = r5.b     // Catch:{ all -> 0x00e1 }
            int r4 = r5.k     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r3, r4, r7, r1, r2)     // Catch:{ all -> 0x00e1 }
            int r3 = r5.k     // Catch:{ all -> 0x00e1 }
            long[] r4 = r5.c     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r4, r1, r8, r2, r3)     // Catch:{ all -> 0x00e1 }
            long[] r4 = r5.f     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r4, r1, r9, r2, r3)     // Catch:{ all -> 0x00e1 }
            int[] r4 = r5.e     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r4, r1, r10, r2, r3)     // Catch:{ all -> 0x00e1 }
            int[] r4 = r5.d     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r4, r1, r11, r2, r3)     // Catch:{ all -> 0x00e1 }
            com.google.ads.interactivemedia.v3.internal.gd[] r4 = r5.g     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r4, r1, r12, r2, r3)     // Catch:{ all -> 0x00e1 }
            com.google.ads.interactivemedia.v3.internal.bs[] r4 = r5.h     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r4, r1, r0, r2, r3)     // Catch:{ all -> 0x00e1 }
            int[] r4 = r5.b     // Catch:{ all -> 0x00e1 }
            java.lang.System.arraycopy(r4, r1, r7, r2, r3)     // Catch:{ all -> 0x00e1 }
            r5.c = r8     // Catch:{ all -> 0x00e1 }
            r5.f = r9     // Catch:{ all -> 0x00e1 }
            r5.e = r10     // Catch:{ all -> 0x00e1 }
            r5.d = r11     // Catch:{ all -> 0x00e1 }
            r5.g = r12     // Catch:{ all -> 0x00e1 }
            r5.h = r0     // Catch:{ all -> 0x00e1 }
            r5.b = r7     // Catch:{ all -> 0x00e1 }
            r5.k = r1     // Catch:{ all -> 0x00e1 }
            int r7 = r5.a     // Catch:{ all -> 0x00e1 }
            r5.i = r7     // Catch:{ all -> 0x00e1 }
            r5.a = r6     // Catch:{ all -> 0x00e1 }
        L_0x00df:
            monitor-exit(r5)
            return
        L_0x00e1:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.mo.a(long, int, long, int, com.google.ads.interactivemedia.v3.internal.gd):void");
    }

    public final synchronized boolean a(long j2) {
        if (this.i == 0) {
            return j2 > this.m;
        }
        if (Math.max(this.m, e(this.l)) >= j2) {
            return false;
        }
        int i2 = this.i;
        int f2 = f(this.i - 1);
        while (i2 > this.l && this.f[f2] >= j2) {
            i2--;
            f2--;
            if (f2 == -1) {
                f2 = this.a - 1;
            }
        }
        a(this.j + i2);
        return true;
    }

    private final int a(int i2, int i3, long j2, boolean z) {
        int i4 = i2;
        int i5 = -1;
        for (int i6 = 0; i6 < i3 && this.f[i4] <= j2; i6++) {
            if (!z || (this.e[i4] & 1) != 0) {
                i5 = i6;
            }
            i4++;
            if (i4 == this.a) {
                i4 = 0;
            }
        }
        return i5;
    }

    private final long d(int i2) {
        this.m = Math.max(this.m, e(i2));
        this.i -= i2;
        this.j += i2;
        this.k += i2;
        int i3 = this.k;
        int i4 = this.a;
        if (i3 >= i4) {
            this.k = i3 - i4;
        }
        this.l -= i2;
        if (this.l < 0) {
            this.l = 0;
        }
        if (this.i != 0) {
            return this.c[this.k];
        }
        int i5 = this.k;
        if (i5 == 0) {
            i5 = this.a;
        }
        int i6 = i5 - 1;
        return this.c[i6] + ((long) this.d[i6]);
    }

    private final long e(int i2) {
        long j2 = Long.MIN_VALUE;
        if (i2 == 0) {
            return Long.MIN_VALUE;
        }
        int f2 = f(i2 - 1);
        for (int i3 = 0; i3 < i2; i3++) {
            j2 = Math.max(j2, this.f[f2]);
            if ((this.e[f2] & 1) != 0) {
                break;
            }
            f2--;
            if (f2 == -1) {
                f2 = this.a - 1;
            }
        }
        return j2;
    }

    private final int f(int i2) {
        int i3 = this.k + i2;
        int i4 = this.a;
        return i3 < i4 ? i3 : i3 - i4;
    }
}

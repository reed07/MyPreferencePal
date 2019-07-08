package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
public final class le implements ll, lm {
    public final ll a;
    long b = 0;
    long c;
    private lm d;
    private lf[] e = new lf[0];
    private long f = 0;

    public le(ll llVar, boolean z, long j, long j2) {
        this.a = llVar;
        this.c = j2;
    }

    public final void a(lm lmVar, long j) {
        this.d = lmVar;
        this.a.a((lm) this, j);
    }

    public final void a_() throws IOException {
        this.a.a_();
    }

    public final mz b() {
        return this.a.b();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0083, code lost:
        if (r1 > r5) goto L_0x0086;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long a(com.google.ads.interactivemedia.v3.internal.rt[] r16, boolean[] r17, com.google.ads.interactivemedia.v3.internal.mt[] r18, boolean[] r19, long r20) {
        /*
            r15 = this;
            r0 = r15
            r8 = r16
            r9 = r18
            int r1 = r9.length
            com.google.ads.interactivemedia.v3.internal.lf[] r1 = new com.google.ads.interactivemedia.v3.internal.lf[r1]
            r0.e = r1
            int r1 = r9.length
            com.google.ads.interactivemedia.v3.internal.mt[] r10 = new com.google.ads.interactivemedia.v3.internal.mt[r1]
            r11 = 0
            r1 = 0
        L_0x000f:
            int r2 = r9.length
            r12 = 0
            if (r1 >= r2) goto L_0x0028
            com.google.ads.interactivemedia.v3.internal.lf[] r2 = r0.e
            r3 = r9[r1]
            com.google.ads.interactivemedia.v3.internal.lf r3 = (com.google.ads.interactivemedia.v3.internal.lf) r3
            r2[r1] = r3
            r3 = r2[r1]
            if (r3 == 0) goto L_0x0023
            r2 = r2[r1]
            com.google.ads.interactivemedia.v3.internal.mt r12 = r2.a
        L_0x0023:
            r10[r1] = r12
            int r1 = r1 + 1
            goto L_0x000f
        L_0x0028:
            com.google.ads.interactivemedia.v3.internal.ll r1 = r0.a
            r2 = r16
            r3 = r17
            r4 = r10
            r5 = r19
            r6 = r20
            long r1 = r1.a(r2, r3, r4, r5, r6)
            boolean r3 = r15.f()
            r4 = 1
            if (r3 == 0) goto L_0x0068
            long r5 = r0.b
            int r3 = (r20 > r5 ? 1 : (r20 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x0068
            r13 = 0
            int r3 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r3 == 0) goto L_0x0063
            int r3 = r8.length
            r5 = 0
        L_0x004c:
            if (r5 >= r3) goto L_0x0063
            r6 = r8[r5]
            if (r6 == 0) goto L_0x0060
            com.google.ads.interactivemedia.v3.internal.bs r6 = r6.h()
            java.lang.String r6 = r6.h
            boolean r6 = com.google.ads.interactivemedia.v3.internal.un.a(r6)
            if (r6 != 0) goto L_0x0060
            r3 = 1
            goto L_0x0064
        L_0x0060:
            int r5 = r5 + 1
            goto L_0x004c
        L_0x0063:
            r3 = 0
        L_0x0064:
            if (r3 == 0) goto L_0x0068
            r5 = r1
            goto L_0x006d
        L_0x0068:
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x006d:
            r0.f = r5
            int r3 = (r1 > r20 ? 1 : (r1 == r20 ? 0 : -1))
            if (r3 == 0) goto L_0x0087
            long r5 = r0.b
            int r3 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r3 < 0) goto L_0x0086
            long r5 = r0.c
            r7 = -9223372036854775808
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x0087
            int r3 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r3 > 0) goto L_0x0086
            goto L_0x0087
        L_0x0086:
            r4 = 0
        L_0x0087:
            com.google.ads.interactivemedia.v3.internal.qi.c(r4)
        L_0x008a:
            int r3 = r9.length
            if (r11 >= r3) goto L_0x00b8
            r3 = r10[r11]
            if (r3 != 0) goto L_0x0096
            com.google.ads.interactivemedia.v3.internal.lf[] r3 = r0.e
            r3[r11] = r12
            goto L_0x00af
        L_0x0096:
            r3 = r9[r11]
            if (r3 == 0) goto L_0x00a4
            com.google.ads.interactivemedia.v3.internal.lf[] r3 = r0.e
            r3 = r3[r11]
            com.google.ads.interactivemedia.v3.internal.mt r3 = r3.a
            r4 = r10[r11]
            if (r3 == r4) goto L_0x00af
        L_0x00a4:
            com.google.ads.interactivemedia.v3.internal.lf[] r3 = r0.e
            com.google.ads.interactivemedia.v3.internal.lf r4 = new com.google.ads.interactivemedia.v3.internal.lf
            r5 = r10[r11]
            r4.<init>(r15, r5)
            r3[r11] = r4
        L_0x00af:
            com.google.ads.interactivemedia.v3.internal.lf[] r3 = r0.e
            r3 = r3[r11]
            r9[r11] = r3
            int r11 = r11 + 1
            goto L_0x008a
        L_0x00b8:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.le.a(com.google.ads.interactivemedia.v3.internal.rt[], boolean[], com.google.ads.interactivemedia.v3.internal.mt[], boolean[], long):long");
    }

    public final void a(long j, boolean z) {
        this.a.a(j, z);
    }

    public final void a(long j) {
        this.a.a(j);
    }

    public final long c() {
        if (f()) {
            long j = this.f;
            this.f = -9223372036854775807L;
            long c2 = c();
            return c2 != -9223372036854775807L ? c2 : j;
        }
        long c3 = this.a.c();
        if (c3 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        boolean z = true;
        qi.c(c3 >= this.b);
        long j2 = this.c;
        if (j2 != Long.MIN_VALUE && c3 > j2) {
            z = false;
        }
        qi.c(z);
        return c3;
    }

    public final long d() {
        long d2 = this.a.d();
        if (d2 != Long.MIN_VALUE) {
            long j = this.c;
            if (j == Long.MIN_VALUE || d2 < j) {
                return d2;
            }
        }
        return Long.MIN_VALUE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        if (r0 > r7) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long b(long r7) {
        /*
            r6 = this;
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6.f = r0
            com.google.ads.interactivemedia.v3.internal.lf[] r0 = r6.e
            int r1 = r0.length
            r2 = 0
            r3 = 0
        L_0x000c:
            if (r3 >= r1) goto L_0x0018
            r4 = r0[r3]
            if (r4 == 0) goto L_0x0015
            r4.a()
        L_0x0015:
            int r3 = r3 + 1
            goto L_0x000c
        L_0x0018:
            com.google.ads.interactivemedia.v3.internal.ll r0 = r6.a
            long r0 = r0.b(r7)
            int r3 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x0034
            long r7 = r6.b
            int r3 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r3 < 0) goto L_0x0035
            long r7 = r6.c
            r3 = -9223372036854775808
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0034
            int r3 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r3 > 0) goto L_0x0035
        L_0x0034:
            r2 = 1
        L_0x0035:
            com.google.ads.interactivemedia.v3.internal.qi.c(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.le.b(long):long");
    }

    public final long a(long j, cm cmVar) {
        long j2 = this.b;
        if (j == j2) {
            return j2;
        }
        long a2 = vf.a(cmVar.c, 0, j - this.b);
        long j3 = cmVar.d;
        long j4 = this.c;
        long a3 = vf.a(j3, 0, j4 == Long.MIN_VALUE ? Long.MAX_VALUE : j4 - j);
        if (!(a2 == cmVar.c && a3 == cmVar.d)) {
            cmVar = new cm(a2, a3);
        }
        return this.a.a(j, cmVar);
    }

    public final long e() {
        long e2 = this.a.e();
        if (e2 != Long.MIN_VALUE) {
            long j = this.c;
            if (j == Long.MIN_VALUE || e2 < j) {
                return e2;
            }
        }
        return Long.MIN_VALUE;
    }

    public final boolean c(long j) {
        return this.a.c(j);
    }

    public final void a(ll llVar) {
        this.d.a(this);
    }

    /* access modifiers changed from: 0000 */
    public final boolean f() {
        return this.f != -9223372036854775807L;
    }

    public final /* synthetic */ void a(mu muVar) {
        this.d.a(this);
    }
}

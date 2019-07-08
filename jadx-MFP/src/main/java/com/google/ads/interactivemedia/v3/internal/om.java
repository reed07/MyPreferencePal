package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class om {
    final nh a;
    public final oy b;
    public final ok c;
    /* access modifiers changed from: private */
    public final long d;
    private final long e;

    /* JADX WARNING: type inference failed for: r0v11, types: [com.google.ads.interactivemedia.v3.internal.gi] */
    /* JADX WARNING: type inference failed for: r0v15, types: [com.google.ads.interactivemedia.v3.internal.hv] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    om(long r14, int r16, com.google.ads.interactivemedia.v3.internal.oy r17, boolean r18, boolean r19, com.google.ads.interactivemedia.v3.internal.gc r20) {
        /*
            r13 = this;
            r3 = r17
            com.google.ads.interactivemedia.v3.internal.bs r0 = r3.a
            java.lang.String r0 = r0.g
            boolean r1 = com.google.ads.interactivemedia.v3.internal.un.c(r0)
            r2 = 1
            r4 = 0
            if (r1 != 0) goto L_0x0019
            java.lang.String r1 = "application/ttml+xml"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0017
            goto L_0x0019
        L_0x0017:
            r1 = 0
            goto L_0x001a
        L_0x0019:
            r1 = 1
        L_0x001a:
            r5 = 0
            if (r1 == 0) goto L_0x0020
            r4 = r5
            goto L_0x0082
        L_0x0020:
            java.lang.String r1 = "application/x-rawcc"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0030
            com.google.ads.interactivemedia.v3.internal.hv r0 = new com.google.ads.interactivemedia.v3.internal.hv
            com.google.ads.interactivemedia.v3.internal.bs r1 = r3.a
            r0.<init>(r1)
            goto L_0x0078
        L_0x0030:
            java.lang.String r1 = "video/webm"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x004b
            java.lang.String r1 = "audio/webm"
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x004b
            java.lang.String r1 = "application/webm"
            boolean r0 = r0.startsWith(r1)
            if (r0 == 0) goto L_0x0049
            goto L_0x004b
        L_0x0049:
            r0 = 0
            goto L_0x004c
        L_0x004b:
            r0 = 1
        L_0x004c:
            if (r0 == 0) goto L_0x0054
            com.google.ads.interactivemedia.v3.internal.gi r0 = new com.google.ads.interactivemedia.v3.internal.gi
            r0.<init>(r2)
            goto L_0x0078
        L_0x0054:
            if (r18 == 0) goto L_0x0059
            r0 = 4
            r7 = 4
            goto L_0x005a
        L_0x0059:
            r7 = 0
        L_0x005a:
            if (r19 == 0) goto L_0x0068
            java.lang.String r0 = "application/cea-608"
            com.google.ads.interactivemedia.v3.internal.bs r0 = com.google.ads.interactivemedia.v3.internal.bs.a(r5, r0, r4, r5)
            java.util.List r0 = java.util.Collections.singletonList(r0)
            r11 = r0
            goto L_0x006d
        L_0x0068:
            java.util.List r0 = java.util.Collections.emptyList()
            r11 = r0
        L_0x006d:
            com.google.ads.interactivemedia.v3.internal.hg r0 = new com.google.ads.interactivemedia.v3.internal.hg
            r8 = 0
            r9 = 0
            r10 = 0
            r6 = r0
            r12 = r20
            r6.<init>(r7, r8, r9, r10, r11, r12)
        L_0x0078:
            com.google.ads.interactivemedia.v3.internal.nh r1 = new com.google.ads.interactivemedia.v3.internal.nh
            com.google.ads.interactivemedia.v3.internal.bs r2 = r3.a
            r4 = r16
            r1.<init>(r0, r4, r2)
            r4 = r1
        L_0x0082:
            r5 = 0
            com.google.ads.interactivemedia.v3.internal.ok r7 = r17.e()
            r0 = r13
            r1 = r14
            r3 = r17
            r0.<init>(r1, r3, r4, r5, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.om.<init>(long, int, com.google.ads.interactivemedia.v3.internal.oy, boolean, boolean, com.google.ads.interactivemedia.v3.internal.gc):void");
    }

    private om(long j, oy oyVar, nh nhVar, long j2, ok okVar) {
        this.d = j;
        this.b = oyVar;
        this.e = j2;
        this.a = nhVar;
        this.c = okVar;
    }

    /* access modifiers changed from: 0000 */
    public final om a(long j, oy oyVar) throws ld {
        long j2;
        long j3 = j;
        ok e2 = this.b.e();
        ok e3 = oyVar.e();
        if (e2 == null) {
            om omVar = new om(j, oyVar, this.a, this.e, e2);
            return omVar;
        } else if (!e2.b()) {
            om omVar2 = new om(j, oyVar, this.a, this.e, e3);
            return omVar2;
        } else {
            int c2 = e2.c(j3);
            if (c2 == 0) {
                om omVar3 = new om(j, oyVar, this.a, this.e, e3);
                return omVar3;
            }
            long b_ = (e2.b_() + ((long) c2)) - 1;
            long a2 = e2.a(b_) + e2.b(b_, j3);
            long b_2 = e3.b_();
            long a3 = e3.a(b_2);
            long j4 = this.e;
            int i = (a2 > a3 ? 1 : (a2 == a3 ? 0 : -1));
            if (i == 0) {
                j2 = j4 + ((b_ + 1) - b_2);
            } else if (i >= 0) {
                j2 = j4 + (e2.a(a3, j3) - b_2);
            } else {
                throw new ld();
            }
            om omVar4 = new om(j, oyVar, this.a, j2, e3);
            return omVar4;
        }
    }

    /* access modifiers changed from: 0000 */
    public final om a(ok okVar) {
        om omVar = new om(this.d, this.b, this.a, this.e, okVar);
        return omVar;
    }

    public final long a() {
        return this.c.b_() + this.e;
    }

    public final int b() {
        return this.c.c(this.d);
    }

    public final long a(long j) {
        return this.c.a(j - this.e);
    }

    public final long b(long j) {
        return a(j) + this.c.b(j - this.e, this.d);
    }

    public final long c(long j) {
        return this.c.a(j, this.d) + this.e;
    }

    public final ox d(long j) {
        return this.c.b(j - this.e);
    }

    public final long a(tc tcVar, int i, long j) {
        if (b() != -1 || tcVar.f == -9223372036854775807L) {
            return a();
        }
        return Math.max(a(), c(((j - at.b(tcVar.a)) - at.b(tcVar.a(i).b)) - at.b(tcVar.f)));
    }

    public final long b(tc tcVar, int i, long j) {
        int b2 = b();
        if (b2 == -1) {
            return c((j - at.b(tcVar.a)) - at.b(tcVar.a(i).b)) - 1;
        }
        return (a() + ((long) b2)) - 1;
    }
}

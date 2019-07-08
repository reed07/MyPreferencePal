package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class hx implements ic {
    private final us a;
    private final ut b;
    private final String c;
    private String d;
    private gc e;
    private int f;
    private int g;
    private boolean h;
    private long i;
    private bs j;
    private int k;
    private long l;

    public hx() {
        this(null);
    }

    public final void b() {
    }

    public hx(String str) {
        this.a = new us(new byte[128]);
        this.b = new ut(this.a.a);
        this.f = 0;
        this.c = str;
    }

    public final void a() {
        this.f = 0;
        this.g = 0;
        this.h = false;
    }

    public final void a(fs fsVar, jd jdVar) {
        jdVar.a();
        this.d = jdVar.c();
        this.e = fsVar.a(jdVar.b(), 1);
    }

    public final void a(long j2, int i2) {
        this.l = j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00d8, code lost:
        if (r20.b() <= 0) goto L_0x00fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00dc, code lost:
        if (r0.h != false) goto L_0x00e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e2, code lost:
        if (r20.e() != 11) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00e5, code lost:
        r7 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00e6, code lost:
        r0.h = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00e9, code lost:
        r2 = r20.e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ed, code lost:
        if (r2 != 119) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ef, code lost:
        r0.h = false;
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f3, code lost:
        if (r2 != 11) goto L_0x00f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00f6, code lost:
        r7 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00f7, code lost:
        r0.h = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00fa, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00fb, code lost:
        if (r2 == false) goto L_0x0004;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00fd, code lost:
        r0.f = 1;
        r0.b.a[0] = com.google.common.base.Ascii.VT;
        r0.b.a[1] = 119;
        r0.g = 2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.ads.interactivemedia.v3.internal.ut r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
        L_0x0004:
            int r2 = r20.b()
            if (r2 <= 0) goto L_0x010f
            int r2 = r0.f
            r3 = 2
            r4 = 0
            switch(r2) {
                case 0: goto L_0x00cf;
                case 1: goto L_0x0043;
                case 2: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0004
        L_0x0012:
            int r2 = r20.b()
            int r3 = r0.k
            int r5 = r0.g
            int r3 = r3 - r5
            int r2 = java.lang.Math.min(r2, r3)
            com.google.ads.interactivemedia.v3.internal.gc r3 = r0.e
            r3.a(r1, r2)
            int r3 = r0.g
            int r3 = r3 + r2
            r0.g = r3
            int r2 = r0.g
            int r9 = r0.k
            if (r2 != r9) goto L_0x0004
            com.google.ads.interactivemedia.v3.internal.gc r5 = r0.e
            long r6 = r0.l
            r8 = 1
            r10 = 0
            r11 = 0
            r5.a(r6, r8, r9, r10, r11)
            long r2 = r0.l
            long r5 = r0.i
            long r2 = r2 + r5
            r0.l = r2
            r0.f = r4
            goto L_0x0004
        L_0x0043:
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.b
            byte[] r2 = r2.a
            int r5 = r20.b()
            int r6 = r0.g
            r7 = 128(0x80, float:1.794E-43)
            int r6 = 128 - r6
            int r5 = java.lang.Math.min(r5, r6)
            int r6 = r0.g
            r1.a(r2, r6, r5)
            int r2 = r0.g
            int r2 = r2 + r5
            r0.g = r2
            int r2 = r0.g
            if (r2 != r7) goto L_0x0004
            com.google.ads.interactivemedia.v3.internal.us r2 = r0.a
            r2.a(r4)
            com.google.ads.interactivemedia.v3.internal.us r2 = r0.a
            com.google.ads.interactivemedia.v3.internal.db r2 = com.google.ads.interactivemedia.v3.internal.da.a(r2)
            com.google.ads.interactivemedia.v3.internal.bs r5 = r0.j
            if (r5 == 0) goto L_0x008a
            int r5 = r2.c
            com.google.ads.interactivemedia.v3.internal.bs r6 = r0.j
            int r6 = r6.s
            if (r5 != r6) goto L_0x008a
            int r5 = r2.b
            com.google.ads.interactivemedia.v3.internal.bs r6 = r0.j
            int r6 = r6.t
            if (r5 != r6) goto L_0x008a
            java.lang.String r5 = r2.a
            com.google.ads.interactivemedia.v3.internal.bs r6 = r0.j
            java.lang.String r6 = r6.h
            if (r5 == r6) goto L_0x00ab
        L_0x008a:
            java.lang.String r8 = r0.d
            java.lang.String r9 = r2.a
            r10 = 0
            r11 = -1
            r12 = -1
            int r13 = r2.c
            int r14 = r2.b
            r15 = 0
            r16 = 0
            r17 = 0
            java.lang.String r5 = r0.c
            r18 = r5
            com.google.ads.interactivemedia.v3.internal.bs r5 = com.google.ads.interactivemedia.v3.internal.bs.a(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            r0.j = r5
            com.google.ads.interactivemedia.v3.internal.gc r5 = r0.e
            com.google.ads.interactivemedia.v3.internal.bs r6 = r0.j
            r5.a(r6)
        L_0x00ab:
            int r5 = r2.d
            r0.k = r5
            r5 = 1000000(0xf4240, double:4.940656E-318)
            int r2 = r2.e
            long r8 = (long) r2
            long r8 = r8 * r5
            com.google.ads.interactivemedia.v3.internal.bs r2 = r0.j
            int r2 = r2.t
            long r5 = (long) r2
            long r8 = r8 / r5
            r0.i = r8
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.b
            r2.c(r4)
            com.google.ads.interactivemedia.v3.internal.gc r2 = r0.e
            com.google.ads.interactivemedia.v3.internal.ut r4 = r0.b
            r2.a(r4, r7)
            r0.f = r3
            goto L_0x0004
        L_0x00cf:
            int r2 = r20.b()
            r5 = 119(0x77, float:1.67E-43)
            r6 = 11
            r7 = 1
            if (r2 <= 0) goto L_0x00fa
            boolean r2 = r0.h
            if (r2 != 0) goto L_0x00e9
            int r2 = r20.e()
            if (r2 != r6) goto L_0x00e5
            goto L_0x00e6
        L_0x00e5:
            r7 = 0
        L_0x00e6:
            r0.h = r7
            goto L_0x00cf
        L_0x00e9:
            int r2 = r20.e()
            if (r2 != r5) goto L_0x00f3
            r0.h = r4
            r2 = 1
            goto L_0x00fb
        L_0x00f3:
            if (r2 != r6) goto L_0x00f6
            goto L_0x00f7
        L_0x00f6:
            r7 = 0
        L_0x00f7:
            r0.h = r7
            goto L_0x00cf
        L_0x00fa:
            r2 = 0
        L_0x00fb:
            if (r2 == 0) goto L_0x0004
            r0.f = r7
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.b
            byte[] r2 = r2.a
            r2[r4] = r6
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.b
            byte[] r2 = r2.a
            r2[r7] = r5
            r0.g = r3
            goto L_0x0004
        L_0x010f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.hx.a(com.google.ads.interactivemedia.v3.internal.ut):void");
    }
}

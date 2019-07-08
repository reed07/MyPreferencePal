package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class ia implements ic {
    private final ut a = new ut(new byte[18]);
    private final String b;
    private String c;
    private gc d;
    private int e = 0;
    private int f;
    private int g;
    private long h;
    private bs i;
    private int j;
    private long k;

    public ia(String str) {
        this.b = str;
    }

    public final void b() {
    }

    public final void a() {
        this.e = 0;
        this.f = 0;
        this.g = 0;
    }

    public final void a(fs fsVar, jd jdVar) {
        jdVar.a();
        this.c = jdVar.c();
        this.d = fsVar.a(jdVar.b(), 1);
    }

    public final void a(long j2, int i2) {
        this.k = j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00ad, code lost:
        if (r11.b() <= 0) goto L_0x00f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00af, code lost:
        r10.g <<= 8;
        r10.g |= r11.e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00c6, code lost:
        r10.a.a[0] = (byte) (r10.g >>> 24);
        r10.a.a[1] = (byte) (r10.g >> 16);
        r10.a.a[2] = (byte) (r10.g >> 8);
        r10.a.a[3] = (byte) r10.g;
        r10.f = 4;
        r10.g = 0;
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00f7, code lost:
        if (r2 == false) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00f9, code lost:
        r10.e = 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.ads.interactivemedia.v3.internal.ut r11) {
        /*
            r10 = this;
        L_0x0000:
            int r0 = r11.b()
            if (r0 <= 0) goto L_0x00fd
            int r0 = r10.e
            r1 = 2
            r2 = 0
            switch(r0) {
                case 0: goto L_0x00a8;
                case 1: goto L_0x0044;
                case 2: goto L_0x0013;
                default: goto L_0x000d;
            }
        L_0x000d:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            r11.<init>()
            throw r11
        L_0x0013:
            int r0 = r11.b()
            int r1 = r10.j
            int r3 = r10.f
            int r1 = r1 - r3
            int r0 = java.lang.Math.min(r0, r1)
            com.google.ads.interactivemedia.v3.internal.gc r1 = r10.d
            r1.a(r11, r0)
            int r1 = r10.f
            int r1 = r1 + r0
            r10.f = r1
            int r0 = r10.f
            int r7 = r10.j
            if (r0 != r7) goto L_0x0000
            com.google.ads.interactivemedia.v3.internal.gc r3 = r10.d
            long r4 = r10.k
            r6 = 1
            r8 = 0
            r9 = 0
            r3.a(r4, r6, r7, r8, r9)
            long r0 = r10.k
            long r3 = r10.h
            long r0 = r0 + r3
            r10.k = r0
            r10.e = r2
            goto L_0x0000
        L_0x0044:
            com.google.ads.interactivemedia.v3.internal.ut r0 = r10.a
            byte[] r0 = r0.a
            int r3 = r11.b()
            int r4 = r10.f
            r5 = 18
            int r4 = 18 - r4
            int r3 = java.lang.Math.min(r3, r4)
            int r4 = r10.f
            r11.a(r0, r4, r3)
            int r0 = r10.f
            int r0 = r0 + r3
            r10.f = r0
            int r0 = r10.f
            if (r0 != r5) goto L_0x0000
            com.google.ads.interactivemedia.v3.internal.ut r0 = r10.a
            byte[] r0 = r0.a
            com.google.ads.interactivemedia.v3.internal.bs r3 = r10.i
            if (r3 != 0) goto L_0x007e
            java.lang.String r3 = r10.c
            java.lang.String r4 = r10.b
            r6 = 0
            com.google.ads.interactivemedia.v3.internal.bs r3 = com.google.ads.interactivemedia.v3.internal.el.a(r0, r3, r4, r6)
            r10.i = r3
            com.google.ads.interactivemedia.v3.internal.gc r3 = r10.d
            com.google.ads.interactivemedia.v3.internal.bs r4 = r10.i
            r3.a(r4)
        L_0x007e:
            int r3 = com.google.ads.interactivemedia.v3.internal.el.b(r0)
            r10.j = r3
            r3 = 1000000(0xf4240, double:4.940656E-318)
            int r0 = com.google.ads.interactivemedia.v3.internal.el.a(r0)
            long r6 = (long) r0
            long r6 = r6 * r3
            com.google.ads.interactivemedia.v3.internal.bs r0 = r10.i
            int r0 = r0.t
            long r3 = (long) r0
            long r6 = r6 / r3
            int r0 = (int) r6
            long r3 = (long) r0
            r10.h = r3
            com.google.ads.interactivemedia.v3.internal.ut r0 = r10.a
            r0.c(r2)
            com.google.ads.interactivemedia.v3.internal.gc r0 = r10.d
            com.google.ads.interactivemedia.v3.internal.ut r2 = r10.a
            r0.a(r2, r5)
            r10.e = r1
            goto L_0x0000
        L_0x00a8:
            int r0 = r11.b()
            r3 = 1
            if (r0 <= 0) goto L_0x00f7
            int r0 = r10.g
            int r0 = r0 << 8
            r10.g = r0
            int r0 = r10.g
            int r4 = r11.e()
            r0 = r0 | r4
            r10.g = r0
            int r0 = r10.g
            boolean r0 = com.google.ads.interactivemedia.v3.internal.el.a(r0)
            if (r0 == 0) goto L_0x00a8
            com.google.ads.interactivemedia.v3.internal.ut r0 = r10.a
            byte[] r0 = r0.a
            int r4 = r10.g
            int r4 = r4 >>> 24
            byte r4 = (byte) r4
            r0[r2] = r4
            com.google.ads.interactivemedia.v3.internal.ut r0 = r10.a
            byte[] r0 = r0.a
            int r4 = r10.g
            int r4 = r4 >> 16
            byte r4 = (byte) r4
            r0[r3] = r4
            com.google.ads.interactivemedia.v3.internal.ut r0 = r10.a
            byte[] r0 = r0.a
            int r4 = r10.g
            int r4 = r4 >> 8
            byte r4 = (byte) r4
            r0[r1] = r4
            com.google.ads.interactivemedia.v3.internal.ut r0 = r10.a
            byte[] r0 = r0.a
            r1 = 3
            int r4 = r10.g
            byte r4 = (byte) r4
            r0[r1] = r4
            r0 = 4
            r10.f = r0
            r10.g = r2
            r2 = 1
        L_0x00f7:
            if (r2 == 0) goto L_0x0000
            r10.e = r3
            goto L_0x0000
        L_0x00fd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ia.a(com.google.ads.interactivemedia.v3.internal.ut):void");
    }
}

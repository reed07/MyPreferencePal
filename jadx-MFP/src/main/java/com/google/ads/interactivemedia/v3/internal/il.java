package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;

/* compiled from: IMASDK */
public final class il implements ic {
    private final String a;
    private final ut b = new ut(1024);
    private final us c = new us(this.b.a);
    private gc d;
    private bs e;
    private String f;
    private int g;
    private int h;
    private int i;
    private int j;
    private long k;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private long q;
    private int r;
    private long s;
    private int t;

    public il(String str) {
        this.a = str;
    }

    public final void b() {
    }

    public final void a() {
        this.g = 0;
        this.l = false;
    }

    public final void a(fs fsVar, jd jdVar) {
        jdVar.a();
        this.d = fsVar.a(jdVar.b(), 1);
        this.f = jdVar.c();
    }

    public final void a(long j2, int i2) {
        this.k = j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x013f, code lost:
        if (r0.l != false) goto L_0x0141;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.ads.interactivemedia.v3.internal.ut r23) throws com.google.ads.interactivemedia.v3.internal.ca {
        /*
            r22 = this;
            r0 = r22
        L_0x0002:
            int r1 = r23.b()
            if (r1 <= 0) goto L_0x020b
            int r1 = r0.g
            r2 = 86
            r3 = 3
            r4 = 8
            r5 = 1
            r6 = 0
            switch(r1) {
                case 0: goto L_0x01ff;
                case 1: goto L_0x01e5;
                case 2: goto L_0x01b4;
                case 3: goto L_0x001a;
                default: goto L_0x0014;
            }
        L_0x0014:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x001a:
            int r1 = r23.b()
            int r2 = r0.i
            int r7 = r0.h
            int r2 = r2 - r7
            int r1 = java.lang.Math.min(r1, r2)
            com.google.ads.interactivemedia.v3.internal.us r2 = r0.c
            byte[] r2 = r2.a
            int r7 = r0.h
            r8 = r23
            r8.a(r2, r7, r1)
            int r2 = r0.h
            int r2 = r2 + r1
            r0.h = r2
            int r1 = r0.h
            int r2 = r0.i
            if (r1 != r2) goto L_0x0002
            com.google.ads.interactivemedia.v3.internal.us r1 = r0.c
            r1.a(r6)
            com.google.ads.interactivemedia.v3.internal.us r1 = r0.c
            boolean r2 = r1.d()
            if (r2 != 0) goto L_0x013d
            r0.l = r5
            int r2 = r1.c(r5)
            if (r2 != r5) goto L_0x0057
            int r7 = r1.c(r5)
            goto L_0x0058
        L_0x0057:
            r7 = 0
        L_0x0058:
            r0.m = r7
            int r7 = r0.m
            if (r7 != 0) goto L_0x0137
            if (r2 != r5) goto L_0x0063
            b(r1)
        L_0x0063:
            boolean r7 = r1.d()
            if (r7 == 0) goto L_0x0131
            r7 = 6
            int r9 = r1.c(r7)
            r0.n = r9
            r9 = 4
            int r9 = r1.c(r9)
            int r10 = r1.c(r3)
            if (r9 != 0) goto L_0x012b
            if (r10 != 0) goto L_0x012b
            if (r2 != 0) goto L_0x00ca
            int r9 = r1.b()
            int r10 = r0.a(r1)
            r1.a(r9)
            int r9 = r10 + 7
            int r9 = r9 / r4
            byte[] r9 = new byte[r9]
            r1.a(r9, r6, r10)
            java.lang.String r11 = r0.f
            java.lang.String r12 = "audio/mp4a-latm"
            r13 = 0
            r14 = -1
            r15 = -1
            int r10 = r0.t
            int r6 = r0.r
            java.util.List r18 = java.util.Collections.singletonList(r9)
            r19 = 0
            r20 = 0
            java.lang.String r9 = r0.a
            r16 = r10
            r17 = r6
            r21 = r9
            com.google.ads.interactivemedia.v3.internal.bs r6 = com.google.ads.interactivemedia.v3.internal.bs.a(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            com.google.ads.interactivemedia.v3.internal.bs r9 = r0.e
            boolean r9 = r6.equals(r9)
            if (r9 != 0) goto L_0x00d7
            r0.e = r6
            r9 = 1024000000(0x3d090000, double:5.059232213E-315)
            int r11 = r6.t
            long r11 = (long) r11
            long r9 = r9 / r11
            r0.s = r9
            com.google.ads.interactivemedia.v3.internal.gc r9 = r0.d
            r9.a(r6)
            goto L_0x00d7
        L_0x00ca:
            long r9 = b(r1)
            int r6 = (int) r9
            int r9 = r0.a(r1)
            int r6 = r6 - r9
            r1.b(r6)
        L_0x00d7:
            int r3 = r1.c(r3)
            r0.o = r3
            int r3 = r0.o
            switch(r3) {
                case 0: goto L_0x00f6;
                case 1: goto L_0x00f0;
                case 2: goto L_0x00e2;
                case 3: goto L_0x00ec;
                case 4: goto L_0x00ec;
                case 5: goto L_0x00ec;
                case 6: goto L_0x00e8;
                case 7: goto L_0x00e8;
                default: goto L_0x00e2;
            }
        L_0x00e2:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x00e8:
            r1.b(r5)
            goto L_0x00f9
        L_0x00ec:
            r1.b(r7)
            goto L_0x00f9
        L_0x00f0:
            r3 = 9
            r1.b(r3)
            goto L_0x00f9
        L_0x00f6:
            r1.b(r4)
        L_0x00f9:
            boolean r3 = r1.d()
            r0.p = r3
            r6 = 0
            r0.q = r6
            boolean r3 = r0.p
            if (r3 == 0) goto L_0x0121
            if (r2 != r5) goto L_0x0110
            long r2 = b(r1)
            r0.q = r2
            goto L_0x0121
        L_0x0110:
            boolean r2 = r1.d()
            long r5 = r0.q
            long r5 = r5 << r4
            int r3 = r1.c(r4)
            long r9 = (long) r3
            long r5 = r5 + r9
            r0.q = r5
            if (r2 != 0) goto L_0x0110
        L_0x0121:
            boolean r2 = r1.d()
            if (r2 == 0) goto L_0x0141
            r1.b(r4)
            goto L_0x0141
        L_0x012b:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            r1.<init>()
            throw r1
        L_0x0131:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            r1.<init>()
            throw r1
        L_0x0137:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            r1.<init>()
            throw r1
        L_0x013d:
            boolean r2 = r0.l
            if (r2 == 0) goto L_0x01af
        L_0x0141:
            int r2 = r0.m
            if (r2 != 0) goto L_0x01a9
            int r2 = r0.n
            if (r2 != 0) goto L_0x01a3
            int r2 = r0.o
            if (r2 != 0) goto L_0x019d
            r2 = 0
        L_0x014e:
            int r3 = r1.c(r4)
            int r13 = r2 + r3
            r2 = 255(0xff, float:3.57E-43)
            if (r3 == r2) goto L_0x019b
            int r2 = r1.b()
            r3 = r2 & 7
            if (r3 != 0) goto L_0x0168
            com.google.ads.interactivemedia.v3.internal.ut r3 = r0.b
            int r2 = r2 >> 3
            r3.c(r2)
            goto L_0x0177
        L_0x0168:
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.b
            byte[] r2 = r2.a
            int r3 = r13 << 3
            r4 = 0
            r1.a(r2, r4, r3)
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.b
            r2.c(r4)
        L_0x0177:
            com.google.ads.interactivemedia.v3.internal.gc r2 = r0.d
            com.google.ads.interactivemedia.v3.internal.ut r3 = r0.b
            r2.a(r3, r13)
            com.google.ads.interactivemedia.v3.internal.gc r9 = r0.d
            long r10 = r0.k
            r12 = 1
            r14 = 0
            r15 = 0
            r9.a(r10, r12, r13, r14, r15)
            long r2 = r0.k
            long r4 = r0.s
            long r2 = r2 + r4
            r0.k = r2
            boolean r2 = r0.p
            if (r2 == 0) goto L_0x0199
            long r2 = r0.q
            int r3 = (int) r2
            r1.b(r3)
        L_0x0199:
            r1 = 0
            goto L_0x01b0
        L_0x019b:
            r2 = r13
            goto L_0x014e
        L_0x019d:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            r1.<init>()
            throw r1
        L_0x01a3:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            r1.<init>()
            throw r1
        L_0x01a9:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            r1.<init>()
            throw r1
        L_0x01af:
            r1 = 0
        L_0x01b0:
            r0.g = r1
            goto L_0x0002
        L_0x01b4:
            r8 = r23
            int r1 = r0.j
            r1 = r1 & -225(0xffffffffffffff1f, float:NaN)
            int r1 = r1 << r4
            int r2 = r23.e()
            r1 = r1 | r2
            r0.i = r1
            int r1 = r0.i
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.b
            byte[] r2 = r2.a
            int r2 = r2.length
            if (r1 <= r2) goto L_0x01de
            int r1 = r0.i
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.b
            r2.a(r1)
            com.google.ads.interactivemedia.v3.internal.us r1 = r0.c
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.b
            byte[] r2 = r2.a
            int r4 = r2.length
            r1.a(r2, r4)
            r1 = 0
            goto L_0x01df
        L_0x01de:
            r1 = 0
        L_0x01df:
            r0.h = r1
            r0.g = r3
            goto L_0x0002
        L_0x01e5:
            r8 = r23
            int r1 = r23.e()
            r3 = r1 & 224(0xe0, float:3.14E-43)
            r4 = 224(0xe0, float:3.14E-43)
            if (r3 != r4) goto L_0x01f8
            r0.j = r1
            r1 = 2
            r0.g = r1
            goto L_0x0002
        L_0x01f8:
            if (r1 == r2) goto L_0x0002
            r1 = 0
            r0.g = r1
            goto L_0x0002
        L_0x01ff:
            r8 = r23
            int r1 = r23.e()
            if (r1 != r2) goto L_0x0002
            r0.g = r5
            goto L_0x0002
        L_0x020b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.il.a(com.google.ads.interactivemedia.v3.internal.ut):void");
    }

    private final int a(us usVar) throws ca {
        int a2 = usVar.a();
        Pair a3 = ub.a(usVar, true);
        this.r = ((Integer) a3.first).intValue();
        this.t = ((Integer) a3.second).intValue();
        return a2 - usVar.a();
    }

    private static long b(us usVar) {
        return (long) usVar.c((usVar.c(2) + 1) << 3);
    }
}

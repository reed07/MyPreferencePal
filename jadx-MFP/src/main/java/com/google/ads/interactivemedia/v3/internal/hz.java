package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class hz implements ic {
    private static final byte[] a = {73, 68, 51};
    private final boolean b;
    private final us c;
    private final ut d;
    private final String e;
    private String f;
    private gc g;
    private gc h;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    private long r;
    private int s;
    private long t;
    private gc u;
    private long v;

    public hz(boolean z) {
        this(true, null);
    }

    public static boolean a(int i2) {
        return (i2 & 65526) == 65520;
    }

    public final void b() {
    }

    public hz(boolean z, String str) {
        this.c = new us(new byte[7]);
        this.d = new ut(Arrays.copyOf(a, 10));
        e();
        this.n = -1;
        this.o = -1;
        this.r = -9223372036854775807L;
        this.b = z;
        this.e = str;
    }

    public final void a() {
        d();
    }

    public final void a(fs fsVar, jd jdVar) {
        jdVar.a();
        this.f = jdVar.c();
        this.g = fsVar.a(jdVar.b(), 1);
        if (this.b) {
            jdVar.a();
            this.h = fsVar.a(jdVar.b(), 4);
            this.h.a(bs.a(jdVar.c(), MimeTypes.APPLICATION_ID3, (String) null, -1, (fa) null));
            return;
        }
        this.h = new fp();
    }

    public final void a(long j2, int i2) {
        this.t = j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0220, code lost:
        r6.p = (r4 & 8) >> 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0227, code lost:
        if ((r4 & 1) != 0) goto L_0x022b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0229, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x022b, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x022c, code lost:
        r6.l = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0230, code lost:
        if (r6.m != false) goto L_0x0238;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0232, code lost:
        r6.i = 1;
        r6.j = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0238, code lost:
        f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x023b, code lost:
        r7.c(r13);
     */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0220 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.ads.interactivemedia.v3.internal.ut r24) throws com.google.ads.interactivemedia.v3.internal.ca {
        /*
            r23 = this;
            r6 = r23
            r7 = r24
        L_0x0004:
            int r0 = r24.b()
            if (r0 <= 0) goto L_0x0292
            int r0 = r6.i
            r1 = 13
            r2 = 6
            r3 = 3
            r4 = 10
            r5 = 4
            r8 = -1
            r9 = 0
            r10 = 2
            r11 = 1
            switch(r0) {
                case 0: goto L_0x0171;
                case 1: goto L_0x0136;
                case 2: goto L_0x0109;
                case 3: goto L_0x0052;
                case 4: goto L_0x0020;
                default: goto L_0x001a;
            }
        L_0x001a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        L_0x0020:
            int r0 = r24.b()
            int r1 = r6.s
            int r2 = r6.j
            int r1 = r1 - r2
            int r0 = java.lang.Math.min(r0, r1)
            com.google.ads.interactivemedia.v3.internal.gc r1 = r6.u
            r1.a(r7, r0)
            int r1 = r6.j
            int r1 = r1 + r0
            r6.j = r1
            int r0 = r6.j
            int r12 = r6.s
            if (r0 != r12) goto L_0x0004
            com.google.ads.interactivemedia.v3.internal.gc r8 = r6.u
            long r9 = r6.t
            r11 = 1
            r13 = 0
            r14 = 0
            r8.a(r9, r11, r12, r13, r14)
            long r0 = r6.t
            long r2 = r6.v
            long r0 = r0 + r2
            r6.t = r0
            r23.e()
            goto L_0x0004
        L_0x0052:
            boolean r0 = r6.l
            r2 = 5
            if (r0 == 0) goto L_0x0059
            r0 = 7
            goto L_0x005a
        L_0x0059:
            r0 = 5
        L_0x005a:
            com.google.ads.interactivemedia.v3.internal.us r8 = r6.c
            byte[] r8 = r8.a
            boolean r0 = r6.a(r7, r8, r0)
            if (r0 == 0) goto L_0x0004
            com.google.ads.interactivemedia.v3.internal.us r0 = r6.c
            r0.a(r9)
            boolean r0 = r6.q
            if (r0 != 0) goto L_0x00e2
            com.google.ads.interactivemedia.v3.internal.us r0 = r6.c
            int r0 = r0.c(r10)
            int r0 = r0 + r11
            if (r0 == r10) goto L_0x0094
            java.lang.String r4 = "AdtsReader"
            r8 = 61
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r8)
            java.lang.String r8 = "Detected audio object type: "
            r9.append(r8)
            r9.append(r0)
            java.lang.String r0 = ", but assuming AAC LC."
            r9.append(r0)
            java.lang.String r0 = r9.toString()
            android.util.Log.w(r4, r0)
            r0 = 2
        L_0x0094:
            com.google.ads.interactivemedia.v3.internal.us r4 = r6.c
            r4.b(r2)
            com.google.ads.interactivemedia.v3.internal.us r4 = r6.c
            int r3 = r4.c(r3)
            int r4 = r6.o
            byte[] r0 = com.google.ads.interactivemedia.v3.internal.ub.a(r0, r4, r3)
            android.util.Pair r3 = com.google.ads.interactivemedia.v3.internal.ub.a(r0)
            java.lang.String r12 = r6.f
            java.lang.String r13 = "audio/mp4a-latm"
            r14 = 0
            r15 = -1
            r16 = -1
            java.lang.Object r4 = r3.second
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r17 = r4.intValue()
            java.lang.Object r3 = r3.first
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r18 = r3.intValue()
            java.util.List r19 = java.util.Collections.singletonList(r0)
            r20 = 0
            r21 = 0
            java.lang.String r0 = r6.e
            r22 = r0
            com.google.ads.interactivemedia.v3.internal.bs r0 = com.google.ads.interactivemedia.v3.internal.bs.a(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            r3 = 1024000000(0x3d090000, double:5.059232213E-315)
            int r8 = r0.t
            long r8 = (long) r8
            long r3 = r3 / r8
            r6.r = r3
            com.google.ads.interactivemedia.v3.internal.gc r3 = r6.g
            r3.a(r0)
            r6.q = r11
            goto L_0x00e7
        L_0x00e2:
            com.google.ads.interactivemedia.v3.internal.us r0 = r6.c
            r0.b(r4)
        L_0x00e7:
            com.google.ads.interactivemedia.v3.internal.us r0 = r6.c
            r0.b(r5)
            com.google.ads.interactivemedia.v3.internal.us r0 = r6.c
            int r0 = r0.c(r1)
            int r0 = r0 - r10
            int r0 = r0 - r2
            boolean r1 = r6.l
            if (r1 == 0) goto L_0x00fc
            int r0 = r0 + -2
            r5 = r0
            goto L_0x00fd
        L_0x00fc:
            r5 = r0
        L_0x00fd:
            com.google.ads.interactivemedia.v3.internal.gc r1 = r6.g
            long r2 = r6.r
            r4 = 0
            r0 = r23
            r0.a(r1, r2, r4, r5)
            goto L_0x0004
        L_0x0109:
            com.google.ads.interactivemedia.v3.internal.ut r0 = r6.d
            byte[] r0 = r0.a
            boolean r0 = r6.a(r7, r0, r4)
            if (r0 == 0) goto L_0x0004
            com.google.ads.interactivemedia.v3.internal.gc r0 = r6.h
            com.google.ads.interactivemedia.v3.internal.ut r1 = r6.d
            r0.a(r1, r4)
            com.google.ads.interactivemedia.v3.internal.ut r0 = r6.d
            r0.c(r2)
            com.google.ads.interactivemedia.v3.internal.gc r1 = r6.h
            r2 = 0
            r5 = 10
            com.google.ads.interactivemedia.v3.internal.ut r0 = r6.d
            int r0 = r0.o()
            int r8 = r0 + 10
            r0 = r23
            r4 = r5
            r5 = r8
            r0.a(r1, r2, r4, r5)
            goto L_0x0004
        L_0x0136:
            int r0 = r24.b()
            if (r0 == 0) goto L_0x0004
            com.google.ads.interactivemedia.v3.internal.us r0 = r6.c
            byte[] r0 = r0.a
            byte[] r1 = r7.a
            int r2 = r24.d()
            byte r1 = r1[r2]
            r0[r9] = r1
            com.google.ads.interactivemedia.v3.internal.us r0 = r6.c
            r0.a(r10)
            com.google.ads.interactivemedia.v3.internal.us r0 = r6.c
            int r0 = r0.c(r5)
            int r1 = r6.o
            if (r1 == r8) goto L_0x0160
            if (r0 == r1) goto L_0x0160
            r23.d()
            goto L_0x0004
        L_0x0160:
            boolean r1 = r6.m
            if (r1 != 0) goto L_0x016c
            r6.m = r11
            int r1 = r6.p
            r6.n = r1
            r6.o = r0
        L_0x016c:
            r23.f()
            goto L_0x0004
        L_0x0171:
            byte[] r0 = r7.a
            int r4 = r24.d()
            int r12 = r24.c()
        L_0x017b:
            if (r4 >= r12) goto L_0x028d
            int r13 = r4 + 1
            byte r4 = r0[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r14 = r6.k
            r15 = 512(0x200, float:7.175E-43)
            if (r14 != r15) goto L_0x0240
            byte r14 = (byte) r4
            boolean r14 = a(r8, r14)
            if (r14 == 0) goto L_0x0240
            boolean r14 = r6.m
            if (r14 != 0) goto L_0x0220
            int r14 = r13 + -2
            int r15 = r14 + 1
            r7.c(r15)
            com.google.ads.interactivemedia.v3.internal.us r15 = r6.c
            byte[] r15 = r15.a
            boolean r15 = b(r7, r15, r11)
            if (r15 == 0) goto L_0x021d
            com.google.ads.interactivemedia.v3.internal.us r15 = r6.c
            r15.a(r5)
            com.google.ads.interactivemedia.v3.internal.us r15 = r6.c
            int r15 = r15.c(r11)
            int r9 = r6.n
            if (r9 == r8) goto L_0x01b6
            if (r15 != r9) goto L_0x021d
        L_0x01b6:
            int r9 = r6.o
            if (r9 == r8) goto L_0x01da
            com.google.ads.interactivemedia.v3.internal.us r9 = r6.c
            byte[] r9 = r9.a
            boolean r9 = b(r7, r9, r11)
            if (r9 != 0) goto L_0x01c6
            r9 = 1
            goto L_0x021e
        L_0x01c6:
            com.google.ads.interactivemedia.v3.internal.us r9 = r6.c
            r9.a(r10)
            com.google.ads.interactivemedia.v3.internal.us r9 = r6.c
            int r9 = r9.c(r5)
            int r10 = r6.o
            if (r9 != r10) goto L_0x021d
            int r9 = r14 + 2
            r7.c(r9)
        L_0x01da:
            com.google.ads.interactivemedia.v3.internal.us r9 = r6.c
            byte[] r9 = r9.a
            boolean r9 = b(r7, r9, r5)
            if (r9 != 0) goto L_0x01e6
            r9 = 1
            goto L_0x021e
        L_0x01e6:
            com.google.ads.interactivemedia.v3.internal.us r9 = r6.c
            r10 = 14
            r9.a(r10)
            com.google.ads.interactivemedia.v3.internal.us r9 = r6.c
            int r9 = r9.c(r1)
            if (r9 <= r2) goto L_0x021d
            int r14 = r14 + r9
            int r9 = r14 + 1
            int r10 = r24.c()
            if (r9 < r10) goto L_0x0200
            r9 = 1
            goto L_0x021e
        L_0x0200:
            byte[] r10 = r7.a
            byte r10 = r10[r14]
            byte[] r14 = r7.a
            byte r14 = r14[r9]
            boolean r10 = a(r10, r14)
            if (r10 == 0) goto L_0x021d
            int r10 = r6.n
            if (r10 == r8) goto L_0x021b
            byte[] r10 = r7.a
            byte r9 = r10[r9]
            r9 = r9 & 8
            int r9 = r9 >> r3
            if (r9 != r15) goto L_0x021d
        L_0x021b:
            r9 = 1
            goto L_0x021e
        L_0x021d:
            r9 = 0
        L_0x021e:
            if (r9 == 0) goto L_0x0240
        L_0x0220:
            r0 = r4 & 8
            int r0 = r0 >> r3
            r6.p = r0
            r0 = r4 & 1
            if (r0 != 0) goto L_0x022b
            r0 = 1
            goto L_0x022c
        L_0x022b:
            r0 = 0
        L_0x022c:
            r6.l = r0
            boolean r0 = r6.m
            if (r0 != 0) goto L_0x0238
            r6.i = r11
            r0 = 0
            r6.j = r0
            goto L_0x023b
        L_0x0238:
            r23.f()
        L_0x023b:
            r7.c(r13)
            goto L_0x0004
        L_0x0240:
            int r9 = r6.k
            r4 = r4 | r9
            r10 = 329(0x149, float:4.61E-43)
            if (r4 == r10) goto L_0x0282
            r10 = 511(0x1ff, float:7.16E-43)
            if (r4 == r10) goto L_0x027b
            r10 = 836(0x344, float:1.171E-42)
            if (r4 == r10) goto L_0x0274
            r10 = 1075(0x433, float:1.506E-42)
            if (r4 == r10) goto L_0x0262
            r4 = 256(0x100, float:3.59E-43)
            if (r9 == r4) goto L_0x025d
            r6.k = r4
            int r13 = r13 + -1
            r4 = r13
            goto L_0x025e
        L_0x025d:
            r4 = r13
        L_0x025e:
            r9 = 0
            r10 = 2
            goto L_0x017b
        L_0x0262:
            r9 = 2
            r6.i = r9
            byte[] r0 = a
            int r0 = r0.length
            r6.j = r0
            r10 = 0
            r6.s = r10
            com.google.ads.interactivemedia.v3.internal.ut r0 = r6.d
            r0.c(r10)
            r4 = r13
            goto L_0x028d
        L_0x0274:
            r9 = 2
            r10 = 0
            r4 = 1024(0x400, float:1.435E-42)
            r6.k = r4
            goto L_0x0288
        L_0x027b:
            r4 = 512(0x200, float:7.175E-43)
            r9 = 2
            r10 = 0
            r6.k = r4
            goto L_0x0288
        L_0x0282:
            r9 = 2
            r10 = 0
            r4 = 768(0x300, float:1.076E-42)
            r6.k = r4
        L_0x0288:
            r4 = r13
            r9 = 0
            r10 = 2
            goto L_0x017b
        L_0x028d:
            r7.c(r4)
            goto L_0x0004
        L_0x0292:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.hz.a(com.google.ads.interactivemedia.v3.internal.ut):void");
    }

    public final long c() {
        return this.r;
    }

    private final void d() {
        this.m = false;
        e();
    }

    private final boolean a(ut utVar, byte[] bArr, int i2) {
        int min = Math.min(utVar.b(), i2 - this.j);
        utVar.a(bArr, this.j, min);
        this.j += min;
        return this.j == i2;
    }

    private final void e() {
        this.i = 0;
        this.j = 0;
        this.k = 256;
    }

    private final void a(gc gcVar, long j2, int i2, int i3) {
        this.i = 4;
        this.j = i2;
        this.u = gcVar;
        this.v = j2;
        this.s = i3;
    }

    private final void f() {
        this.i = 3;
        this.j = 0;
    }

    private static boolean a(byte b2, byte b3) {
        return a((int) ((b2 & 255) << 8) | (b3 & 255));
    }

    private static boolean b(ut utVar, byte[] bArr, int i2) {
        if (utVar.b() < i2) {
            return false;
        }
        utVar.a(bArr, 0, i2);
        return true;
    }
}

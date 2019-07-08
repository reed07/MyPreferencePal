package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
public final class hv implements fq {
    private static final int a = vf.h("RCC\u0001");
    private final bs b;
    private final ut c = new ut(9);
    private gc d;
    private int e = 0;
    private int f;
    private long g;
    private int h;
    private int i;

    public hv(bs bsVar) {
        this.b = bsVar;
    }

    public final void c() {
    }

    public final void a(fs fsVar) {
        fsVar.a(new ga(-9223372036854775807L));
        this.d = fsVar.a(0, 3);
        fsVar.a();
        this.d.a(this.b);
    }

    public final boolean a(fr frVar) throws IOException, InterruptedException {
        this.c.a();
        frVar.c(this.c.a, 0, 8);
        if (this.c.l() == a) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0091 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(com.google.ads.interactivemedia.v3.internal.fr r11, com.google.ads.interactivemedia.v3.internal.fx r12) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r10 = this;
        L_0x0000:
            int r12 = r10.e
            r0 = -1
            r1 = 1
            r2 = 0
            switch(r12) {
                case 0: goto L_0x00ad;
                case 1: goto L_0x0042;
                case 2: goto L_0x000e;
                default: goto L_0x0008;
            }
        L_0x0008:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            r11.<init>()
            throw r11
        L_0x000e:
            int r12 = r10.h
            if (r12 <= 0) goto L_0x0031
            com.google.ads.interactivemedia.v3.internal.ut r12 = r10.c
            r12.a()
            com.google.ads.interactivemedia.v3.internal.ut r12 = r10.c
            byte[] r12 = r12.a
            r0 = 3
            r11.b(r12, r2, r0)
            com.google.ads.interactivemedia.v3.internal.gc r12 = r10.d
            com.google.ads.interactivemedia.v3.internal.ut r3 = r10.c
            r12.a(r3, r0)
            int r12 = r10.i
            int r12 = r12 + r0
            r10.i = r12
            int r12 = r10.h
            int r12 = r12 - r1
            r10.h = r12
            goto L_0x000e
        L_0x0031:
            int r7 = r10.i
            if (r7 <= 0) goto L_0x003f
            com.google.ads.interactivemedia.v3.internal.gc r3 = r10.d
            long r4 = r10.g
            r6 = 1
            r8 = 0
            r9 = 0
            r3.a(r4, r6, r7, r8, r9)
        L_0x003f:
            r10.e = r1
            return r2
        L_0x0042:
            com.google.ads.interactivemedia.v3.internal.ut r12 = r10.c
            r12.a()
            int r12 = r10.f
            if (r12 != 0) goto L_0x0068
            com.google.ads.interactivemedia.v3.internal.ut r12 = r10.c
            byte[] r12 = r12.a
            r3 = 5
            boolean r12 = r11.a(r12, r2, r3, r1)
            if (r12 != 0) goto L_0x0058
            r1 = 0
            goto L_0x008a
        L_0x0058:
            com.google.ads.interactivemedia.v3.internal.ut r12 = r10.c
            long r3 = r12.j()
            r5 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 * r5
            r5 = 45
            long r3 = r3 / r5
            r10.g = r3
            goto L_0x0080
        L_0x0068:
            if (r12 != r1) goto L_0x0094
            com.google.ads.interactivemedia.v3.internal.ut r12 = r10.c
            byte[] r12 = r12.a
            r3 = 9
            boolean r12 = r11.a(r12, r2, r3, r1)
            if (r12 != 0) goto L_0x0078
            r1 = 0
            goto L_0x008a
        L_0x0078:
            com.google.ads.interactivemedia.v3.internal.ut r12 = r10.c
            long r3 = r12.m()
            r10.g = r3
        L_0x0080:
            com.google.ads.interactivemedia.v3.internal.ut r12 = r10.c
            int r12 = r12.e()
            r10.h = r12
            r10.i = r2
        L_0x008a:
            if (r1 == 0) goto L_0x0091
            r12 = 2
            r10.e = r12
            goto L_0x0000
        L_0x0091:
            r10.e = r2
            return r0
        L_0x0094:
            com.google.ads.interactivemedia.v3.internal.ca r11 = new com.google.ads.interactivemedia.v3.internal.ca
            r0 = 39
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r0 = "Unsupported version number: "
            r1.append(r0)
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            r11.<init>(r12)
            throw r11
        L_0x00ad:
            com.google.ads.interactivemedia.v3.internal.ut r12 = r10.c
            r12.a()
            com.google.ads.interactivemedia.v3.internal.ut r12 = r10.c
            byte[] r12 = r12.a
            r3 = 8
            boolean r12 = r11.a(r12, r2, r3, r1)
            if (r12 == 0) goto L_0x00da
            com.google.ads.interactivemedia.v3.internal.ut r12 = r10.c
            int r12 = r12.l()
            int r2 = a
            if (r12 != r2) goto L_0x00d2
            com.google.ads.interactivemedia.v3.internal.ut r12 = r10.c
            int r12 = r12.e()
            r10.f = r12
            r2 = 1
            goto L_0x00da
        L_0x00d2:
            java.io.IOException r11 = new java.io.IOException
            java.lang.String r12 = "Input not RawCC"
            r11.<init>(r12)
            throw r11
        L_0x00da:
            if (r2 == 0) goto L_0x00e0
            r10.e = r1
            goto L_0x0000
        L_0x00e0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.hv.a(com.google.ads.interactivemedia.v3.internal.fr, com.google.ads.interactivemedia.v3.internal.fx):int");
    }

    public final void a(long j, long j2) {
        this.e = 0;
    }
}

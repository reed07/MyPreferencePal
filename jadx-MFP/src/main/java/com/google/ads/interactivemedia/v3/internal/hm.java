package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

/* compiled from: IMASDK */
public final class hm implements fq, fy {
    private static final int a = vf.h("qt  ");
    private final int b;
    private final ut c;
    private final ut d;
    private final ut e;
    private final ArrayDeque<gv> f;
    private int g;
    private int h;
    private long i;
    private int j;
    private ut k;
    private int l;
    private int m;
    private int n;
    private fs o;
    private hn[] p;
    private long[][] q;
    private int r;
    private long s;
    private boolean t;

    public hm() {
        this(0);
    }

    public final boolean a() {
        return true;
    }

    public final void c() {
    }

    private hm(int i2) {
        this.b = 0;
        this.e = new ut(16);
        this.f = new ArrayDeque<>();
        this.c = new ut(up.a);
        this.d = new ut(4);
        this.l = -1;
    }

    public final boolean a(fr frVar) throws IOException, InterruptedException {
        return hq.b(frVar);
    }

    public final void a(fs fsVar) {
        this.o = fsVar;
    }

    public final void a(long j2, long j3) {
        this.f.clear();
        this.j = 0;
        this.l = -1;
        this.m = 0;
        this.n = 0;
        if (j2 == 0) {
            d();
            return;
        }
        hn[] hnVarArr = this.p;
        if (hnVarArr != null) {
            for (hn hnVar : hnVarArr) {
                hu huVar = hnVar.b;
                int a2 = huVar.a(j3);
                if (a2 == -1) {
                    a2 = huVar.b(j3);
                }
                hnVar.d = a2;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:200:0x035d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x0201 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x0006 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0006 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(com.google.ads.interactivemedia.v3.internal.fr r31, com.google.ads.interactivemedia.v3.internal.fx r32) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r30 = this;
            r0 = r30
            r1 = r31
            r2 = r32
        L_0x0006:
            int r3 = r0.g
            r5 = 0
            r7 = 262144(0x40000, double:1.295163E-318)
            r10 = -1
            r11 = 8
            switch(r3) {
                case 0: goto L_0x0203;
                case 1: goto L_0x017a;
                case 2: goto L_0x0019;
                default: goto L_0x0013;
            }
        L_0x0013:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x0019:
            long r14 = r31.c()
            int r3 = r0.l
            if (r3 != r10) goto L_0x009b
            r16 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r18 = r16
            r20 = r18
            r24 = r20
            r3 = 0
            r9 = 1
            r11 = 1
            r22 = -1
            r23 = -1
        L_0x0033:
            com.google.ads.interactivemedia.v3.internal.hn[] r4 = r0.p
            int r13 = r4.length
            if (r3 >= r13) goto L_0x007f
            r4 = r4[r3]
            int r13 = r4.d
            com.google.ads.interactivemedia.v3.internal.hu r12 = r4.b
            int r12 = r12.b
            if (r13 == r12) goto L_0x007c
            com.google.ads.interactivemedia.v3.internal.hu r4 = r4.b
            long[] r4 = r4.c
            r27 = r4[r13]
            long[][] r4 = r0.q
            r4 = r4[r3]
            r12 = r4[r13]
            long r27 = r27 - r14
            int r4 = (r27 > r5 ? 1 : (r27 == r5 ? 0 : -1))
            if (r4 < 0) goto L_0x005b
            int r4 = (r27 > r7 ? 1 : (r27 == r7 ? 0 : -1))
            if (r4 < 0) goto L_0x0059
            goto L_0x005b
        L_0x0059:
            r4 = 0
            goto L_0x005c
        L_0x005b:
            r4 = 1
        L_0x005c:
            if (r4 != 0) goto L_0x0060
            if (r9 != 0) goto L_0x0066
        L_0x0060:
            if (r4 != r9) goto L_0x006c
            int r29 = (r27 > r24 ? 1 : (r27 == r24 ? 0 : -1))
            if (r29 >= 0) goto L_0x006c
        L_0x0066:
            r23 = r3
            r9 = r4
            r20 = r12
            goto L_0x006e
        L_0x006c:
            r27 = r24
        L_0x006e:
            int r24 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1))
            if (r24 >= 0) goto L_0x007a
            r22 = r3
            r11 = r4
            r18 = r12
            r24 = r27
            goto L_0x007c
        L_0x007a:
            r24 = r27
        L_0x007c:
            int r3 = r3 + 1
            goto L_0x0033
        L_0x007f:
            int r3 = (r18 > r16 ? 1 : (r18 == r16 ? 0 : -1))
            if (r3 == 0) goto L_0x0092
            if (r11 == 0) goto L_0x0092
            r3 = 10485760(0xa00000, double:5.180654E-317)
            long r18 = r18 + r3
            int r3 = (r20 > r18 ? 1 : (r20 == r18 ? 0 : -1))
            if (r3 >= 0) goto L_0x008f
            goto L_0x0092
        L_0x008f:
            r3 = r22
            goto L_0x0094
        L_0x0092:
            r3 = r23
        L_0x0094:
            r0.l = r3
            int r3 = r0.l
            if (r3 != r10) goto L_0x009b
            return r10
        L_0x009b:
            com.google.ads.interactivemedia.v3.internal.hn[] r3 = r0.p
            int r4 = r0.l
            r3 = r3[r4]
            com.google.ads.interactivemedia.v3.internal.gc r4 = r3.c
            int r9 = r3.d
            com.google.ads.interactivemedia.v3.internal.hu r11 = r3.b
            long[] r11 = r11.c
            r12 = r11[r9]
            com.google.ads.interactivemedia.v3.internal.hu r11 = r3.b
            int[] r11 = r11.d
            r11 = r11[r9]
            long r14 = r12 - r14
            int r10 = r0.m
            long r7 = (long) r10
            long r14 = r14 + r7
            int r7 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r7 < 0) goto L_0x0176
            r5 = 262144(0x40000, double:1.295163E-318)
            int r7 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r7 < 0) goto L_0x00c4
            goto L_0x0176
        L_0x00c4:
            com.google.ads.interactivemedia.v3.internal.hr r2 = r3.a
            int r2 = r2.g
            r5 = 1
            if (r2 != r5) goto L_0x00d0
            r5 = 8
            long r14 = r14 + r5
            int r11 = r11 + -8
        L_0x00d0:
            int r2 = (int) r14
            r1.b(r2)
            com.google.ads.interactivemedia.v3.internal.hr r2 = r3.a
            int r2 = r2.j
            if (r2 == 0) goto L_0x013a
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.d
            byte[] r2 = r2.a
            r5 = 0
            r2[r5] = r5
            r6 = 1
            r2[r6] = r5
            r6 = 2
            r2[r6] = r5
            com.google.ads.interactivemedia.v3.internal.hr r5 = r3.a
            int r5 = r5.j
            com.google.ads.interactivemedia.v3.internal.hr r6 = r3.a
            int r6 = r6.j
            r7 = 4
            int r6 = 4 - r6
        L_0x00f2:
            int r7 = r0.m
            if (r7 >= r11) goto L_0x0137
            int r7 = r0.n
            if (r7 != 0) goto L_0x0127
            r1.b(r2, r6, r5)
            com.google.ads.interactivemedia.v3.internal.ut r7 = r0.d
            r8 = 0
            r7.c(r8)
            com.google.ads.interactivemedia.v3.internal.ut r7 = r0.d
            int r7 = r7.l()
            if (r7 < 0) goto L_0x011f
            r0.n = r7
            com.google.ads.interactivemedia.v3.internal.ut r7 = r0.c
            r7.c(r8)
            com.google.ads.interactivemedia.v3.internal.ut r7 = r0.c
            r8 = 4
            r4.a(r7, r8)
            int r7 = r0.m
            int r7 = r7 + r8
            r0.m = r7
            int r11 = r11 + r6
            goto L_0x00f2
        L_0x011f:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "Invalid NAL length"
            r1.<init>(r2)
            throw r1
        L_0x0127:
            r8 = 0
            int r7 = r4.a(r1, r7, r8)
            int r8 = r0.m
            int r8 = r8 + r7
            r0.m = r8
            int r8 = r0.n
            int r8 = r8 - r7
            r0.n = r8
            goto L_0x00f2
        L_0x0137:
            r20 = r11
            goto L_0x0152
        L_0x013a:
            int r2 = r0.m
            if (r2 >= r11) goto L_0x0150
            int r2 = r11 - r2
            r5 = 0
            int r2 = r4.a(r1, r2, r5)
            int r5 = r0.m
            int r5 = r5 + r2
            r0.m = r5
            int r5 = r0.n
            int r5 = r5 - r2
            r0.n = r5
            goto L_0x013a
        L_0x0150:
            r20 = r11
        L_0x0152:
            com.google.ads.interactivemedia.v3.internal.hu r1 = r3.b
            long[] r1 = r1.f
            r17 = r1[r9]
            com.google.ads.interactivemedia.v3.internal.hu r1 = r3.b
            int[] r1 = r1.g
            r19 = r1[r9]
            r21 = 0
            r22 = 0
            r16 = r4
            r16.a(r17, r19, r20, r21, r22)
            int r1 = r3.d
            r4 = 1
            int r1 = r1 + r4
            r3.d = r1
            r1 = -1
            r0.l = r1
            r1 = 0
            r0.m = r1
            r0.n = r1
            return r1
        L_0x0176:
            r4 = 1
            r2.a = r12
            return r4
        L_0x017a:
            long r3 = r0.i
            int r5 = r0.j
            long r5 = (long) r5
            long r3 = r3 - r5
            long r5 = r31.c()
            long r5 = r5 + r3
            com.google.ads.interactivemedia.v3.internal.ut r7 = r0.k
            if (r7 == 0) goto L_0x01db
            byte[] r7 = r7.a
            int r8 = r0.j
            int r4 = (int) r3
            r1.b(r7, r8, r4)
            int r3 = r0.h
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.a
            if (r3 != r4) goto L_0x01be
            com.google.ads.interactivemedia.v3.internal.ut r3 = r0.k
            r3.c(r11)
            int r4 = r3.l()
            int r7 = a
            if (r4 != r7) goto L_0x01a6
            r3 = 1
            goto L_0x01bb
        L_0x01a6:
            r4 = 4
            r3.d(r4)
        L_0x01aa:
            int r4 = r3.b()
            if (r4 <= 0) goto L_0x01ba
            int r4 = r3.l()
            int r7 = a
            if (r4 != r7) goto L_0x01aa
            r3 = 1
            goto L_0x01bb
        L_0x01ba:
            r3 = 0
        L_0x01bb:
            r0.t = r3
            goto L_0x01e6
        L_0x01be:
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r3 = r0.f
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x01e6
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r3 = r0.f
            java.lang.Object r3 = r3.peek()
            com.google.ads.interactivemedia.v3.internal.gv r3 = (com.google.ads.interactivemedia.v3.internal.gv) r3
            com.google.ads.interactivemedia.v3.internal.gw r4 = new com.google.ads.interactivemedia.v3.internal.gw
            int r7 = r0.h
            com.google.ads.interactivemedia.v3.internal.ut r8 = r0.k
            r4.<init>(r7, r8)
            r3.a(r4)
            goto L_0x01e6
        L_0x01db:
            r7 = 262144(0x40000, double:1.295163E-318)
            int r9 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r9 >= 0) goto L_0x01e8
            int r4 = (int) r3
            r1.b(r4)
        L_0x01e6:
            r3 = 0
            goto L_0x01f0
        L_0x01e8:
            long r7 = r31.c()
            long r7 = r7 + r3
            r2.a = r7
            r3 = 1
        L_0x01f0:
            r0.b(r5)
            if (r3 == 0) goto L_0x01fd
            int r3 = r0.g
            r4 = 2
            if (r3 == r4) goto L_0x01fd
            r26 = 1
            goto L_0x01ff
        L_0x01fd:
            r26 = 0
        L_0x01ff:
            if (r26 == 0) goto L_0x0006
            r3 = 1
            return r3
        L_0x0203:
            r3 = 1
            int r4 = r0.j
            if (r4 != 0) goto L_0x022d
            com.google.ads.interactivemedia.v3.internal.ut r4 = r0.e
            byte[] r4 = r4.a
            r7 = 0
            boolean r4 = r1.a(r4, r7, r11, r3)
            if (r4 != 0) goto L_0x0216
            r5 = 0
            goto L_0x035b
        L_0x0216:
            r0.j = r11
            com.google.ads.interactivemedia.v3.internal.ut r3 = r0.e
            r3.c(r7)
            com.google.ads.interactivemedia.v3.internal.ut r3 = r0.e
            long r3 = r3.j()
            r0.i = r3
            com.google.ads.interactivemedia.v3.internal.ut r3 = r0.e
            int r3 = r3.l()
            r0.h = r3
        L_0x022d:
            long r3 = r0.i
            r7 = 1
            int r9 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x024a
            com.google.ads.interactivemedia.v3.internal.ut r3 = r0.e
            byte[] r3 = r3.a
            r1.b(r3, r11, r11)
            int r3 = r0.j
            int r3 = r3 + r11
            r0.j = r3
            com.google.ads.interactivemedia.v3.internal.ut r3 = r0.e
            long r3 = r3.q()
            r0.i = r3
            goto L_0x0279
        L_0x024a:
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0279
            long r3 = r31.d()
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x026a
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r7 = r0.f
            boolean r7 = r7.isEmpty()
            if (r7 != 0) goto L_0x026a
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r3 = r0.f
            java.lang.Object r3 = r3.peek()
            com.google.ads.interactivemedia.v3.internal.gv r3 = (com.google.ads.interactivemedia.v3.internal.gv) r3
            long r3 = r3.be
        L_0x026a:
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0279
            long r5 = r31.c()
            long r3 = r3 - r5
            int r5 = r0.j
            long r5 = (long) r5
            long r3 = r3 + r5
            r0.i = r3
        L_0x0279:
            long r3 = r0.i
            int r5 = r0.j
            long r5 = (long) r5
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 < 0) goto L_0x035f
            int r3 = r0.h
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.O
            if (r3 == r4) goto L_0x02a3
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.Q
            if (r3 == r4) goto L_0x02a3
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.R
            if (r3 == r4) goto L_0x02a3
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.S
            if (r3 == r4) goto L_0x02a3
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.T
            if (r3 == r4) goto L_0x02a3
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.aa
            if (r3 == r4) goto L_0x02a3
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.aL
            if (r3 != r4) goto L_0x02a1
            goto L_0x02a3
        L_0x02a1:
            r3 = 0
            goto L_0x02a4
        L_0x02a3:
            r3 = 1
        L_0x02a4:
            if (r3 == 0) goto L_0x02d2
            long r3 = r31.c()
            long r5 = r0.i
            long r3 = r3 + r5
            int r5 = r0.j
            long r5 = (long) r5
            long r3 = r3 - r5
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r5 = r0.f
            com.google.ads.interactivemedia.v3.internal.gv r6 = new com.google.ads.interactivemedia.v3.internal.gv
            int r7 = r0.h
            r6.<init>(r7, r3)
            r5.push(r6)
            long r5 = r0.i
            int r7 = r0.j
            long r7 = (long) r7
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x02cc
            r0.b(r3)
            r5 = 1
            goto L_0x035b
        L_0x02cc:
            r30.d()
            r5 = 1
            goto L_0x035b
        L_0x02d2:
            int r3 = r0.h
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.ac
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.P
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.ad
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.ae
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.aw
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.ax
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.ay
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.ab
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.az
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.aA
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.aB
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.aC
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.aD
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.Z
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.a
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.aK
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.aM
            if (r3 == r4) goto L_0x031f
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.aN
            if (r3 != r4) goto L_0x031d
            goto L_0x031f
        L_0x031d:
            r3 = 0
            goto L_0x0320
        L_0x031f:
            r3 = 1
        L_0x0320:
            if (r3 == 0) goto L_0x0355
            int r3 = r0.j
            if (r3 != r11) goto L_0x0328
            r3 = 1
            goto L_0x0329
        L_0x0328:
            r3 = 0
        L_0x0329:
            com.google.ads.interactivemedia.v3.internal.qi.c(r3)
            long r3 = r0.i
            r5 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 > 0) goto L_0x0337
            r3 = 1
            goto L_0x0338
        L_0x0337:
            r3 = 0
        L_0x0338:
            com.google.ads.interactivemedia.v3.internal.qi.c(r3)
            com.google.ads.interactivemedia.v3.internal.ut r3 = new com.google.ads.interactivemedia.v3.internal.ut
            long r4 = r0.i
            int r5 = (int) r4
            r3.<init>(r5)
            r0.k = r3
            com.google.ads.interactivemedia.v3.internal.ut r3 = r0.e
            byte[] r3 = r3.a
            com.google.ads.interactivemedia.v3.internal.ut r4 = r0.k
            byte[] r4 = r4.a
            r5 = 0
            java.lang.System.arraycopy(r3, r5, r4, r5, r11)
            r5 = 1
            r0.g = r5
            goto L_0x035b
        L_0x0355:
            r5 = 1
            r3 = 0
            r0.k = r3
            r0.g = r5
        L_0x035b:
            if (r5 != 0) goto L_0x0006
            r3 = -1
            return r3
        L_0x035f:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "Atom size less than header length (unsupported)."
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.hm.a(com.google.ads.interactivemedia.v3.internal.fr, com.google.ads.interactivemedia.v3.internal.fx):int");
    }

    public final long b() {
        return this.s;
    }

    public final fz a(long j2) {
        long j3;
        long j4;
        long j5;
        long j6;
        hn[] hnVarArr = this.p;
        if (hnVarArr.length == 0) {
            return new fz(gb.a);
        }
        int i2 = this.r;
        if (i2 != -1) {
            hu huVar = hnVarArr[i2].b;
            int a2 = a(huVar, j2);
            if (a2 == -1) {
                return new fz(gb.a);
            }
            long j7 = huVar.f[a2];
            j3 = huVar.c[a2];
            if (j7 < j2 && a2 < huVar.b - 1) {
                int b2 = huVar.b(j2);
                if (!(b2 == -1 || b2 == a2)) {
                    j5 = huVar.f[b2];
                    j6 = huVar.c[b2];
                    j4 = j6;
                    j2 = j7;
                }
            }
            j6 = -1;
            j5 = -9223372036854775807L;
            j4 = j6;
            j2 = j7;
        } else {
            j3 = Long.MAX_VALUE;
            j4 = -1;
            j5 = -9223372036854775807L;
        }
        int i3 = 0;
        while (true) {
            hn[] hnVarArr2 = this.p;
            if (i3 >= hnVarArr2.length) {
                break;
            }
            if (i3 != this.r) {
                hu huVar2 = hnVarArr2[i3].b;
                long a3 = a(huVar2, j2, j3);
                if (j5 != -9223372036854775807L) {
                    j4 = a(huVar2, j5, j4);
                    j3 = a3;
                } else {
                    j3 = a3;
                }
            }
            i3++;
        }
        gb gbVar = new gb(j2, j3);
        if (j5 == -9223372036854775807L) {
            return new fz(gbVar);
        }
        return new fz(gbVar, new gb(j5, j4));
    }

    private final void d() {
        this.g = 0;
        this.j = 0;
    }

    private final void b(long j2) throws ca {
        js jsVar;
        int i2;
        ArrayList arrayList;
        while (!this.f.isEmpty() && ((gv) this.f.peek()).be == j2) {
            gv gvVar = (gv) this.f.pop();
            if (gvVar.bd == gu.O) {
                ArrayList arrayList2 = new ArrayList();
                fu fuVar = new fu();
                gw d2 = gvVar.d(gu.aK);
                js jsVar2 = null;
                if (d2 != null) {
                    jsVar = gx.a(d2, this.t);
                    if (jsVar != null) {
                        fuVar.a(jsVar);
                    }
                } else {
                    jsVar = null;
                }
                gv e2 = gvVar.e(gu.aL);
                if (e2 != null) {
                    jsVar2 = gx.a(e2);
                }
                boolean z = (this.b & 1) != 0;
                ArrayList arrayList3 = new ArrayList();
                int i3 = 0;
                while (i3 < gvVar.bg.size()) {
                    gv gvVar2 = (gv) gvVar.bg.get(i3);
                    if (gvVar2.bd == gu.Q) {
                        gv gvVar3 = gvVar2;
                        i2 = i3;
                        arrayList = arrayList3;
                        hr a2 = gx.a(gvVar2, gvVar.d(gu.P), -9223372036854775807L, null, z, this.t);
                        if (a2 != null) {
                            hu a3 = gx.a(a2, gvVar3.e(gu.R).e(gu.S).e(gu.T), fuVar);
                            if (a3.b != 0) {
                                arrayList.add(a3);
                            }
                        }
                    } else {
                        i2 = i3;
                        arrayList = arrayList3;
                    }
                    i3 = i2 + 1;
                    arrayList3 = arrayList;
                }
                ArrayList arrayList4 = arrayList3;
                int size = arrayList4.size();
                long j3 = -9223372036854775807L;
                long j4 = -9223372036854775807L;
                int i4 = 0;
                int i5 = -1;
                while (i4 < size) {
                    hu huVar = (hu) arrayList4.get(i4);
                    hr hrVar = huVar.a;
                    ArrayList arrayList5 = arrayList2;
                    long j5 = hrVar.e != j3 ? hrVar.e : huVar.h;
                    j4 = Math.max(j4, j5);
                    int i6 = size;
                    hn hnVar = new hn(hrVar, huVar, this.o.a(i4, hrVar.b));
                    bs a4 = hrVar.f.a(huVar.e + 30);
                    ArrayList arrayList6 = arrayList4;
                    if (hrVar.b == 2 && j5 > 0 && huVar.b > 1) {
                        a4 = a4.a(((float) huVar.b) / (((float) j5) / 1000000.0f));
                    }
                    hnVar.c.a(hl.a(hrVar.b, a4, jsVar, jsVar2, fuVar));
                    if (hrVar.b == 2) {
                        if (i5 == -1) {
                            i5 = arrayList5.size();
                        }
                    }
                    ArrayList arrayList7 = arrayList5;
                    arrayList7.add(hnVar);
                    i4++;
                    arrayList2 = arrayList7;
                    size = i6;
                    arrayList4 = arrayList6;
                    j3 = -9223372036854775807L;
                }
                ArrayList arrayList8 = arrayList2;
                long j6 = 0;
                this.r = i5;
                this.s = j4;
                this.p = (hn[]) arrayList8.toArray(new hn[0]);
                hn[] hnVarArr = this.p;
                long[][] jArr = new long[hnVarArr.length][];
                int[] iArr = new int[hnVarArr.length];
                long[] jArr2 = new long[hnVarArr.length];
                boolean[] zArr = new boolean[hnVarArr.length];
                for (int i7 = 0; i7 < hnVarArr.length; i7++) {
                    jArr[i7] = new long[hnVarArr[i7].b.b];
                    jArr2[i7] = hnVarArr[i7].b.f[0];
                }
                int i8 = 0;
                while (i8 < hnVarArr.length) {
                    long j7 = Long.MAX_VALUE;
                    int i9 = -1;
                    for (int i10 = 0; i10 < hnVarArr.length; i10++) {
                        if (!zArr[i10] && jArr2[i10] <= j7) {
                            j7 = jArr2[i10];
                            i9 = i10;
                        }
                    }
                    int i11 = iArr[i9];
                    jArr[i9][i11] = j6;
                    j6 += (long) hnVarArr[i9].b.d[i11];
                    int i12 = i11 + 1;
                    iArr[i9] = i12;
                    if (i12 < jArr[i9].length) {
                        jArr2[i9] = hnVarArr[i9].b.f[i12];
                    } else {
                        zArr[i9] = true;
                        i8++;
                    }
                }
                this.q = jArr;
                this.o.a();
                this.o.a(this);
                this.f.clear();
                this.g = 2;
            } else if (!this.f.isEmpty()) {
                ((gv) this.f.peek()).a(gvVar);
            }
        }
        if (this.g != 2) {
            d();
        }
    }

    private static long a(hu huVar, long j2, long j3) {
        int a2 = a(huVar, j2);
        if (a2 == -1) {
            return j3;
        }
        return Math.min(huVar.c[a2], j3);
    }

    private static int a(hu huVar, long j2) {
        int a2 = huVar.a(j2);
        return a2 == -1 ? huVar.b(j2) : a2;
    }
}

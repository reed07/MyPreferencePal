package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import android.util.SparseArray;
import com.google.ads.interactivemedia.v3.internal.fa.a;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* compiled from: IMASDK */
public final class hg implements fq {
    private static final int a = vf.h("seig");
    private static final byte[] b = {-94, 57, 79, 82, 90, -101, 79, Ascii.DC4, -94, 68, 108, 66, 124, 100, -115, -12};
    private static final bs c = bs.a(null, MimeTypes.APPLICATION_EMSG, Long.MAX_VALUE);
    private long A;
    private hi B;
    private int C;
    private int D;
    private int E;
    private boolean F;
    private fs G;
    private gc[] H;
    private gc[] I;
    private boolean J;
    private final int d;
    private final hr e;
    private final List<bs> f;
    private final fa g;
    private final SparseArray<hi> h;
    private final ut i;
    private final ut j;
    private final ut k;
    private final ve l;
    private final ut m;
    private final byte[] n;
    private final ArrayDeque<gv> o;
    private final ArrayDeque<hh> p;
    private final gc q;
    private int r;
    private int s;
    private long t;
    private int u;
    private ut v;
    private long w;
    private int x;
    private long y;
    private long z;

    public hg() {
        this(0);
    }

    public final void c() {
    }

    private hg(int i2) {
        this(0, null);
    }

    private hg(int i2, ve veVar) {
        this(i2, null, null, null);
    }

    private hg(int i2, ve veVar, hr hrVar, fa faVar) {
        this(i2, veVar, null, null, Collections.emptyList());
    }

    public hg(int i2, ve veVar, hr hrVar, fa faVar, List<bs> list) {
        this(i2, veVar, hrVar, faVar, list, null);
    }

    public hg(int i2, ve veVar, hr hrVar, fa faVar, List<bs> list, gc gcVar) {
        this.d = i2 | (hrVar != null ? 8 : 0);
        this.l = veVar;
        this.e = hrVar;
        this.g = faVar;
        this.f = Collections.unmodifiableList(list);
        this.q = gcVar;
        this.m = new ut(16);
        this.i = new ut(up.a);
        this.j = new ut(5);
        this.k = new ut();
        this.n = new byte[16];
        this.o = new ArrayDeque<>();
        this.p = new ArrayDeque<>();
        this.h = new SparseArray<>();
        this.z = -9223372036854775807L;
        this.y = -9223372036854775807L;
        this.A = -9223372036854775807L;
        a();
    }

    public final boolean a(fr frVar) throws IOException, InterruptedException {
        return hq.a(frVar);
    }

    public final void a(fs fsVar) {
        this.G = fsVar;
        hr hrVar = this.e;
        if (hrVar != null) {
            hi hiVar = new hi(fsVar.a(0, hrVar.b));
            hiVar.a(this.e, new hd(0, 0, 0, 0));
            this.h.put(0, hiVar);
            b();
            this.G.a();
        }
    }

    public final void a(long j2, long j3) {
        int size = this.h.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((hi) this.h.valueAt(i2)).a();
        }
        this.p.clear();
        this.x = 0;
        this.y = j3;
        this.o.clear();
        a();
    }

    /* JADX WARNING: Removed duplicated region for block: B:259:0x0602 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:268:0x03d2 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:272:0x0004 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:273:0x0004 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(com.google.ads.interactivemedia.v3.internal.fr r27, com.google.ads.interactivemedia.v3.internal.fx r28) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r26 = this;
            r0 = r26
            r1 = r27
        L_0x0004:
            int r2 = r0.r
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r5 = 2
            r6 = 3
            r7 = 4
            r8 = 0
            r9 = 8
            r11 = 0
            switch(r2) {
                case 0: goto L_0x0218;
                case 1: goto L_0x007d;
                case 2: goto L_0x0026;
                default: goto L_0x0015;
            }
        L_0x0015:
            if (r2 != r6) goto L_0x0494
            com.google.ads.interactivemedia.v3.internal.hi r2 = r0.B
            if (r2 != 0) goto L_0x043e
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r2 = r0.h
            int r7 = r2.size()
            r12 = r3
            r4 = r8
            r3 = 0
            goto L_0x03e4
        L_0x0026:
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r2 = r0.h
            int r2 = r2.size()
            r4 = r3
            r3 = 0
        L_0x002e:
            if (r3 >= r2) goto L_0x0052
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r7 = r0.h
            java.lang.Object r7 = r7.valueAt(r3)
            com.google.ads.interactivemedia.v3.internal.hi r7 = (com.google.ads.interactivemedia.v3.internal.hi) r7
            com.google.ads.interactivemedia.v3.internal.ht r7 = r7.b
            boolean r9 = r7.q
            if (r9 == 0) goto L_0x004f
            long r9 = r7.c
            int r12 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r12 >= 0) goto L_0x004f
            long r4 = r7.c
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r7 = r0.h
            java.lang.Object r7 = r7.valueAt(r3)
            com.google.ads.interactivemedia.v3.internal.hi r7 = (com.google.ads.interactivemedia.v3.internal.hi) r7
            r8 = r7
        L_0x004f:
            int r3 = r3 + 1
            goto L_0x002e
        L_0x0052:
            if (r8 != 0) goto L_0x0057
            r0.r = r6
            goto L_0x0004
        L_0x0057:
            long r2 = r27.c()
            long r4 = r4 - r2
            int r2 = (int) r4
            if (r2 < 0) goto L_0x0075
            r1.b(r2)
            com.google.ads.interactivemedia.v3.internal.ht r2 = r8.b
            com.google.ads.interactivemedia.v3.internal.ut r3 = r2.p
            byte[] r3 = r3.a
            int r4 = r2.o
            r1.b(r3, r11, r4)
            com.google.ads.interactivemedia.v3.internal.ut r3 = r2.p
            r3.c(r11)
            r2.q = r11
            goto L_0x0004
        L_0x0075:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "Offset to encryption data was negative."
            r1.<init>(r2)
            throw r1
        L_0x007d:
            long r2 = r0.t
            int r3 = (int) r2
            int r2 = r0.u
            int r3 = r3 - r2
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.v
            if (r2 == 0) goto L_0x020c
            byte[] r2 = r2.a
            r1.b(r2, r9, r3)
            com.google.ads.interactivemedia.v3.internal.gw r2 = new com.google.ads.interactivemedia.v3.internal.gw
            int r3 = r0.s
            com.google.ads.interactivemedia.v3.internal.ut r4 = r0.v
            r2.<init>(r3, r4)
            long r3 = r27.c()
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r6 = r0.o
            boolean r6 = r6.isEmpty()
            if (r6 != 0) goto L_0x00ae
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r3 = r0.o
            java.lang.Object r3 = r3.peek()
            com.google.ads.interactivemedia.v3.internal.gv r3 = (com.google.ads.interactivemedia.v3.internal.gv) r3
            r3.a(r2)
            goto L_0x020f
        L_0x00ae:
            int r6 = r2.bd
            int r8 = com.google.ads.interactivemedia.v3.internal.gu.N
            if (r6 != r8) goto L_0x017a
            com.google.ads.interactivemedia.v3.internal.ut r2 = r2.be
            r2.c(r9)
            int r6 = r2.l()
            int r6 = com.google.ads.interactivemedia.v3.internal.gu.a(r6)
            r2.d(r7)
            long r8 = r2.j()
            if (r6 != 0) goto L_0x00d7
            long r12 = r2.j()
            long r14 = r2.j()
            long r3 = r3 + r14
            r18 = r3
            r3 = r12
            goto L_0x00e3
        L_0x00d7:
            long r12 = r2.q()
            long r14 = r2.q()
            long r3 = r3 + r14
            r18 = r3
            r3 = r12
        L_0x00e3:
            r14 = 1000000(0xf4240, double:4.940656E-318)
            r12 = r3
            r16 = r8
            long r20 = com.google.ads.interactivemedia.v3.internal.vf.c(r12, r14, r16)
            r2.d(r5)
            int r5 = r2.f()
            int[] r6 = new int[r5]
            long[] r14 = new long[r5]
            long[] r15 = new long[r5]
            long[] r12 = new long[r5]
            r16 = r3
            r3 = r20
        L_0x0100:
            if (r11 >= r5) goto L_0x014f
            int r13 = r2.l()
            r22 = -2147483648(0xffffffff80000000, float:-0.0)
            r22 = r13 & r22
            if (r22 != 0) goto L_0x0147
            long r22 = r2.j()
            r24 = 2147483647(0x7fffffff, float:NaN)
            r13 = r13 & r24
            r6[r11] = r13
            r14[r11] = r18
            r12[r11] = r3
            long r3 = r16 + r22
            r16 = 1000000(0xf4240, double:4.940656E-318)
            r10 = r12
            r12 = r3
            r25 = r14
            r7 = r15
            r14 = r16
            r16 = r8
            long r12 = com.google.ads.interactivemedia.v3.internal.vf.c(r12, r14, r16)
            r14 = r10[r11]
            long r14 = r12 - r14
            r7[r11] = r14
            r14 = 4
            r2.d(r14)
            r14 = r6[r11]
            long r14 = (long) r14
            long r18 = r18 + r14
            int r11 = r11 + 1
            r16 = r3
            r15 = r7
            r3 = r12
            r14 = r25
            r7 = 4
            r12 = r10
            goto L_0x0100
        L_0x0147:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "Unhandled indirect reference"
            r1.<init>(r2)
            throw r1
        L_0x014f:
            r10 = r12
            r25 = r14
            r7 = r15
            java.lang.Long r2 = java.lang.Long.valueOf(r20)
            com.google.ads.interactivemedia.v3.internal.fn r3 = new com.google.ads.interactivemedia.v3.internal.fn
            r4 = r25
            r3.<init>(r6, r4, r7, r10)
            android.util.Pair r2 = android.util.Pair.create(r2, r3)
            java.lang.Object r3 = r2.first
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            r0.A = r3
            com.google.ads.interactivemedia.v3.internal.fs r3 = r0.G
            java.lang.Object r2 = r2.second
            com.google.ads.interactivemedia.v3.internal.fy r2 = (com.google.ads.interactivemedia.v3.internal.fy) r2
            r3.a(r2)
            r2 = 1
            r0.J = r2
            goto L_0x020f
        L_0x017a:
            int r3 = r2.bd
            int r4 = com.google.ads.interactivemedia.v3.internal.gu.aR
            if (r3 != r4) goto L_0x020f
            com.google.ads.interactivemedia.v3.internal.ut r2 = r2.be
            com.google.ads.interactivemedia.v3.internal.gc[] r3 = r0.H
            if (r3 == 0) goto L_0x020f
            int r3 = r3.length
            if (r3 != 0) goto L_0x018b
            goto L_0x020f
        L_0x018b:
            r3 = 12
            r2.c(r3)
            int r12 = r2.b()
            r2.r()
            r2.r()
            long r8 = r2.j()
            long r4 = r2.j()
            r6 = 1000000(0xf4240, double:4.940656E-318)
            long r4 = com.google.ads.interactivemedia.v3.internal.vf.c(r4, r6, r8)
            int r6 = r2.d()
            byte[] r7 = r2.a
            int r8 = r6 + -4
            r7[r8] = r11
            byte[] r7 = r2.a
            int r8 = r6 + -3
            r7[r8] = r11
            byte[] r7 = r2.a
            int r8 = r6 + -2
            r7[r8] = r11
            byte[] r7 = r2.a
            r8 = 1
            int r6 = r6 - r8
            r7[r6] = r11
            com.google.ads.interactivemedia.v3.internal.gc[] r6 = r0.H
            int r7 = r6.length
            r8 = 0
        L_0x01c9:
            if (r8 >= r7) goto L_0x01d6
            r9 = r6[r8]
            r2.c(r3)
            r9.a(r2, r12)
            int r8 = r8 + 1
            goto L_0x01c9
        L_0x01d6:
            long r2 = r0.A
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01fc
            long r2 = r2 + r4
            com.google.ads.interactivemedia.v3.internal.ve r4 = r0.l
            if (r4 == 0) goto L_0x01ea
            long r2 = r4.c(r2)
        L_0x01ea:
            com.google.ads.interactivemedia.v3.internal.gc[] r13 = r0.H
            int r14 = r13.length
        L_0x01ed:
            if (r11 >= r14) goto L_0x020f
            r4 = r13[r11]
            r7 = 1
            r9 = 0
            r10 = 0
            r5 = r2
            r8 = r12
            r4.a(r5, r7, r8, r9, r10)
            int r11 = r11 + 1
            goto L_0x01ed
        L_0x01fc:
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.hh> r2 = r0.p
            com.google.ads.interactivemedia.v3.internal.hh r3 = new com.google.ads.interactivemedia.v3.internal.hh
            r3.<init>(r4, r12)
            r2.addLast(r3)
            int r2 = r0.x
            int r2 = r2 + r12
            r0.x = r2
            goto L_0x020f
        L_0x020c:
            r1.b(r3)
        L_0x020f:
            long r2 = r27.c()
            r0.a(r2)
            goto L_0x0004
        L_0x0218:
            int r2 = r0.u
            if (r2 != 0) goto L_0x0240
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.m
            byte[] r2 = r2.a
            r3 = 1
            boolean r2 = r1.a(r2, r11, r9, r3)
            if (r2 != 0) goto L_0x0229
            goto L_0x03d0
        L_0x0229:
            r0.u = r9
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.m
            r2.c(r11)
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.m
            long r2 = r2.j()
            r0.t = r2
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.m
            int r2 = r2.l()
            r0.s = r2
        L_0x0240:
            long r2 = r0.t
            r6 = 1
            int r4 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x025d
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.m
            byte[] r2 = r2.a
            r1.b(r2, r9, r9)
            int r2 = r0.u
            int r2 = r2 + r9
            r0.u = r2
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.m
            long r2 = r2.q()
            r0.t = r2
            goto L_0x028e
        L_0x025d:
            r6 = 0
            int r4 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x028e
            long r2 = r27.d()
            r6 = -1
            int r4 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x027f
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r4 = r0.o
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x027f
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r2 = r0.o
            java.lang.Object r2 = r2.peek()
            com.google.ads.interactivemedia.v3.internal.gv r2 = (com.google.ads.interactivemedia.v3.internal.gv) r2
            long r2 = r2.be
        L_0x027f:
            int r4 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x028e
            long r6 = r27.c()
            long r2 = r2 - r6
            int r4 = r0.u
            long r6 = (long) r4
            long r2 = r2 + r6
            r0.t = r2
        L_0x028e:
            long r2 = r0.t
            int r4 = r0.u
            long r6 = (long) r4
            int r4 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r4 < 0) goto L_0x03dc
            long r2 = r27.c()
            int r4 = r0.u
            long r6 = (long) r4
            long r2 = r2 - r6
            int r4 = r0.s
            int r6 = com.google.ads.interactivemedia.v3.internal.gu.V
            if (r4 != r6) goto L_0x02bf
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r4 = r0.h
            int r4 = r4.size()
            r6 = 0
        L_0x02ac:
            if (r6 >= r4) goto L_0x02bf
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r7 = r0.h
            java.lang.Object r7 = r7.valueAt(r6)
            com.google.ads.interactivemedia.v3.internal.hi r7 = (com.google.ads.interactivemedia.v3.internal.hi) r7
            com.google.ads.interactivemedia.v3.internal.ht r7 = r7.b
            r7.c = r2
            r7.b = r2
            int r6 = r6 + 1
            goto L_0x02ac
        L_0x02bf:
            int r4 = r0.s
            int r6 = com.google.ads.interactivemedia.v3.internal.gu.u
            if (r4 != r6) goto L_0x02e3
            r0.B = r8
            long r6 = r0.t
            long r6 = r6 + r2
            r0.w = r6
            boolean r4 = r0.J
            if (r4 != 0) goto L_0x02df
            com.google.ads.interactivemedia.v3.internal.fs r4 = r0.G
            com.google.ads.interactivemedia.v3.internal.ga r6 = new com.google.ads.interactivemedia.v3.internal.ga
            long r7 = r0.z
            r6.<init>(r7, r2)
            r4.a(r6)
            r2 = 1
            r0.J = r2
        L_0x02df:
            r0.r = r5
            goto L_0x03cf
        L_0x02e3:
            int r2 = r0.s
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.O
            if (r2 == r3) goto L_0x030c
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.Q
            if (r2 == r3) goto L_0x030c
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.R
            if (r2 == r3) goto L_0x030c
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.S
            if (r2 == r3) goto L_0x030c
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.T
            if (r2 == r3) goto L_0x030c
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.V
            if (r2 == r3) goto L_0x030c
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.W
            if (r2 == r3) goto L_0x030c
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.X
            if (r2 == r3) goto L_0x030c
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.aa
            if (r2 != r3) goto L_0x030a
            goto L_0x030c
        L_0x030a:
            r2 = 0
            goto L_0x030d
        L_0x030c:
            r2 = 1
        L_0x030d:
            if (r2 == 0) goto L_0x0338
            long r2 = r27.c()
            long r4 = r0.t
            long r2 = r2 + r4
            r4 = 8
            long r2 = r2 - r4
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r4 = r0.o
            com.google.ads.interactivemedia.v3.internal.gv r5 = new com.google.ads.interactivemedia.v3.internal.gv
            int r6 = r0.s
            r5.<init>(r6, r2)
            r4.push(r5)
            long r4 = r0.t
            int r6 = r0.u
            long r6 = (long) r6
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x0333
            r0.a(r2)
            goto L_0x03cf
        L_0x0333:
            r26.a()
            goto L_0x03cf
        L_0x0338:
            int r2 = r0.s
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.ad
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.ac
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.P
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.N
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.ae
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.J
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.K
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.Z
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.L
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.M
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.af
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.an
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.ao
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.as
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.ar
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.ap
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.aq
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.ab
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.Y
            if (r2 == r3) goto L_0x038d
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.aR
            if (r2 != r3) goto L_0x038b
            goto L_0x038d
        L_0x038b:
            r2 = 0
            goto L_0x038e
        L_0x038d:
            r2 = 1
        L_0x038e:
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
            if (r2 == 0) goto L_0x03c4
            int r2 = r0.u
            if (r2 != r9) goto L_0x03bc
            long r5 = r0.t
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x03b4
            com.google.ads.interactivemedia.v3.internal.ut r2 = new com.google.ads.interactivemedia.v3.internal.ut
            int r3 = (int) r5
            r2.<init>(r3)
            r0.v = r2
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.m
            byte[] r2 = r2.a
            com.google.ads.interactivemedia.v3.internal.ut r3 = r0.v
            byte[] r3 = r3.a
            java.lang.System.arraycopy(r2, r11, r3, r11, r9)
            r2 = 1
            r0.r = r2
            goto L_0x03cf
        L_0x03b4:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "Leaf atom with length > 2147483647 (unsupported)."
            r1.<init>(r2)
            throw r1
        L_0x03bc:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "Leaf atom defines extended atom size (unsupported)."
            r1.<init>(r2)
            throw r1
        L_0x03c4:
            long r5 = r0.t
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x03d4
            r0.v = r8
            r2 = 1
            r0.r = r2
        L_0x03cf:
            r11 = 1
        L_0x03d0:
            if (r11 != 0) goto L_0x0004
            r1 = -1
            return r1
        L_0x03d4:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "Skipping atom with length > 2147483647 (unsupported)."
            r1.<init>(r2)
            throw r1
        L_0x03dc:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "Atom size less than header length (unsupported)."
            r1.<init>(r2)
            throw r1
        L_0x03e4:
            if (r3 >= r7) goto L_0x0405
            java.lang.Object r10 = r2.valueAt(r3)
            com.google.ads.interactivemedia.v3.internal.hi r10 = (com.google.ads.interactivemedia.v3.internal.hi) r10
            int r14 = r10.g
            com.google.ads.interactivemedia.v3.internal.ht r15 = r10.b
            int r15 = r15.d
            if (r14 == r15) goto L_0x0402
            com.google.ads.interactivemedia.v3.internal.ht r14 = r10.b
            long[] r14 = r14.f
            int r15 = r10.g
            r15 = r14[r15]
            int r14 = (r15 > r12 ? 1 : (r15 == r12 ? 0 : -1))
            if (r14 >= 0) goto L_0x0402
            r4 = r10
            r12 = r15
        L_0x0402:
            int r3 = r3 + 1
            goto L_0x03e4
        L_0x0405:
            if (r4 != 0) goto L_0x0421
            long r2 = r0.w
            long r4 = r27.c()
            long r2 = r2 - r4
            int r3 = (int) r2
            if (r3 < 0) goto L_0x0419
            r1.b(r3)
            r26.a()
            goto L_0x0600
        L_0x0419:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "Offset to end of mdat was negative."
            r1.<init>(r2)
            throw r1
        L_0x0421:
            com.google.ads.interactivemedia.v3.internal.ht r2 = r4.b
            long[] r2 = r2.f
            int r3 = r4.g
            r12 = r2[r3]
            long r2 = r27.c()
            long r12 = r12 - r2
            int r2 = (int) r12
            if (r2 >= 0) goto L_0x0439
            java.lang.String r2 = "FragmentedMp4Extractor"
            java.lang.String r3 = "Ignoring negative offset to sample data."
            android.util.Log.w(r2, r3)
            r2 = 0
        L_0x0439:
            r1.b(r2)
            r0.B = r4
        L_0x043e:
            com.google.ads.interactivemedia.v3.internal.hi r2 = r0.B
            com.google.ads.interactivemedia.v3.internal.ht r2 = r2.b
            int[] r2 = r2.h
            com.google.ads.interactivemedia.v3.internal.hi r3 = r0.B
            int r3 = r3.e
            r2 = r2[r3]
            r0.C = r2
            com.google.ads.interactivemedia.v3.internal.hi r2 = r0.B
            int r2 = r2.e
            com.google.ads.interactivemedia.v3.internal.hi r3 = r0.B
            int r3 = r3.h
            if (r2 >= r3) goto L_0x046f
            int r2 = r0.C
            r1.b(r2)
            com.google.ads.interactivemedia.v3.internal.hi r2 = r0.B
            r2.d()
            com.google.ads.interactivemedia.v3.internal.hi r2 = r0.B
            boolean r2 = r2.b()
            if (r2 != 0) goto L_0x046a
            r0.B = r8
        L_0x046a:
            r0.r = r6
            r11 = 1
            goto L_0x0600
        L_0x046f:
            com.google.ads.interactivemedia.v3.internal.hi r2 = r0.B
            com.google.ads.interactivemedia.v3.internal.hr r2 = r2.c
            int r2 = r2.g
            r3 = 1
            if (r2 != r3) goto L_0x0480
            int r2 = r0.C
            int r2 = r2 - r9
            r0.C = r2
            r1.b(r9)
        L_0x0480:
            com.google.ads.interactivemedia.v3.internal.hi r2 = r0.B
            int r2 = r2.c()
            r0.D = r2
            int r2 = r0.C
            int r3 = r0.D
            int r2 = r2 + r3
            r0.C = r2
            r2 = 4
            r0.r = r2
            r0.E = r11
        L_0x0494:
            com.google.ads.interactivemedia.v3.internal.hi r2 = r0.B
            com.google.ads.interactivemedia.v3.internal.ht r2 = r2.b
            com.google.ads.interactivemedia.v3.internal.hi r3 = r0.B
            com.google.ads.interactivemedia.v3.internal.hr r3 = r3.c
            com.google.ads.interactivemedia.v3.internal.hi r4 = r0.B
            com.google.ads.interactivemedia.v3.internal.gc r12 = r4.a
            com.google.ads.interactivemedia.v3.internal.hi r4 = r0.B
            int r4 = r4.e
            long r9 = r2.b(r4)
            r13 = 1000(0x3e8, double:4.94E-321)
            long r9 = r9 * r13
            com.google.ads.interactivemedia.v3.internal.ve r7 = r0.l
            if (r7 == 0) goto L_0x04b4
            long r9 = r7.c(r9)
        L_0x04b4:
            int r7 = r3.j
            if (r7 == 0) goto L_0x0581
            com.google.ads.interactivemedia.v3.internal.ut r7 = r0.j
            byte[] r7 = r7.a
            r7[r11] = r11
            r13 = 1
            r7[r13] = r11
            r7[r5] = r11
            int r5 = r3.j
            int r5 = r5 + r13
            int r13 = r3.j
            r14 = 4
            int r13 = 4 - r13
        L_0x04cb:
            int r14 = r0.D
            int r15 = r0.C
            if (r14 >= r15) goto L_0x0593
            int r14 = r0.E
            if (r14 != 0) goto L_0x0525
            r1.b(r7, r13, r5)
            com.google.ads.interactivemedia.v3.internal.ut r14 = r0.j
            r14.c(r11)
            com.google.ads.interactivemedia.v3.internal.ut r14 = r0.j
            int r14 = r14.l()
            if (r14 <= 0) goto L_0x051d
            int r14 = r14 + -1
            r0.E = r14
            com.google.ads.interactivemedia.v3.internal.ut r14 = r0.i
            r14.c(r11)
            com.google.ads.interactivemedia.v3.internal.ut r14 = r0.i
            r15 = 4
            r12.a(r14, r15)
            com.google.ads.interactivemedia.v3.internal.ut r14 = r0.j
            r6 = 1
            r12.a(r14, r6)
            com.google.ads.interactivemedia.v3.internal.gc[] r14 = r0.I
            int r14 = r14.length
            if (r14 <= 0) goto L_0x050d
            com.google.ads.interactivemedia.v3.internal.bs r14 = r3.f
            java.lang.String r14 = r14.h
            byte r6 = r7[r15]
            boolean r6 = com.google.ads.interactivemedia.v3.internal.up.a(r14, r6)
            if (r6 == 0) goto L_0x050d
            r6 = 1
            goto L_0x050e
        L_0x050d:
            r6 = 0
        L_0x050e:
            r0.F = r6
            int r6 = r0.D
            int r6 = r6 + 5
            r0.D = r6
            int r6 = r0.C
            int r6 = r6 + r13
            r0.C = r6
            r6 = 3
            goto L_0x04cb
        L_0x051d:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "Invalid NAL length"
            r1.<init>(r2)
            throw r1
        L_0x0525:
            r15 = 4
            boolean r6 = r0.F
            if (r6 == 0) goto L_0x056c
            com.google.ads.interactivemedia.v3.internal.ut r6 = r0.k
            r6.a(r14)
            com.google.ads.interactivemedia.v3.internal.ut r6 = r0.k
            byte[] r6 = r6.a
            int r14 = r0.E
            r1.b(r6, r11, r14)
            com.google.ads.interactivemedia.v3.internal.ut r6 = r0.k
            int r14 = r0.E
            r12.a(r6, r14)
            int r6 = r0.E
            com.google.ads.interactivemedia.v3.internal.ut r14 = r0.k
            byte[] r14 = r14.a
            com.google.ads.interactivemedia.v3.internal.ut r15 = r0.k
            int r15 = r15.c()
            int r14 = com.google.ads.interactivemedia.v3.internal.up.a(r14, r15)
            com.google.ads.interactivemedia.v3.internal.ut r15 = r0.k
            java.lang.String r8 = "video/hevc"
            com.google.ads.interactivemedia.v3.internal.bs r11 = r3.f
            java.lang.String r11 = r11.h
            boolean r8 = r8.equals(r11)
            r15.c(r8)
            com.google.ads.interactivemedia.v3.internal.ut r8 = r0.k
            r8.b(r14)
            com.google.ads.interactivemedia.v3.internal.ut r8 = r0.k
            com.google.ads.interactivemedia.v3.internal.gc[] r11 = r0.I
            com.google.ads.interactivemedia.v3.internal.rb.a(r9, r8, r11)
            goto L_0x0572
        L_0x056c:
            r6 = 0
            int r8 = r12.a(r1, r14, r6)
            r6 = r8
        L_0x0572:
            int r8 = r0.D
            int r8 = r8 + r6
            r0.D = r8
            int r8 = r0.E
            int r8 = r8 - r6
            r0.E = r8
            r6 = 3
            r8 = 0
            r11 = 0
            goto L_0x04cb
        L_0x0581:
            int r3 = r0.D
            int r5 = r0.C
            if (r3 >= r5) goto L_0x0593
            int r5 = r5 - r3
            r3 = 0
            int r5 = r12.a(r1, r5, r3)
            int r3 = r0.D
            int r3 = r3 + r5
            r0.D = r3
            goto L_0x0581
        L_0x0593:
            boolean[] r2 = r2.k
            boolean r2 = r2[r4]
            com.google.ads.interactivemedia.v3.internal.hi r3 = r0.B
            com.google.ads.interactivemedia.v3.internal.hs r3 = r3.e()
            if (r3 == 0) goto L_0x05a8
            r4 = 1073741824(0x40000000, float:2.0)
            r2 = r2 | r4
            com.google.ads.interactivemedia.v3.internal.gd r8 = r3.c
            r15 = r2
            r18 = r8
            goto L_0x05ab
        L_0x05a8:
            r15 = r2
            r18 = 0
        L_0x05ab:
            int r2 = r0.C
            r17 = 0
            r13 = r9
            r16 = r2
            r12.a(r13, r15, r16, r17, r18)
        L_0x05b5:
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.hh> r2 = r0.p
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x05ef
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.hh> r2 = r0.p
            java.lang.Object r2 = r2.removeFirst()
            com.google.ads.interactivemedia.v3.internal.hh r2 = (com.google.ads.interactivemedia.v3.internal.hh) r2
            int r3 = r0.x
            int r4 = r2.b
            int r3 = r3 - r4
            r0.x = r3
            long r3 = r2.a
            long r3 = r3 + r9
            com.google.ads.interactivemedia.v3.internal.ve r5 = r0.l
            if (r5 == 0) goto L_0x05d7
            long r3 = r5.c(r3)
        L_0x05d7:
            com.google.ads.interactivemedia.v3.internal.gc[] r5 = r0.H
            int r6 = r5.length
            r7 = 0
        L_0x05db:
            if (r7 >= r6) goto L_0x05b5
            r11 = r5[r7]
            r14 = 1
            int r15 = r2.b
            int r8 = r0.x
            r17 = 0
            r12 = r3
            r16 = r8
            r11.a(r12, r14, r15, r16, r17)
            int r7 = r7 + 1
            goto L_0x05db
        L_0x05ef:
            com.google.ads.interactivemedia.v3.internal.hi r2 = r0.B
            boolean r2 = r2.b()
            if (r2 != 0) goto L_0x05fc
            r2 = 0
            r0.B = r2
            r2 = 3
            goto L_0x05fd
        L_0x05fc:
            r2 = 3
        L_0x05fd:
            r0.r = r2
            r11 = 1
        L_0x0600:
            if (r11 == 0) goto L_0x0004
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.hg.a(com.google.ads.interactivemedia.v3.internal.fr, com.google.ads.interactivemedia.v3.internal.fx):int");
    }

    private final void a() {
        this.r = 0;
        this.u = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:160:0x03af  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x03b6  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x03c2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void a(long r55) throws com.google.ads.interactivemedia.v3.internal.ca {
        /*
            r54 = this;
            r0 = r54
        L_0x0002:
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r1 = r0.o
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0763
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r1 = r0.o
            java.lang.Object r1 = r1.peek()
            com.google.ads.interactivemedia.v3.internal.gv r1 = (com.google.ads.interactivemedia.v3.internal.gv) r1
            long r1 = r1.be
            int r3 = (r1 > r55 ? 1 : (r1 == r55 ? 0 : -1))
            if (r3 != 0) goto L_0x0763
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r1 = r0.o
            java.lang.Object r1 = r1.pop()
            com.google.ads.interactivemedia.v3.internal.gv r1 = (com.google.ads.interactivemedia.v3.internal.gv) r1
            int r2 = r1.bd
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.O
            r4 = 12
            r7 = 16
            r8 = 8
            r10 = 1
            if (r2 != r3) goto L_0x0182
            com.google.ads.interactivemedia.v3.internal.hr r2 = r0.e
            if (r2 != 0) goto L_0x0033
            r2 = 1
            goto L_0x0034
        L_0x0033:
            r2 = 0
        L_0x0034:
            java.lang.String r3 = "Unexpected moov box."
            com.google.ads.interactivemedia.v3.internal.qi.b(r2, r3)
            com.google.ads.interactivemedia.v3.internal.fa r2 = r0.g
            if (r2 == 0) goto L_0x003e
            goto L_0x0044
        L_0x003e:
            java.util.List<com.google.ads.interactivemedia.v3.internal.gw> r2 = r1.bf
            com.google.ads.interactivemedia.v3.internal.fa r2 = a(r2)
        L_0x0044:
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.X
            com.google.ads.interactivemedia.v3.internal.gv r3 = r1.e(r3)
            android.util.SparseArray r15 = new android.util.SparseArray
            r15.<init>()
            java.util.List<com.google.ads.interactivemedia.v3.internal.gw> r11 = r3.bf
            int r11 = r11.size()
            r5 = 0
            r18 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x005b:
            if (r5 >= r11) goto L_0x00c7
            java.util.List<com.google.ads.interactivemedia.v3.internal.gw> r6 = r3.bf
            java.lang.Object r6 = r6.get(r5)
            com.google.ads.interactivemedia.v3.internal.gw r6 = (com.google.ads.interactivemedia.v3.internal.gw) r6
            int r12 = r6.bd
            int r13 = com.google.ads.interactivemedia.v3.internal.gu.L
            if (r12 != r13) goto L_0x00a2
            com.google.ads.interactivemedia.v3.internal.ut r6 = r6.be
            r6.c(r4)
            int r12 = r6.l()
            int r13 = r6.p()
            int r13 = r13 - r10
            int r14 = r6.p()
            int r4 = r6.p()
            int r6 = r6.l()
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            com.google.ads.interactivemedia.v3.internal.hd r9 = new com.google.ads.interactivemedia.v3.internal.hd
            r9.<init>(r13, r14, r4, r6)
            android.util.Pair r4 = android.util.Pair.create(r12, r9)
            java.lang.Object r6 = r4.first
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            java.lang.Object r4 = r4.second
            com.google.ads.interactivemedia.v3.internal.hd r4 = (com.google.ads.interactivemedia.v3.internal.hd) r4
            r15.put(r6, r4)
            goto L_0x00c2
        L_0x00a2:
            int r4 = r6.bd
            int r9 = com.google.ads.interactivemedia.v3.internal.gu.Y
            if (r4 != r9) goto L_0x00c2
            com.google.ads.interactivemedia.v3.internal.ut r4 = r6.be
            r4.c(r8)
            int r6 = r4.l()
            int r6 = com.google.ads.interactivemedia.v3.internal.gu.a(r6)
            if (r6 != 0) goto L_0x00bc
            long r12 = r4.j()
            goto L_0x00c0
        L_0x00bc:
            long r12 = r4.q()
        L_0x00c0:
            r18 = r12
        L_0x00c2:
            int r5 = r5 + 1
            r4 = 12
            goto L_0x005b
        L_0x00c7:
            android.util.SparseArray r3 = new android.util.SparseArray
            r3.<init>()
            java.util.List<com.google.ads.interactivemedia.v3.internal.gv> r4 = r1.bg
            int r4 = r4.size()
            r5 = 0
        L_0x00d3:
            if (r5 >= r4) goto L_0x010b
            java.util.List<com.google.ads.interactivemedia.v3.internal.gv> r6 = r1.bg
            java.lang.Object r6 = r6.get(r5)
            r11 = r6
            com.google.ads.interactivemedia.v3.internal.gv r11 = (com.google.ads.interactivemedia.v3.internal.gv) r11
            int r6 = r11.bd
            int r8 = com.google.ads.interactivemedia.v3.internal.gu.Q
            if (r6 != r8) goto L_0x0106
            int r6 = com.google.ads.interactivemedia.v3.internal.gu.P
            com.google.ads.interactivemedia.v3.internal.gw r12 = r1.d(r6)
            int r6 = r0.d
            r6 = r6 & r7
            if (r6 == 0) goto L_0x00f2
            r16 = 1
            goto L_0x00f4
        L_0x00f2:
            r16 = 0
        L_0x00f4:
            r17 = 0
            r13 = r18
            r6 = r15
            r15 = r2
            com.google.ads.interactivemedia.v3.internal.hr r8 = com.google.ads.interactivemedia.v3.internal.gx.a(r11, r12, r13, r15, r16, r17)
            if (r8 == 0) goto L_0x0107
            int r9 = r8.a
            r3.put(r9, r8)
            goto L_0x0107
        L_0x0106:
            r6 = r15
        L_0x0107:
            int r5 = r5 + 1
            r15 = r6
            goto L_0x00d3
        L_0x010b:
            r6 = r15
            int r1 = r3.size()
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r2 = r0.h
            int r2 = r2.size()
            if (r2 != 0) goto L_0x0156
            r2 = 0
        L_0x0119:
            if (r2 >= r1) goto L_0x014b
            java.lang.Object r4 = r3.valueAt(r2)
            com.google.ads.interactivemedia.v3.internal.hr r4 = (com.google.ads.interactivemedia.v3.internal.hr) r4
            com.google.ads.interactivemedia.v3.internal.hi r5 = new com.google.ads.interactivemedia.v3.internal.hi
            com.google.ads.interactivemedia.v3.internal.fs r7 = r0.G
            int r8 = r4.b
            com.google.ads.interactivemedia.v3.internal.gc r7 = r7.a(r2, r8)
            r5.<init>(r7)
            int r7 = r4.a
            com.google.ads.interactivemedia.v3.internal.hd r7 = a(r6, r7)
            r5.a(r4, r7)
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r7 = r0.h
            int r8 = r4.a
            r7.put(r8, r5)
            long r7 = r0.z
            long r4 = r4.e
            long r4 = java.lang.Math.max(r7, r4)
            r0.z = r4
            int r2 = r2 + 1
            goto L_0x0119
        L_0x014b:
            r54.b()
            com.google.ads.interactivemedia.v3.internal.fs r1 = r0.G
            r1.a()
            r1 = r0
            goto L_0x0760
        L_0x0156:
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r2 = r0.h
            int r2 = r2.size()
            if (r2 != r1) goto L_0x015f
            goto L_0x0160
        L_0x015f:
            r10 = 0
        L_0x0160:
            com.google.ads.interactivemedia.v3.internal.qi.c(r10)
            r2 = 0
        L_0x0164:
            if (r2 >= r1) goto L_0x0002
            java.lang.Object r4 = r3.valueAt(r2)
            com.google.ads.interactivemedia.v3.internal.hr r4 = (com.google.ads.interactivemedia.v3.internal.hr) r4
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r5 = r0.h
            int r7 = r4.a
            java.lang.Object r5 = r5.get(r7)
            com.google.ads.interactivemedia.v3.internal.hi r5 = (com.google.ads.interactivemedia.v3.internal.hi) r5
            int r7 = r4.a
            com.google.ads.interactivemedia.v3.internal.hd r7 = a(r6, r7)
            r5.a(r4, r7)
            int r2 = r2 + 1
            goto L_0x0164
        L_0x0182:
            int r2 = r1.bd
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.V
            if (r2 != r3) goto L_0x0748
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r2 = r0.h
            int r3 = r0.d
            byte[] r4 = r0.n
            java.util.List<com.google.ads.interactivemedia.v3.internal.gv> r9 = r1.bg
            int r9 = r9.size()
            r11 = 0
        L_0x0195:
            if (r11 >= r9) goto L_0x06b0
            java.util.List<com.google.ads.interactivemedia.v3.internal.gv> r13 = r1.bg
            java.lang.Object r13 = r13.get(r11)
            com.google.ads.interactivemedia.v3.internal.gv r13 = (com.google.ads.interactivemedia.v3.internal.gv) r13
            int r14 = r13.bd
            int r15 = com.google.ads.interactivemedia.v3.internal.gu.W
            if (r14 != r15) goto L_0x068b
            int r14 = com.google.ads.interactivemedia.v3.internal.gu.K
            com.google.ads.interactivemedia.v3.internal.gw r14 = r13.d(r14)
            com.google.ads.interactivemedia.v3.internal.ut r14 = r14.be
            r14.c(r8)
            int r15 = r14.l()
            int r15 = com.google.ads.interactivemedia.v3.internal.gu.b(r15)
            int r12 = r14.l()
            int r5 = r2.size()
            if (r5 != r10) goto L_0x01cb
            r5 = 0
            java.lang.Object r6 = r2.valueAt(r5)
            com.google.ads.interactivemedia.v3.internal.hi r6 = (com.google.ads.interactivemedia.v3.internal.hi) r6
            r12 = r6
            goto L_0x01d2
        L_0x01cb:
            java.lang.Object r5 = r2.get(r12)
            com.google.ads.interactivemedia.v3.internal.hi r5 = (com.google.ads.interactivemedia.v3.internal.hi) r5
            r12 = r5
        L_0x01d2:
            if (r12 != 0) goto L_0x01d6
            r12 = 0
            goto L_0x0220
        L_0x01d6:
            r5 = r15 & 1
            if (r5 == 0) goto L_0x01e6
            long r5 = r14.q()
            com.google.ads.interactivemedia.v3.internal.ht r7 = r12.b
            r7.b = r5
            com.google.ads.interactivemedia.v3.internal.ht r7 = r12.b
            r7.c = r5
        L_0x01e6:
            com.google.ads.interactivemedia.v3.internal.hd r5 = r12.d
            r6 = r15 & 2
            if (r6 == 0) goto L_0x01f2
            int r6 = r14.p()
            int r6 = r6 - r10
            goto L_0x01f4
        L_0x01f2:
            int r6 = r5.a
        L_0x01f4:
            r7 = r15 & 8
            if (r7 == 0) goto L_0x01fd
            int r7 = r14.p()
            goto L_0x01ff
        L_0x01fd:
            int r7 = r5.b
        L_0x01ff:
            r20 = r15 & 16
            if (r20 == 0) goto L_0x020a
            int r20 = r14.p()
            r10 = r20
            goto L_0x020c
        L_0x020a:
            int r10 = r5.c
        L_0x020c:
            r15 = r15 & 32
            if (r15 == 0) goto L_0x0215
            int r5 = r14.p()
            goto L_0x0217
        L_0x0215:
            int r5 = r5.d
        L_0x0217:
            com.google.ads.interactivemedia.v3.internal.ht r14 = r12.b
            com.google.ads.interactivemedia.v3.internal.hd r15 = new com.google.ads.interactivemedia.v3.internal.hd
            r15.<init>(r6, r7, r10, r5)
            r14.a = r15
        L_0x0220:
            if (r12 == 0) goto L_0x0679
            com.google.ads.interactivemedia.v3.internal.ht r5 = r12.b
            long r6 = r5.r
            r12.a()
            int r10 = com.google.ads.interactivemedia.v3.internal.gu.J
            com.google.ads.interactivemedia.v3.internal.gw r10 = r13.d(r10)
            if (r10 == 0) goto L_0x0254
            r10 = r3 & 2
            if (r10 != 0) goto L_0x0254
            int r6 = com.google.ads.interactivemedia.v3.internal.gu.J
            com.google.ads.interactivemedia.v3.internal.gw r6 = r13.d(r6)
            com.google.ads.interactivemedia.v3.internal.ut r6 = r6.be
            r6.c(r8)
            int r7 = r6.l()
            int r7 = com.google.ads.interactivemedia.v3.internal.gu.a(r7)
            r10 = 1
            if (r7 != r10) goto L_0x0250
            long r6 = r6.q()
            goto L_0x0254
        L_0x0250:
            long r6 = r6.j()
        L_0x0254:
            java.util.List<com.google.ads.interactivemedia.v3.internal.gw> r10 = r13.bf
            int r14 = r10.size()
            r21 = r2
            r2 = 0
            r8 = 0
            r15 = 0
        L_0x025f:
            if (r15 >= r14) goto L_0x028d
            java.lang.Object r22 = r10.get(r15)
            r23 = r6
            r6 = r22
            com.google.ads.interactivemedia.v3.internal.gw r6 = (com.google.ads.interactivemedia.v3.internal.gw) r6
            int r7 = r6.bd
            r22 = r9
            int r9 = com.google.ads.interactivemedia.v3.internal.gu.M
            if (r7 != r9) goto L_0x0284
            com.google.ads.interactivemedia.v3.internal.ut r6 = r6.be
            r7 = 12
            r6.c(r7)
            int r6 = r6.p()
            if (r6 <= 0) goto L_0x0286
            int r2 = r2 + r6
            int r8 = r8 + 1
            goto L_0x0286
        L_0x0284:
            r7 = 12
        L_0x0286:
            int r15 = r15 + 1
            r9 = r22
            r6 = r23
            goto L_0x025f
        L_0x028d:
            r23 = r6
            r22 = r9
            r6 = 0
            r7 = 12
            r12.g = r6
            r12.f = r6
            r12.e = r6
            com.google.ads.interactivemedia.v3.internal.ht r6 = r12.b
            r6.d = r8
            r6.e = r2
            int[] r9 = r6.g
            if (r9 == 0) goto L_0x02a9
            int[] r9 = r6.g
            int r9 = r9.length
            if (r9 >= r8) goto L_0x02b1
        L_0x02a9:
            long[] r9 = new long[r8]
            r6.f = r9
            int[] r8 = new int[r8]
            r6.g = r8
        L_0x02b1:
            int[] r8 = r6.h
            if (r8 == 0) goto L_0x02ba
            int[] r8 = r6.h
            int r8 = r8.length
            if (r8 >= r2) goto L_0x02d2
        L_0x02ba:
            int r2 = r2 * 125
            int r2 = r2 / 100
            int[] r8 = new int[r2]
            r6.h = r8
            int[] r8 = new int[r2]
            r6.i = r8
            long[] r8 = new long[r2]
            r6.j = r8
            boolean[] r8 = new boolean[r2]
            r6.k = r8
            boolean[] r2 = new boolean[r2]
            r6.m = r2
        L_0x02d2:
            r2 = 0
            r6 = 0
            r8 = 0
        L_0x02d5:
            if (r2 >= r14) goto L_0x0471
            java.lang.Object r25 = r10.get(r2)
            r7 = r25
            com.google.ads.interactivemedia.v3.internal.gw r7 = (com.google.ads.interactivemedia.v3.internal.gw) r7
            int r9 = r7.bd
            int r15 = com.google.ads.interactivemedia.v3.internal.gu.M
            if (r9 != r15) goto L_0x0441
            int r9 = r6 + 1
            com.google.ads.interactivemedia.v3.internal.ut r7 = r7.be
            r15 = 8
            r7.c(r15)
            int r15 = r7.l()
            int r15 = com.google.ads.interactivemedia.v3.internal.gu.b(r15)
            r16 = r9
            com.google.ads.interactivemedia.v3.internal.hr r9 = r12.c
            r28 = r10
            com.google.ads.interactivemedia.v3.internal.ht r10 = r12.b
            r29 = r14
            com.google.ads.interactivemedia.v3.internal.hd r14 = r10.a
            r30 = r1
            int[] r1 = r10.g
            int r31 = r7.p()
            r1[r6] = r31
            long[] r1 = r10.f
            r31 = r4
            r32 = r5
            long r4 = r10.b
            r1[r6] = r4
            r1 = r15 & 1
            if (r1 == 0) goto L_0x032b
            long[] r1 = r10.f
            r4 = r1[r6]
            int r0 = r7.l()
            r33 = r11
            r34 = r12
            long r11 = (long) r0
            long r4 = r4 + r11
            r1[r6] = r4
            goto L_0x032f
        L_0x032b:
            r33 = r11
            r34 = r12
        L_0x032f:
            r0 = r15 & 4
            if (r0 == 0) goto L_0x0335
            r0 = 1
            goto L_0x0336
        L_0x0335:
            r0 = 0
        L_0x0336:
            int r1 = r14.d
            if (r0 == 0) goto L_0x033e
            int r1 = r7.p()
        L_0x033e:
            r4 = r15 & 256(0x100, float:3.59E-43)
            if (r4 == 0) goto L_0x0344
            r4 = 1
            goto L_0x0345
        L_0x0344:
            r4 = 0
        L_0x0345:
            r5 = r15 & 512(0x200, float:7.175E-43)
            if (r5 == 0) goto L_0x034b
            r5 = 1
            goto L_0x034c
        L_0x034b:
            r5 = 0
        L_0x034c:
            r11 = r15 & 1024(0x400, float:1.435E-42)
            if (r11 == 0) goto L_0x0352
            r11 = 1
            goto L_0x0353
        L_0x0352:
            r11 = 0
        L_0x0353:
            r12 = r15 & 2048(0x800, float:2.87E-42)
            if (r12 == 0) goto L_0x0359
            r12 = 1
            goto L_0x035a
        L_0x0359:
            r12 = 0
        L_0x035a:
            long[] r15 = r9.h
            if (r15 == 0) goto L_0x0383
            long[] r15 = r9.h
            int r15 = r15.length
            r35 = r1
            r1 = 1
            if (r15 != r1) goto L_0x0385
            long[] r1 = r9.h
            r15 = 0
            r36 = r1[r15]
            r26 = 0
            int r1 = (r36 > r26 ? 1 : (r36 == r26 ? 0 : -1))
            if (r1 != 0) goto L_0x0385
            long[] r1 = r9.i
            r36 = r1[r15]
            r38 = 1000(0x3e8, double:4.94E-321)
            r15 = r2
            long r1 = r9.c
            r40 = r1
            long r1 = com.google.ads.interactivemedia.v3.internal.vf.c(r36, r38, r40)
            r26 = r1
            goto L_0x0388
        L_0x0383:
            r35 = r1
        L_0x0385:
            r15 = r2
            r26 = 0
        L_0x0388:
            int[] r1 = r10.h
            int[] r2 = r10.i
            r36 = r13
            long[] r13 = r10.j
            r37 = r15
            boolean[] r15 = r10.k
            r38 = r15
            int r15 = r9.b
            r39 = r1
            r1 = 2
            if (r15 != r1) goto L_0x03a3
            r1 = r3 & 1
            if (r1 == 0) goto L_0x03a3
            r1 = 1
            goto L_0x03a4
        L_0x03a3:
            r1 = 0
        L_0x03a4:
            int[] r15 = r10.g
            r15 = r15[r6]
            int r15 = r15 + r8
            r25 = r8
            long r8 = r9.c
            if (r6 <= 0) goto L_0x03b6
            r47 = r1
            r46 = r2
            long r1 = r10.r
            goto L_0x03bc
        L_0x03b6:
            r47 = r1
            r46 = r2
            r1 = r23
        L_0x03bc:
            r48 = r1
            r1 = r25
        L_0x03c0:
            if (r1 >= r15) goto L_0x0437
            if (r4 == 0) goto L_0x03c9
            int r2 = r7.p()
            goto L_0x03cb
        L_0x03c9:
            int r2 = r14.b
        L_0x03cb:
            if (r5 == 0) goto L_0x03d2
            int r6 = r7.p()
            goto L_0x03d4
        L_0x03d2:
            int r6 = r14.c
        L_0x03d4:
            if (r1 != 0) goto L_0x03dd
            if (r0 == 0) goto L_0x03dd
            r50 = r0
            r0 = r35
            goto L_0x03ec
        L_0x03dd:
            if (r11 == 0) goto L_0x03e8
            int r25 = r7.l()
            r50 = r0
            r0 = r25
            goto L_0x03ec
        L_0x03e8:
            r50 = r0
            int r0 = r14.d
        L_0x03ec:
            if (r12 == 0) goto L_0x0400
            r51 = r3
            int r3 = r7.l()
            r52 = r4
            long r3 = (long) r3
            r40 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 * r40
            long r3 = r3 / r8
            int r4 = (int) r3
            r46[r1] = r4
            goto L_0x0407
        L_0x0400:
            r51 = r3
            r52 = r4
            r3 = 0
            r46[r1] = r3
        L_0x0407:
            r42 = 1000(0x3e8, double:4.94E-321)
            r40 = r48
            r44 = r8
            long r3 = com.google.ads.interactivemedia.v3.internal.vf.c(r40, r42, r44)
            long r3 = r3 - r26
            r13[r1] = r3
            r39[r1] = r6
            r3 = 16
            int r0 = r0 >> r3
            r3 = 1
            r0 = r0 & r3
            if (r0 != 0) goto L_0x0424
            if (r47 == 0) goto L_0x0422
            if (r1 != 0) goto L_0x0424
        L_0x0422:
            r0 = 1
            goto L_0x0425
        L_0x0424:
            r0 = 0
        L_0x0425:
            r38[r1] = r0
            long r2 = (long) r2
            r0 = r5
            r4 = r48
            long r48 = r4 + r2
            int r1 = r1 + 1
            r5 = r0
            r0 = r50
            r3 = r51
            r4 = r52
            goto L_0x03c0
        L_0x0437:
            r51 = r3
            r4 = r48
            r10.r = r4
            r8 = r15
            r6 = r16
            goto L_0x0457
        L_0x0441:
            r30 = r1
            r37 = r2
            r51 = r3
            r31 = r4
            r32 = r5
            r25 = r8
            r28 = r10
            r33 = r11
            r34 = r12
            r36 = r13
            r29 = r14
        L_0x0457:
            int r2 = r37 + 1
            r10 = r28
            r14 = r29
            r1 = r30
            r4 = r31
            r5 = r32
            r11 = r33
            r12 = r34
            r13 = r36
            r3 = r51
            r0 = r54
            r7 = 12
            goto L_0x02d5
        L_0x0471:
            r30 = r1
            r51 = r3
            r31 = r4
            r32 = r5
            r33 = r11
            r36 = r13
            com.google.ads.interactivemedia.v3.internal.hr r0 = r12.c
            r1 = r32
            com.google.ads.interactivemedia.v3.internal.hd r2 = r1.a
            int r2 = r2.a
            com.google.ads.interactivemedia.v3.internal.hs r0 = r0.a(r2)
            int r2 = com.google.ads.interactivemedia.v3.internal.gu.an
            com.google.ads.interactivemedia.v3.internal.gw r2 = r13.d(r2)
            if (r2 == 0) goto L_0x0502
            com.google.ads.interactivemedia.v3.internal.ut r2 = r2.be
            int r3 = r0.d
            r4 = 8
            r2.c(r4)
            int r5 = r2.l()
            int r5 = com.google.ads.interactivemedia.v3.internal.gu.b(r5)
            r6 = 1
            r5 = r5 & r6
            if (r5 != r6) goto L_0x04a9
            r2.d(r4)
        L_0x04a9:
            int r4 = r2.e()
            int r5 = r2.p()
            int r6 = r1.e
            if (r5 != r6) goto L_0x04df
            if (r4 != 0) goto L_0x04cc
            boolean[] r4 = r1.m
            r6 = 0
            r7 = 0
        L_0x04bb:
            if (r6 >= r5) goto L_0x04db
            int r8 = r2.e()
            int r7 = r7 + r8
            if (r8 <= r3) goto L_0x04c6
            r8 = 1
            goto L_0x04c7
        L_0x04c6:
            r8 = 0
        L_0x04c7:
            r4[r6] = r8
            int r6 = r6 + 1
            goto L_0x04bb
        L_0x04cc:
            if (r4 <= r3) goto L_0x04d0
            r2 = 1
            goto L_0x04d1
        L_0x04d0:
            r2 = 0
        L_0x04d1:
            int r4 = r4 * r5
            r3 = 0
            int r7 = r4 + 0
            boolean[] r4 = r1.m
            java.util.Arrays.fill(r4, r3, r5, r2)
        L_0x04db:
            r1.a(r7)
            goto L_0x0502
        L_0x04df:
            com.google.ads.interactivemedia.v3.internal.ca r0 = new com.google.ads.interactivemedia.v3.internal.ca
            int r1 = r1.e
            r2 = 41
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Length mismatch: "
            r3.append(r2)
            r3.append(r5)
            java.lang.String r2 = ", "
            r3.append(r2)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r0.<init>(r1)
            throw r0
        L_0x0502:
            int r2 = com.google.ads.interactivemedia.v3.internal.gu.ao
            com.google.ads.interactivemedia.v3.internal.gw r2 = r13.d(r2)
            if (r2 == 0) goto L_0x0554
            com.google.ads.interactivemedia.v3.internal.ut r2 = r2.be
            r3 = 8
            r2.c(r3)
            int r4 = r2.l()
            int r5 = com.google.ads.interactivemedia.v3.internal.gu.b(r4)
            r6 = 1
            r5 = r5 & r6
            if (r5 != r6) goto L_0x0520
            r2.d(r3)
        L_0x0520:
            int r3 = r2.p()
            if (r3 != r6) goto L_0x053b
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.a(r4)
            long r4 = r1.c
            if (r3 != 0) goto L_0x0533
            long r2 = r2.j()
            goto L_0x0537
        L_0x0533:
            long r2 = r2.q()
        L_0x0537:
            long r4 = r4 + r2
            r1.c = r4
            goto L_0x0554
        L_0x053b:
            com.google.ads.interactivemedia.v3.internal.ca r0 = new com.google.ads.interactivemedia.v3.internal.ca
            r1 = 40
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            java.lang.String r1 = "Unexpected saio entry count: "
            r2.append(r1)
            r2.append(r3)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            throw r0
        L_0x0554:
            int r2 = com.google.ads.interactivemedia.v3.internal.gu.as
            com.google.ads.interactivemedia.v3.internal.gw r2 = r13.d(r2)
            if (r2 == 0) goto L_0x0562
            com.google.ads.interactivemedia.v3.internal.ut r2 = r2.be
            r3 = 0
            a(r2, r3, r1)
        L_0x0562:
            int r2 = com.google.ads.interactivemedia.v3.internal.gu.ap
            com.google.ads.interactivemedia.v3.internal.gw r2 = r13.d(r2)
            int r3 = com.google.ads.interactivemedia.v3.internal.gu.aq
            com.google.ads.interactivemedia.v3.internal.gw r3 = r13.d(r3)
            if (r2 == 0) goto L_0x0632
            if (r3 == 0) goto L_0x0632
            com.google.ads.interactivemedia.v3.internal.ut r2 = r2.be
            com.google.ads.interactivemedia.v3.internal.ut r3 = r3.be
            if (r0 == 0) goto L_0x057e
            java.lang.String r12 = r0.b
            r6 = r12
            r0 = 8
            goto L_0x0581
        L_0x057e:
            r0 = 8
            r6 = 0
        L_0x0581:
            r2.c(r0)
            int r0 = r2.l()
            int r4 = r2.l()
            int r5 = a
            if (r4 != r5) goto L_0x0630
            int r0 = com.google.ads.interactivemedia.v3.internal.gu.a(r0)
            r4 = 4
            r5 = 1
            if (r0 != r5) goto L_0x059b
            r2.d(r4)
        L_0x059b:
            int r0 = r2.l()
            if (r0 != r5) goto L_0x0628
            r0 = 8
            r3.c(r0)
            int r0 = r3.l()
            int r2 = r3.l()
            int r7 = a
            if (r2 != r7) goto L_0x0626
            int r0 = com.google.ads.interactivemedia.v3.internal.gu.a(r0)
            if (r0 != r5) goto L_0x05cb
            long r7 = r3.j()
            r9 = 0
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x05c3
            goto L_0x05d1
        L_0x05c3:
            com.google.ads.interactivemedia.v3.internal.ca r0 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r1 = "Variable length description in sgpd found (unsupported)"
            r0.<init>(r1)
            throw r0
        L_0x05cb:
            r2 = 2
            if (r0 < r2) goto L_0x05d1
            r3.d(r4)
        L_0x05d1:
            long r7 = r3.j()
            r9 = 1
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 != 0) goto L_0x061e
            r0 = 1
            r3.d(r0)
            int r2 = r3.e()
            r5 = r2 & 240(0xf0, float:3.36E-43)
            int r9 = r5 >> 4
            r10 = r2 & 15
            int r2 = r3.e()
            if (r2 != r0) goto L_0x05f1
            r0 = 1
            goto L_0x05f2
        L_0x05f1:
            r0 = 0
        L_0x05f2:
            if (r0 == 0) goto L_0x061c
            int r7 = r3.e()
            r0 = 16
            byte[] r8 = new byte[r0]
            r2 = 0
            r3.a(r8, r2, r0)
            if (r7 != 0) goto L_0x060e
            int r0 = r3.e()
            byte[] r12 = new byte[r0]
            r3.a(r12, r2, r0)
            r11 = r12
            r0 = 1
            goto L_0x0610
        L_0x060e:
            r0 = 1
            r11 = 0
        L_0x0610:
            r1.l = r0
            com.google.ads.interactivemedia.v3.internal.hs r2 = new com.google.ads.interactivemedia.v3.internal.hs
            r5 = 1
            r4 = r2
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)
            r1.n = r2
            goto L_0x0633
        L_0x061c:
            r0 = 1
            goto L_0x0633
        L_0x061e:
            com.google.ads.interactivemedia.v3.internal.ca r0 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r1 = "Entry count in sgpd != 1 (unsupported)."
            r0.<init>(r1)
            throw r0
        L_0x0626:
            r0 = 1
            goto L_0x0633
        L_0x0628:
            com.google.ads.interactivemedia.v3.internal.ca r0 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r1 = "Entry count in sbgp != 1 (unsupported)."
            r0.<init>(r1)
            throw r0
        L_0x0630:
            r0 = 1
            goto L_0x0633
        L_0x0632:
            r0 = 1
        L_0x0633:
            java.util.List<com.google.ads.interactivemedia.v3.internal.gw> r2 = r13.bf
            int r2 = r2.size()
            r3 = 0
        L_0x063a:
            if (r3 >= r2) goto L_0x0671
            java.util.List<com.google.ads.interactivemedia.v3.internal.gw> r4 = r13.bf
            java.lang.Object r4 = r4.get(r3)
            com.google.ads.interactivemedia.v3.internal.gw r4 = (com.google.ads.interactivemedia.v3.internal.gw) r4
            int r5 = r4.bd
            int r6 = com.google.ads.interactivemedia.v3.internal.gu.ar
            if (r5 != r6) goto L_0x0665
            com.google.ads.interactivemedia.v3.internal.ut r4 = r4.be
            r5 = 8
            r4.c(r5)
            r6 = r31
            r7 = 0
            r8 = 16
            r4.a(r6, r7, r8)
            byte[] r9 = b
            boolean r9 = java.util.Arrays.equals(r6, r9)
            if (r9 == 0) goto L_0x066c
            a(r4, r8, r1)
            goto L_0x066c
        L_0x0665:
            r6 = r31
            r5 = 8
            r7 = 0
            r8 = 16
        L_0x066c:
            int r3 = r3 + 1
            r31 = r6
            goto L_0x063a
        L_0x0671:
            r6 = r31
            r5 = 8
            r7 = 0
            r8 = 16
            goto L_0x069c
        L_0x0679:
            r30 = r1
            r21 = r2
            r51 = r3
            r6 = r4
            r22 = r9
            r33 = r11
            r0 = 1
            r5 = 8
            r7 = 0
            r8 = 16
            goto L_0x069c
        L_0x068b:
            r30 = r1
            r21 = r2
            r51 = r3
            r6 = r4
            r22 = r9
            r33 = r11
            r0 = 1
            r5 = 8
            r7 = 0
            r8 = 16
        L_0x069c:
            int r11 = r33 + 1
            r4 = r6
            r2 = r21
            r9 = r22
            r1 = r30
            r3 = r51
            r0 = r54
            r7 = 16
            r8 = 8
            r10 = 1
            goto L_0x0195
        L_0x06b0:
            r30 = r1
            r7 = 0
            r1 = r0
            com.google.ads.interactivemedia.v3.internal.fa r0 = r1.g
            if (r0 == 0) goto L_0x06ba
            r12 = 0
            goto L_0x06c2
        L_0x06ba:
            r0 = r30
            java.util.List<com.google.ads.interactivemedia.v3.internal.gw> r0 = r0.bf
            com.google.ads.interactivemedia.v3.internal.fa r12 = a(r0)
        L_0x06c2:
            if (r12 == 0) goto L_0x06fb
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r0 = r1.h
            int r0 = r0.size()
            r2 = 0
        L_0x06cb:
            if (r2 >= r0) goto L_0x06fb
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r3 = r1.h
            java.lang.Object r3 = r3.valueAt(r2)
            com.google.ads.interactivemedia.v3.internal.hi r3 = (com.google.ads.interactivemedia.v3.internal.hi) r3
            com.google.ads.interactivemedia.v3.internal.hr r4 = r3.c
            com.google.ads.interactivemedia.v3.internal.ht r5 = r3.b
            com.google.ads.interactivemedia.v3.internal.hd r5 = r5.a
            int r5 = r5.a
            com.google.ads.interactivemedia.v3.internal.hs r4 = r4.a(r5)
            if (r4 == 0) goto L_0x06e6
            java.lang.String r4 = r4.b
            goto L_0x06e7
        L_0x06e6:
            r4 = 0
        L_0x06e7:
            com.google.ads.interactivemedia.v3.internal.gc r5 = r3.a
            com.google.ads.interactivemedia.v3.internal.hr r3 = r3.c
            com.google.ads.interactivemedia.v3.internal.bs r3 = r3.f
            com.google.ads.interactivemedia.v3.internal.fa r4 = r12.a(r4)
            com.google.ads.interactivemedia.v3.internal.bs r3 = r3.a(r4)
            r5.a(r3)
            int r2 = r2 + 1
            goto L_0x06cb
        L_0x06fb:
            long r2 = r1.y
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0745
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r0 = r1.h
            int r0 = r0.size()
        L_0x070c:
            if (r7 >= r0) goto L_0x073e
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.hi> r2 = r1.h
            java.lang.Object r2 = r2.valueAt(r7)
            com.google.ads.interactivemedia.v3.internal.hi r2 = (com.google.ads.interactivemedia.v3.internal.hi) r2
            long r3 = r1.y
            long r3 = com.google.ads.interactivemedia.v3.internal.at.a(r3)
            int r5 = r2.e
        L_0x071e:
            com.google.ads.interactivemedia.v3.internal.ht r6 = r2.b
            int r6 = r6.e
            if (r5 >= r6) goto L_0x073b
            com.google.ads.interactivemedia.v3.internal.ht r6 = r2.b
            long r8 = r6.b(r5)
            int r6 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r6 >= 0) goto L_0x073b
            com.google.ads.interactivemedia.v3.internal.ht r6 = r2.b
            boolean[] r6 = r6.k
            boolean r6 = r6[r5]
            if (r6 == 0) goto L_0x0738
            r2.h = r5
        L_0x0738:
            int r5 = r5 + 1
            goto L_0x071e
        L_0x073b:
            int r7 = r7 + 1
            goto L_0x070c
        L_0x073e:
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r1.y = r2
        L_0x0745:
            r0 = r1
            goto L_0x0002
        L_0x0748:
            r53 = r1
            r1 = r0
            r0 = r53
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r2 = r1.o
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x0760
            java.util.ArrayDeque<com.google.ads.interactivemedia.v3.internal.gv> r2 = r1.o
            java.lang.Object r2 = r2.peek()
            com.google.ads.interactivemedia.v3.internal.gv r2 = (com.google.ads.interactivemedia.v3.internal.gv) r2
            r2.a(r0)
        L_0x0760:
            r0 = r1
            goto L_0x0002
        L_0x0763:
            r1 = r0
            r54.a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.hg.a(long):void");
    }

    private static hd a(SparseArray<hd> sparseArray, int i2) {
        if (sparseArray.size() == 1) {
            return (hd) sparseArray.valueAt(0);
        }
        return (hd) qi.a((hd) sparseArray.get(i2));
    }

    private final void b() {
        int i2;
        if (this.H == null) {
            this.H = new gc[2];
            gc gcVar = this.q;
            if (gcVar != null) {
                this.H[0] = gcVar;
                i2 = 1;
            } else {
                i2 = 0;
            }
            if ((this.d & 4) != 0) {
                int i3 = i2 + 1;
                this.H[i2] = this.G.a(this.h.size(), 4);
                i2 = i3;
            }
            this.H = (gc[]) Arrays.copyOf(this.H, i2);
            for (gc a2 : this.H) {
                a2.a(c);
            }
        }
        if (this.I == null) {
            this.I = new gc[this.f.size()];
            for (int i4 = 0; i4 < this.I.length; i4++) {
                gc a3 = this.G.a(this.h.size() + 1 + i4, 3);
                a3.a((bs) this.f.get(i4));
                this.I[i4] = a3;
            }
        }
    }

    private static void a(ut utVar, int i2, ht htVar) throws ca {
        utVar.c(i2 + 8);
        int b2 = gu.b(utVar.l());
        if ((b2 & 1) == 0) {
            boolean z2 = (b2 & 2) != 0;
            int p2 = utVar.p();
            if (p2 == htVar.e) {
                Arrays.fill(htVar.m, 0, p2, z2);
                htVar.a(utVar.b());
                utVar.a(htVar.p.a, 0, htVar.o);
                htVar.p.c(0);
                htVar.q = false;
                return;
            }
            int i3 = htVar.e;
            StringBuilder sb = new StringBuilder(41);
            sb.append("Length mismatch: ");
            sb.append(p2);
            sb.append(", ");
            sb.append(i3);
            throw new ca(sb.toString());
        }
        throw new ca("Overriding TrackEncryptionBox parameters is unsupported.");
    }

    private static fa a(List<gw> list) {
        int size = list.size();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < size; i2++) {
            gw gwVar = (gw) list.get(i2);
            if (gwVar.bd == gu.af) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                byte[] bArr = gwVar.be.a;
                UUID a2 = ho.a(bArr);
                if (a2 == null) {
                    Log.w("FragmentedMp4Extractor", "Skipped pssh atom (failed to extract uuid)");
                } else {
                    arrayList.add(new a(a2, MimeTypes.VIDEO_MP4, bArr));
                }
            }
        }
        if (arrayList == null) {
            return null;
        }
        return new fa((List<a>) arrayList);
    }
}

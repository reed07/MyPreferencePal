package com.google.ads.interactivemedia.v3.internal;

import com.brightcove.player.C;
import java.io.EOFException;
import java.io.IOException;

/* compiled from: IMASDK */
public final class gp implements fq {
    private static final kn a = gq.a;
    private static final int b = vf.h("Xing");
    private static final int c = vf.h("Info");
    private static final int d = vf.h("VBRI");
    private final int e;
    private final long f;
    private final ut g;
    private final fw h;
    private final fu i;
    private final fv j;
    private fs k;
    private gc l;
    private int m;
    private js n;
    private gr o;
    private long p;
    private long q;
    private int r;

    public gp() {
        this(0);
    }

    static final /* synthetic */ boolean a(int i2, int i3, int i4, int i5, int i6) {
        return (i3 == 67 && i4 == 79 && i5 == 77 && (i6 == 77 || i2 == 2)) || (i3 == 77 && i4 == 76 && i5 == 76 && (i6 == 84 || i2 == 2));
    }

    private static boolean a(int i2, long j2) {
        return ((long) (i2 & -128000)) == (j2 & -128000);
    }

    public final void c() {
    }

    private gp(int i2) {
        this(0, -9223372036854775807L);
    }

    public gp(int i2, long j2) {
        this.e = i2;
        this.f = j2;
        this.g = new ut(10);
        this.h = new fw();
        this.i = new fu();
        this.p = -9223372036854775807L;
        this.j = new fv();
    }

    public final boolean a(fr frVar) throws IOException, InterruptedException {
        return a(frVar, true);
    }

    public final void a(fs fsVar) {
        this.k = fsVar;
        this.l = this.k.a(0, 1);
        this.k.a();
    }

    public final void a(long j2, long j3) {
        this.m = 0;
        this.p = -9223372036854775807L;
        this.q = 0;
        this.r = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x016f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(com.google.ads.interactivemedia.v3.internal.fr r30, com.google.ads.interactivemedia.v3.internal.fx r31) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            int r2 = r0.m
            r3 = -1
            r4 = 0
            if (r2 != 0) goto L_0x000f
            r0.a(r1, r4)     // Catch:{ EOFException -> 0x000e }
            goto L_0x000f
        L_0x000e:
            return r3
        L_0x000f:
            com.google.ads.interactivemedia.v3.internal.gr r2 = r0.o
            r5 = 1
            if (r2 != 0) goto L_0x0182
            com.google.ads.interactivemedia.v3.internal.ut r11 = new com.google.ads.interactivemedia.v3.internal.ut
            com.google.ads.interactivemedia.v3.internal.fw r2 = r0.h
            int r2 = r2.c
            r11.<init>(r2)
            byte[] r2 = r11.a
            com.google.ads.interactivemedia.v3.internal.fw r6 = r0.h
            int r6 = r6.c
            r1.c(r2, r4, r6)
            com.google.ads.interactivemedia.v3.internal.fw r2 = r0.h
            int r2 = r2.a
            r2 = r2 & r5
            r6 = 36
            r7 = 21
            if (r2 == 0) goto L_0x003d
            com.google.ads.interactivemedia.v3.internal.fw r2 = r0.h
            int r2 = r2.e
            if (r2 == r5) goto L_0x003a
            r2 = 36
            goto L_0x0048
        L_0x003a:
            r2 = 21
            goto L_0x0048
        L_0x003d:
            com.google.ads.interactivemedia.v3.internal.fw r2 = r0.h
            int r2 = r2.e
            if (r2 == r5) goto L_0x0046
            r2 = 21
            goto L_0x0048
        L_0x0046:
            r2 = 13
        L_0x0048:
            int r7 = r11.c()
            int r8 = r2 + 4
            if (r7 < r8) goto L_0x0061
            r11.c(r2)
            int r7 = r11.l()
            int r8 = b
            if (r7 == r8) goto L_0x005f
            int r8 = c
            if (r7 != r8) goto L_0x0061
        L_0x005f:
            r12 = r7
            goto L_0x0077
        L_0x0061:
            int r7 = r11.c()
            r8 = 40
            if (r7 < r8) goto L_0x0076
            r11.c(r6)
            int r6 = r11.l()
            int r7 = d
            if (r6 != r7) goto L_0x0076
            r12 = r7
            goto L_0x0077
        L_0x0076:
            r12 = 0
        L_0x0077:
            int r6 = b
            r13 = 0
            if (r12 == r6) goto L_0x00a0
            int r6 = c
            if (r12 != r6) goto L_0x0081
            goto L_0x00a0
        L_0x0081:
            int r2 = d
            if (r12 != r2) goto L_0x009b
            long r6 = r30.d()
            long r8 = r30.c()
            com.google.ads.interactivemedia.v3.internal.fw r10 = r0.h
            com.google.ads.interactivemedia.v3.internal.gs r2 = com.google.ads.interactivemedia.v3.internal.gs.a(r6, r8, r10, r11)
            com.google.ads.interactivemedia.v3.internal.fw r6 = r0.h
            int r6 = r6.c
            r1.b(r6)
            goto L_0x00fa
        L_0x009b:
            r30.a()
            r2 = r13
            goto L_0x00fa
        L_0x00a0:
            long r6 = r30.d()
            long r8 = r30.c()
            com.google.ads.interactivemedia.v3.internal.fw r10 = r0.h
            com.google.ads.interactivemedia.v3.internal.gt r6 = com.google.ads.interactivemedia.v3.internal.gt.a(r6, r8, r10, r11)
            if (r6 == 0) goto L_0x00e1
            com.google.ads.interactivemedia.v3.internal.fu r7 = r0.i
            boolean r7 = r7.a()
            if (r7 != 0) goto L_0x00e1
            r30.a()
            int r2 = r2 + 141
            r1.c(r2)
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.g
            byte[] r2 = r2.a
            r7 = 3
            r1.c(r2, r4, r7)
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.g
            r2.c(r4)
            com.google.ads.interactivemedia.v3.internal.fu r2 = r0.i
            com.google.ads.interactivemedia.v3.internal.ut r7 = r0.g
            int r7 = r7.i()
            int r8 = r7 >> 12
            r7 = r7 & 4095(0xfff, float:5.738E-42)
            if (r8 > 0) goto L_0x00dd
            if (r7 <= 0) goto L_0x00e1
        L_0x00dd:
            r2.a = r8
            r2.b = r7
        L_0x00e1:
            com.google.ads.interactivemedia.v3.internal.fw r2 = r0.h
            int r2 = r2.c
            r1.b(r2)
            if (r6 == 0) goto L_0x00f9
            boolean r2 = r6.a()
            if (r2 != 0) goto L_0x00f9
            int r2 = c
            if (r12 != r2) goto L_0x00f9
            com.google.ads.interactivemedia.v3.internal.gr r2 = r29.c(r30)
            goto L_0x00fa
        L_0x00f9:
            r2 = r6
        L_0x00fa:
            com.google.ads.interactivemedia.v3.internal.js r6 = r0.n
            long r7 = r30.c()
            if (r6 == 0) goto L_0x011b
            int r9 = r6.a()
            r10 = 0
        L_0x0107:
            if (r10 >= r9) goto L_0x011b
            com.google.ads.interactivemedia.v3.internal.js$a r11 = r6.a(r10)
            boolean r12 = r11 instanceof com.google.ads.interactivemedia.v3.internal.ks
            if (r12 == 0) goto L_0x0118
            com.google.ads.interactivemedia.v3.internal.ks r11 = (com.google.ads.interactivemedia.v3.internal.ks) r11
            com.google.ads.interactivemedia.v3.internal.go r6 = com.google.ads.interactivemedia.v3.internal.go.a(r7, r11)
            goto L_0x011c
        L_0x0118:
            int r10 = r10 + 1
            goto L_0x0107
        L_0x011b:
            r6 = r13
        L_0x011c:
            if (r6 == 0) goto L_0x0121
            r0.o = r6
            goto L_0x0125
        L_0x0121:
            if (r2 == 0) goto L_0x0125
            r0.o = r2
        L_0x0125:
            com.google.ads.interactivemedia.v3.internal.gr r2 = r0.o
            if (r2 == 0) goto L_0x0134
            boolean r2 = r2.a()
            if (r2 != 0) goto L_0x013a
            int r2 = r0.e
            r2 = r2 & r5
            if (r2 == 0) goto L_0x013a
        L_0x0134:
            com.google.ads.interactivemedia.v3.internal.gr r2 = r29.c(r30)
            r0.o = r2
        L_0x013a:
            com.google.ads.interactivemedia.v3.internal.fs r2 = r0.k
            com.google.ads.interactivemedia.v3.internal.gr r6 = r0.o
            r2.a(r6)
            com.google.ads.interactivemedia.v3.internal.gc r2 = r0.l
            r14 = 0
            com.google.ads.interactivemedia.v3.internal.fw r6 = r0.h
            java.lang.String r15 = r6.b
            r16 = 0
            r17 = -1
            r18 = 4096(0x1000, float:5.74E-42)
            com.google.ads.interactivemedia.v3.internal.fw r6 = r0.h
            int r6 = r6.e
            com.google.ads.interactivemedia.v3.internal.fw r7 = r0.h
            int r7 = r7.d
            r21 = -1
            com.google.ads.interactivemedia.v3.internal.fu r8 = r0.i
            int r8 = r8.a
            com.google.ads.interactivemedia.v3.internal.fu r9 = r0.i
            int r9 = r9.b
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            int r10 = r0.e
            r10 = r10 & 2
            if (r10 == 0) goto L_0x016f
            goto L_0x0171
        L_0x016f:
            com.google.ads.interactivemedia.v3.internal.js r13 = r0.n
        L_0x0171:
            r28 = r13
            r19 = r6
            r20 = r7
            r22 = r8
            r23 = r9
            com.google.ads.interactivemedia.v3.internal.bs r6 = com.google.ads.interactivemedia.v3.internal.bs.a(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
            r2.a(r6)
        L_0x0182:
            int r2 = r0.r
            if (r2 != 0) goto L_0x01ea
            r30.a()
            boolean r2 = r29.b(r30)
            if (r2 == 0) goto L_0x0190
            return r3
        L_0x0190:
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.g
            r2.c(r4)
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.g
            int r2 = r2.l()
            int r6 = r0.m
            long r6 = (long) r6
            boolean r6 = a(r2, r6)
            if (r6 == 0) goto L_0x01e4
            int r6 = com.google.ads.interactivemedia.v3.internal.fw.a(r2)
            if (r6 != r3) goto L_0x01ab
            goto L_0x01e4
        L_0x01ab:
            com.google.ads.interactivemedia.v3.internal.fw r6 = r0.h
            com.google.ads.interactivemedia.v3.internal.fw.a(r2, r6)
            long r6 = r0.p
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x01dd
            com.google.ads.interactivemedia.v3.internal.gr r2 = r0.o
            long r6 = r30.c()
            long r6 = r2.c(r6)
            r0.p = r6
            long r6 = r0.f
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 == 0) goto L_0x01dd
            com.google.ads.interactivemedia.v3.internal.gr r2 = r0.o
            r6 = 0
            long r6 = r2.c(r6)
            long r8 = r0.p
            long r10 = r0.f
            long r10 = r10 - r6
            long r8 = r8 + r10
            r0.p = r8
        L_0x01dd:
            com.google.ads.interactivemedia.v3.internal.fw r2 = r0.h
            int r2 = r2.c
            r0.r = r2
            goto L_0x01ea
        L_0x01e4:
            r1.b(r5)
            r0.m = r4
            return r4
        L_0x01ea:
            com.google.ads.interactivemedia.v3.internal.gc r2 = r0.l
            int r6 = r0.r
            int r1 = r2.a(r1, r6, r5)
            if (r1 != r3) goto L_0x01f5
            return r3
        L_0x01f5:
            int r2 = r0.r
            int r2 = r2 - r1
            r0.r = r2
            int r1 = r0.r
            if (r1 <= 0) goto L_0x01ff
            return r4
        L_0x01ff:
            long r1 = r0.p
            long r5 = r0.q
            r7 = 1000000(0xf4240, double:4.940656E-318)
            long r5 = r5 * r7
            com.google.ads.interactivemedia.v3.internal.fw r3 = r0.h
            int r3 = r3.d
            long r7 = (long) r3
            long r5 = r5 / r7
            long r8 = r1 + r5
            com.google.ads.interactivemedia.v3.internal.gc r7 = r0.l
            r10 = 1
            com.google.ads.interactivemedia.v3.internal.fw r1 = r0.h
            int r11 = r1.c
            r12 = 0
            r13 = 0
            r7.a(r8, r10, r11, r12, r13)
            long r1 = r0.q
            com.google.ads.interactivemedia.v3.internal.fw r3 = r0.h
            int r3 = r3.g
            long r5 = (long) r3
            long r1 = r1 + r5
            r0.q = r1
            r0.r = r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.gp.a(com.google.ads.interactivemedia.v3.internal.fr, com.google.ads.interactivemedia.v3.internal.fx):int");
    }

    private final boolean a(fr frVar, boolean z) throws IOException, InterruptedException {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        kn knVar;
        int i7 = z ? C.DASH_ROLE_CAPTION_FLAG : 131072;
        frVar.a();
        if (frVar.c() == 0) {
            if ((this.e & 2) == 0) {
                knVar = null;
            } else {
                knVar = a;
            }
            this.n = this.j.a(frVar, knVar);
            js jsVar = this.n;
            if (jsVar != null) {
                this.i.a(jsVar);
            }
            int b2 = (int) frVar.b();
            if (!z) {
                frVar.b(b2);
            }
            i2 = b2;
            i5 = 0;
            i4 = 0;
            i3 = 0;
        } else {
            i5 = 0;
            i4 = 0;
            i3 = 0;
            i2 = 0;
        }
        while (true) {
            if (!b(frVar)) {
                this.g.c(0);
                int l2 = this.g.l();
                if (i5 == 0 || a(l2, (long) i5)) {
                    int a2 = fw.a(l2);
                    if (a2 != -1) {
                        i6 = i4 + 1;
                        if (i6 != 1) {
                            if (i6 == 4) {
                                break;
                            }
                        } else {
                            fw.a(l2, this.h);
                            i5 = l2;
                        }
                        frVar.c(a2 - 4);
                    }
                }
                int i8 = i3 + 1;
                if (i3 != i7) {
                    if (z) {
                        frVar.a();
                        frVar.c(i2 + i8);
                    } else {
                        frVar.b(1);
                    }
                    i3 = i8;
                    i5 = 0;
                    i6 = 0;
                } else if (z) {
                    return false;
                } else {
                    throw new ca("Searched too many bytes.");
                }
            } else if (i4 <= 0) {
                throw new EOFException();
            }
        }
        if (z) {
            frVar.b(i2 + i3);
        } else {
            frVar.a();
        }
        this.m = i5;
        return true;
    }

    private final boolean b(fr frVar) throws IOException, InterruptedException {
        return (this.o != null && frVar.b() == this.o.c()) || !frVar.b(this.g.a, 0, 4, true);
    }

    private final gr c(fr frVar) throws IOException, InterruptedException {
        frVar.c(this.g.a, 0, 4);
        this.g.c(0);
        fw.a(this.g.l(), this.h);
        gn gnVar = new gn(frVar.d(), frVar.c(), this.h);
        return gnVar;
    }
}

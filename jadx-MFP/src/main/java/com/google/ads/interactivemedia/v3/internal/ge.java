package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: IMASDK */
public final class ge implements fq {
    private static final int[] a = {13, 14, 16, 18, 20, 21, 27, 32, 6, 7, 6, 6, 1, 1, 1, 1};
    private static final int[] b = {18, 24, 33, 37, 41, 47, 51, 59, 61, 6, 1, 1, 1, 1, 1, 1};
    private static final byte[] c = vf.c("#!AMR\n");
    private static final byte[] d = vf.c("#!AMR-WB\n");
    private static final int e = b[8];
    private final byte[] f;
    private final int g;
    private boolean h;
    private long i;
    private int j;
    private int k;
    private boolean l;
    private long m;
    private int n;
    private int o;
    private long p;
    private fs q;
    private gc r;
    private fy s;
    private boolean t;

    public ge() {
        this(0);
    }

    public final void c() {
    }

    private ge(int i2) {
        this.g = 0;
        this.f = new byte[1];
        this.n = -1;
    }

    public final boolean a(fr frVar) throws IOException, InterruptedException {
        return b(frVar);
    }

    public final void a(fs fsVar) {
        this.q = fsVar;
        this.r = fsVar.a(0, 1);
        fsVar.a();
    }

    public final int a(fr frVar, fx fxVar) throws IOException, InterruptedException {
        if (frVar.c() != 0 || b(frVar)) {
            if (!this.t) {
                this.t = true;
                this.r.a(bs.a((String) null, this.h ? MimeTypes.AUDIO_AMR_WB : MimeTypes.AUDIO_AMR_NB, (String) null, -1, e, 1, this.h ? 16000 : 8000, -1, null, (fa) null, 0, (String) null));
            }
            int c2 = c(frVar);
            long d2 = frVar.d();
            if (!this.l) {
                if (!((this.g & 1) == 0 || d2 == -1)) {
                    int i2 = this.n;
                    if (i2 == -1 || i2 == this.j) {
                        if (this.o >= 20 || c2 == -1) {
                            int i3 = this.n;
                            fo foVar = new fo(d2, this.m, (int) ((((long) (i3 << 3)) * 1000000) / 20000), i3);
                            this.s = foVar;
                            this.q.a(this.s);
                            this.l = true;
                        }
                    }
                }
                this.s = new ga(-9223372036854775807L);
                this.q.a(this.s);
                this.l = true;
            }
            return c2;
        }
        throw new ca("Could not find AMR header.");
    }

    public final void a(long j2, long j3) {
        this.i = 0;
        this.j = 0;
        this.k = 0;
        if (j2 != 0) {
            fy fyVar = this.s;
            if (fyVar instanceof fo) {
                this.p = ((fo) fyVar).b(j2);
                return;
            }
        }
        this.p = 0;
    }

    private final boolean b(fr frVar) throws IOException, InterruptedException {
        if (a(frVar, c)) {
            this.h = false;
            frVar.b(c.length);
            return true;
        } else if (!a(frVar, d)) {
            return false;
        } else {
            this.h = true;
            frVar.b(d.length);
            return true;
        }
    }

    private static boolean a(fr frVar, byte[] bArr) throws IOException, InterruptedException {
        frVar.a();
        byte[] bArr2 = new byte[bArr.length];
        frVar.c(bArr2, 0, bArr.length);
        return Arrays.equals(bArr2, bArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0040, code lost:
        if ((!r11.h && (r0 < 12 || r0 > 14)) != false) goto L_0x0042;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0047 A[Catch:{ EOFException -> 0x00bf }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0079 A[Catch:{ EOFException -> 0x00bf }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int c(com.google.ads.interactivemedia.v3.internal.fr r12) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r11 = this;
            int r0 = r11.k
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 != 0) goto L_0x00c0
            r12.a()     // Catch:{ EOFException -> 0x00bf }
            byte[] r0 = r11.f     // Catch:{ EOFException -> 0x00bf }
            r12.c(r0, r3, r2)     // Catch:{ EOFException -> 0x00bf }
            byte[] r0 = r11.f     // Catch:{ EOFException -> 0x00bf }
            byte r0 = r0[r3]     // Catch:{ EOFException -> 0x00bf }
            r4 = r0 & 131(0x83, float:1.84E-43)
            if (r4 > 0) goto L_0x00a6
            int r0 = r0 >> 3
            r4 = 15
            r0 = r0 & r4
            if (r0 < 0) goto L_0x0044
            if (r0 > r4) goto L_0x0044
            boolean r4 = r11.h     // Catch:{ EOFException -> 0x00bf }
            if (r4 == 0) goto L_0x002e
            r4 = 10
            if (r0 < r4) goto L_0x002c
            r4 = 13
            if (r0 <= r4) goto L_0x002e
        L_0x002c:
            r4 = 1
            goto L_0x002f
        L_0x002e:
            r4 = 0
        L_0x002f:
            if (r4 != 0) goto L_0x0042
            boolean r4 = r11.h     // Catch:{ EOFException -> 0x00bf }
            if (r4 != 0) goto L_0x003f
            r4 = 12
            if (r0 < r4) goto L_0x003d
            r4 = 14
            if (r0 <= r4) goto L_0x003f
        L_0x003d:
            r4 = 1
            goto L_0x0040
        L_0x003f:
            r4 = 0
        L_0x0040:
            if (r4 == 0) goto L_0x0044
        L_0x0042:
            r4 = 1
            goto L_0x0045
        L_0x0044:
            r4 = 0
        L_0x0045:
            if (r4 != 0) goto L_0x0079
            com.google.ads.interactivemedia.v3.internal.ca r12 = new com.google.ads.interactivemedia.v3.internal.ca     // Catch:{ EOFException -> 0x00bf }
            boolean r2 = r11.h     // Catch:{ EOFException -> 0x00bf }
            if (r2 == 0) goto L_0x0050
            java.lang.String r2 = "WB"
            goto L_0x0052
        L_0x0050:
            java.lang.String r2 = "NB"
        L_0x0052:
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ EOFException -> 0x00bf }
            int r3 = r3.length()     // Catch:{ EOFException -> 0x00bf }
            int r3 = r3 + 35
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x00bf }
            r4.<init>(r3)     // Catch:{ EOFException -> 0x00bf }
            java.lang.String r3 = "Illegal AMR "
            r4.append(r3)     // Catch:{ EOFException -> 0x00bf }
            r4.append(r2)     // Catch:{ EOFException -> 0x00bf }
            java.lang.String r2 = " frame type "
            r4.append(r2)     // Catch:{ EOFException -> 0x00bf }
            r4.append(r0)     // Catch:{ EOFException -> 0x00bf }
            java.lang.String r0 = r4.toString()     // Catch:{ EOFException -> 0x00bf }
            r12.<init>(r0)     // Catch:{ EOFException -> 0x00bf }
            throw r12     // Catch:{ EOFException -> 0x00bf }
        L_0x0079:
            boolean r4 = r11.h     // Catch:{ EOFException -> 0x00bf }
            if (r4 == 0) goto L_0x0082
            int[] r4 = b     // Catch:{ EOFException -> 0x00bf }
            r0 = r4[r0]     // Catch:{ EOFException -> 0x00bf }
            goto L_0x0086
        L_0x0082:
            int[] r4 = a     // Catch:{ EOFException -> 0x00bf }
            r0 = r4[r0]     // Catch:{ EOFException -> 0x00bf }
        L_0x0086:
            r11.j = r0     // Catch:{ EOFException -> 0x00bf }
            int r0 = r11.j
            r11.k = r0
            int r0 = r11.n
            if (r0 != r1) goto L_0x009a
            long r4 = r12.c()
            r11.m = r4
            int r0 = r11.j
            r11.n = r0
        L_0x009a:
            int r0 = r11.n
            int r4 = r11.j
            if (r0 != r4) goto L_0x00c0
            int r0 = r11.o
            int r0 = r0 + r2
            r11.o = r0
            goto L_0x00c0
        L_0x00a6:
            com.google.ads.interactivemedia.v3.internal.ca r12 = new com.google.ads.interactivemedia.v3.internal.ca     // Catch:{ EOFException -> 0x00bf }
            r2 = 42
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x00bf }
            r3.<init>(r2)     // Catch:{ EOFException -> 0x00bf }
            java.lang.String r2 = "Invalid padding bits for frame header "
            r3.append(r2)     // Catch:{ EOFException -> 0x00bf }
            r3.append(r0)     // Catch:{ EOFException -> 0x00bf }
            java.lang.String r0 = r3.toString()     // Catch:{ EOFException -> 0x00bf }
            r12.<init>(r0)     // Catch:{ EOFException -> 0x00bf }
            throw r12     // Catch:{ EOFException -> 0x00bf }
        L_0x00bf:
            return r1
        L_0x00c0:
            com.google.ads.interactivemedia.v3.internal.gc r0 = r11.r
            int r4 = r11.k
            int r12 = r0.a(r12, r4, r2)
            if (r12 != r1) goto L_0x00cb
            return r1
        L_0x00cb:
            int r0 = r11.k
            int r0 = r0 - r12
            r11.k = r0
            int r12 = r11.k
            if (r12 <= 0) goto L_0x00d5
            return r3
        L_0x00d5:
            com.google.ads.interactivemedia.v3.internal.gc r4 = r11.r
            long r0 = r11.p
            long r5 = r11.i
            long r5 = r5 + r0
            r7 = 1
            int r8 = r11.j
            r9 = 0
            r10 = 0
            r4.a(r5, r7, r8, r9, r10)
            long r0 = r11.i
            r4 = 20000(0x4e20, double:9.8813E-320)
            long r0 = r0 + r4
            r11.i = r0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.ge.c(com.google.ads.interactivemedia.v3.internal.fr):int");
    }
}

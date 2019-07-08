package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: IMASDK */
final class bk implements Callback, av, ch, lm, lp, sa {
    private boolean A;
    private boolean B;
    private int C;
    private bq D;
    private long E;
    private int F;
    private final ci[] a;
    private final cj[] b;
    private final rz c;
    private final sb d;
    private final bw e;
    private final sh f;
    private final uj g;
    private final HandlerThread h;
    private final Handler i;
    private final ct j;
    private final cs k;
    private final long l;
    private final boolean m;
    private final au n;
    private final bp o;
    private final ArrayList<bo> p;
    private final ua q;
    private final bz r = new bz();
    private cm s;
    private cb t;
    private ln u;
    private ci[] v;
    private boolean w;
    private boolean x;
    private boolean y;
    private int z;

    public bk(ci[] ciVarArr, rz rzVar, sb sbVar, bw bwVar, sh shVar, boolean z2, int i2, boolean z3, Handler handler, ua uaVar) {
        this.a = ciVarArr;
        this.c = rzVar;
        this.d = sbVar;
        this.e = bwVar;
        this.f = shVar;
        this.x = z2;
        this.z = i2;
        this.A = z3;
        this.i = handler;
        this.q = uaVar;
        this.l = bwVar.e();
        this.m = bwVar.f();
        this.s = cm.b;
        this.t = cb.a(-9223372036854775807L, sbVar);
        this.o = new bp(0);
        this.b = new cj[ciVarArr.length];
        for (int i3 = 0; i3 < ciVarArr.length; i3++) {
            ciVarArr[i3].a(i3);
            this.b[i3] = ciVarArr[i3].b();
        }
        this.n = new au(this, uaVar);
        this.p = new ArrayList<>();
        this.v = new ci[0];
        this.j = new ct();
        this.k = new cs();
        rzVar.a((sa) this, shVar);
        this.h = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.h.start();
        this.g = uaVar.a(this.h.getLooper(), this);
    }

    public final void a(ln lnVar, boolean z2, boolean z3) {
        this.g.a(0, z2 ? 1 : 0, z3 ? 1 : 0, lnVar).sendToTarget();
    }

    public final void a(boolean z2) {
        this.g.a(1, z2 ? 1 : 0, 0).sendToTarget();
    }

    public final void a(cq cqVar, int i2, long j2) {
        this.g.a(3, (Object) new bq(cqVar, i2, j2)).sendToTarget();
    }

    public final void b(boolean z2) {
        this.g.a(6, z2 ? 1 : 0, 0).sendToTarget();
    }

    public final synchronized void a(cg cgVar) {
        if (this.w) {
            Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
            cgVar.a(false);
            return;
        }
        this.g.a(15, (Object) cgVar).sendToTarget();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.w     // Catch:{ all -> 0x0023 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            com.google.ads.interactivemedia.v3.internal.uj r0 = r2.g     // Catch:{ all -> 0x0023 }
            r1 = 7
            r0.a(r1)     // Catch:{ all -> 0x0023 }
            r0 = 0
        L_0x000e:
            boolean r1 = r2.w     // Catch:{ all -> 0x0023 }
            if (r1 != 0) goto L_0x0018
            r2.wait()     // Catch:{ InterruptedException -> 0x0016 }
            goto L_0x000e
        L_0x0016:
            r0 = 1
            goto L_0x000e
        L_0x0018:
            if (r0 == 0) goto L_0x0021
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0023 }
            r0.interrupt()     // Catch:{ all -> 0x0023 }
        L_0x0021:
            monitor-exit(r2)
            return
        L_0x0023:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.bk.a():void");
    }

    public final Looper b() {
        return this.h.getLooper();
    }

    public final void a(ln lnVar, cq cqVar, Object obj) {
        this.g.a(8, (Object) new bn(lnVar, cqVar, obj)).sendToTarget();
    }

    public final void a(ll llVar) {
        this.g.a(9, (Object) llVar).sendToTarget();
    }

    public final void a(cc ccVar) {
        this.g.a(17, (Object) ccVar).sendToTarget();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:164:0x039b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x039c, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x063f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:304:0x0640, code lost:
        r3 = r0;
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:477:0x092a, code lost:
        if (r13 == false) goto L_0x092c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:525:0x09e7, code lost:
        c();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:526:0x09ea, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:530:0x09f2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:531:0x09f5, code lost:
        r2 = r0;
        com.google.ads.interactivemedia.v3.internal.uk.b("ExoPlayerImplInternal", "Internal runtime error.", r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:532:0x09ff, code lost:
        if ((r2 instanceof java.lang.OutOfMemoryError) != false) goto L_0x0a01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:533:0x0a01, code lost:
        r2 = com.google.ads.interactivemedia.v3.internal.aw.a((java.lang.OutOfMemoryError) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:534:0x0a08, code lost:
        r2 = com.google.ads.interactivemedia.v3.internal.aw.a((java.lang.RuntimeException) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:535:0x0a0e, code lost:
        r1.i.obtainMessage(2, r2).sendToTarget();
        a(true, false, false);
        c();
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:536:0x0a22, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:537:0x0a23, code lost:
        r2 = r0;
        com.google.ads.interactivemedia.v3.internal.uk.b("ExoPlayerImplInternal", "Source error.", r2);
        r1.i.obtainMessage(2, com.google.ads.interactivemedia.v3.internal.aw.a(r2)).sendToTarget();
        a(false, false, false);
        c();
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:538:0x0a42, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:539:0x0a43, code lost:
        r2 = false;
        r4 = 2;
        r3 = r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x061b A[Catch:{ all -> 0x0623, all -> 0x0509, all -> 0x00e6, aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError | RuntimeException -> 0x09f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:336:0x06d9 A[Catch:{ all -> 0x0623, all -> 0x0509, all -> 0x00e6, aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError | RuntimeException -> 0x09f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:429:0x0885 A[Catch:{ all -> 0x0623, all -> 0x0509, all -> 0x00e6, aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError | RuntimeException -> 0x09f2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:530:0x09f2 A[ExcHandler: OutOfMemoryError | RuntimeException (r0v2 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:536:0x0a22 A[ExcHandler: IOException (r0v1 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:1:0x0007] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleMessage(android.os.Message r37) {
        /*
            r36 = this;
            r1 = r36
            r2 = r37
            r3 = 2
            r4 = 0
            r5 = 1
            int r6 = r2.what     // Catch:{ aw -> 0x0a42, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r7 = 0
            r9 = 4
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            switch(r6) {
                case 0: goto L_0x09b2;
                case 1: goto L_0x097f;
                case 2: goto L_0x0645;
                case 3: goto L_0x052e;
                case 4: goto L_0x0523;
                case 5: goto L_0x051b;
                case 6: goto L_0x050d;
                case 7: goto L_0x04f0;
                case 8: goto L_0x0281;
                case 9: goto L_0x0238;
                case 10: goto L_0x0220;
                case 11: goto L_0x0117;
                case 12: goto L_0x0103;
                case 13: goto L_0x00ea;
                case 14: goto L_0x00b3;
                case 15: goto L_0x0071;
                case 16: goto L_0x005f;
                case 17: goto L_0x0015;
                default: goto L_0x0013;
            }
        L_0x0013:
            r2 = 0
            return r2
        L_0x0015:
            java.lang.Object r2 = r2.obj     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cc r2 = (com.google.ads.interactivemedia.v3.internal.cc) r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            android.os.Handler r6 = r1.i     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            android.os.Message r6 = r6.obtainMessage(r5, r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r6.sendToTarget()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            float r6 = r2.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r7 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r7 = r7.e()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x002a:
            if (r7 == 0) goto L_0x004d
            boolean r8 = r7.d     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r8 == 0) goto L_0x004d
            com.google.ads.interactivemedia.v3.internal.sb r8 = r7.h()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.rw r8 = r8.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.rt[] r8 = r8.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r9 = r8.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r10 = 0
        L_0x003c:
            if (r10 >= r9) goto L_0x0048
            r11 = r8[r10]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r11 == 0) goto L_0x0045
            r11.a(r6)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x0045:
            int r10 = r10 + 1
            goto L_0x003c
        L_0x0048:
            com.google.ads.interactivemedia.v3.internal.bx r7 = r7.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x002a
        L_0x004d:
            com.google.ads.interactivemedia.v3.internal.ci[] r6 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r7 = r6.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r8 = 0
        L_0x0051:
            if (r8 >= r7) goto L_0x09e7
            r9 = r6[r8]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r9 == 0) goto L_0x005c
            float r10 = r2.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r9.a(r10)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x005c:
            int r8 = r8 + 1
            goto L_0x0051
        L_0x005f:
            java.lang.Object r2 = r2.obj     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cg r2 = (com.google.ads.interactivemedia.v3.internal.cg) r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            android.os.Handler r6 = r2.e()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bl r7 = new com.google.ads.interactivemedia.v3.internal.bl     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r7.<init>(r1, r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r6.post(r7)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x0071:
            java.lang.Object r2 = r2.obj     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cg r2 = (com.google.ads.interactivemedia.v3.internal.cg) r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r6 = r2.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r8 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r8 != 0) goto L_0x0082
            r1.c(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x0082:
            com.google.ads.interactivemedia.v3.internal.ln r6 = r1.u     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r6 == 0) goto L_0x00a7
            int r6 = r1.C     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r6 <= 0) goto L_0x008b
            goto L_0x00a7
        L_0x008b:
            com.google.ads.interactivemedia.v3.internal.bo r6 = new com.google.ads.interactivemedia.v3.internal.bo     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r6.<init>(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r7 = r1.a(r6)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r7 == 0) goto L_0x00a2
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.bo> r2 = r1.p     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r2.add(r6)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.bo> r2 = r1.p     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.util.Collections.sort(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x00a2:
            r2.a(r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x00a7:
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.bo> r6 = r1.p     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bo r7 = new com.google.ads.interactivemedia.v3.internal.bo     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r7.<init>(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r6.add(r7)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x00b3:
            int r6 = r2.arg1     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r6 == 0) goto L_0x00b9
            r6 = 1
            goto L_0x00ba
        L_0x00b9:
            r6 = 0
        L_0x00ba:
            java.lang.Object r2 = r2.obj     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.util.concurrent.atomic.AtomicBoolean r2 = (java.util.concurrent.atomic.AtomicBoolean) r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r7 = r1.B     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r7 == r6) goto L_0x00da
            r1.B = r6     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r6 != 0) goto L_0x00da
            com.google.ads.interactivemedia.v3.internal.ci[] r6 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r7 = r6.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r8 = 0
        L_0x00ca:
            if (r8 >= r7) goto L_0x00da
            r9 = r6[r8]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r10 = r9.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r10 != 0) goto L_0x00d7
            r9.r()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x00d7:
            int r8 = r8 + 1
            goto L_0x00ca
        L_0x00da:
            if (r2 == 0) goto L_0x09e7
            monitor-enter(r36)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r2.set(r5)     // Catch:{ all -> 0x00e6 }
            r36.notifyAll()     // Catch:{ all -> 0x00e6 }
            monitor-exit(r36)     // Catch:{ all -> 0x00e6 }
            goto L_0x09e7
        L_0x00e6:
            r0 = move-exception
            r2 = r0
            monitor-exit(r36)     // Catch:{ all -> 0x00e6 }
            throw r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x00ea:
            int r2 = r2.arg1     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x00f0
            r2 = 1
            goto L_0x00f1
        L_0x00f0:
            r2 = 0
        L_0x00f1:
            r1.A = r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r6 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r2 = r6.a(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 != 0) goto L_0x00fe
            r1.d(r5)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x00fe:
            r1.e(r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x0103:
            int r2 = r2.arg1     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.z = r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r6 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r2 = r6.a(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 != 0) goto L_0x0112
            r1.d(r5)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x0112:
            r1.e(r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x0117:
            com.google.ads.interactivemedia.v3.internal.bz r2 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r2 = r2.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x09e7
            com.google.ads.interactivemedia.v3.internal.au r2 = r1.n     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cc r2 = r2.e()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            float r2 = r2.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r6 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r6 = r6.c()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r7 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r7 = r7.d()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r8 = 1
        L_0x0134:
            if (r6 == 0) goto L_0x09e7
            boolean r10 = r6.d     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r10 != 0) goto L_0x013c
            goto L_0x09e7
        L_0x013c:
            com.google.ads.interactivemedia.v3.internal.cb r10 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cq r10 = r10.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.sb r12 = r6.b(r2, r10)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r12 != 0) goto L_0x014e
            if (r6 != r7) goto L_0x0149
            r8 = 0
        L_0x0149:
            com.google.ads.interactivemedia.v3.internal.bx r6 = r6.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x0134
        L_0x014e:
            if (r8 == 0) goto L_0x01ef
            com.google.ads.interactivemedia.v3.internal.bz r2 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r2 = r2.c()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r6 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r15 = r6.a(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.ci[] r6 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r6 = r6.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean[] r6 = new boolean[r6]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r7 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r13 = r7.m     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r11 = r2
            r16 = r6
            long r7 = r11.a(r12, r13, r15, r16)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r10 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r10 = r10.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r10 == r9) goto L_0x019e
            com.google.ads.interactivemedia.v3.internal.cb r10 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r10 = r10.m     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r12 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r12 == 0) goto L_0x019e
            com.google.ads.interactivemedia.v3.internal.cb r10 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r11 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.lo r11 = r11.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r12 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r12 = r12.e     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r22 = r36.k()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r16 = r10
            r17 = r11
            r18 = r7
            r20 = r12
            com.google.ads.interactivemedia.v3.internal.cb r10 = r16.a(r17, r18, r20, r22)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.t = r10     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bp r10 = r1.o     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r10.b(r9)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.a(r7)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x019e:
            com.google.ads.interactivemedia.v3.internal.ci[] r7 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r7 = r7.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean[] r7 = new boolean[r7]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r8 = 0
            r10 = 0
        L_0x01a5:
            com.google.ads.interactivemedia.v3.internal.ci[] r11 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r11 = r11.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r8 >= r11) goto L_0x01db
            com.google.ads.interactivemedia.v3.internal.ci[] r11 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r11 = r11[r8]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r12 = r11.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r12 == 0) goto L_0x01b6
            r12 = 1
            goto L_0x01b7
        L_0x01b6:
            r12 = 0
        L_0x01b7:
            r7[r8] = r12     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.mt[] r12 = r2.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r12 = r12[r8]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r12 == 0) goto L_0x01c1
            int r10 = r10 + 1
        L_0x01c1:
            boolean r13 = r7[r8]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r13 == 0) goto L_0x01d8
            com.google.ads.interactivemedia.v3.internal.mt r13 = r11.h()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r12 == r13) goto L_0x01cf
            r1.b(r11)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x01d8
        L_0x01cf:
            boolean r12 = r6[r8]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r12 == 0) goto L_0x01d8
            long r12 = r1.E     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r11.a(r12)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x01d8:
            int r8 = r8 + 1
            goto L_0x01a5
        L_0x01db:
            com.google.ads.interactivemedia.v3.internal.cb r6 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.mz r8 = r2.g()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.sb r2 = r2.h()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r2 = r6.a(r8, r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.t = r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.a(r7, r10)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x020a
        L_0x01ef:
            com.google.ads.interactivemedia.v3.internal.bz r2 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r2.a(r6)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r2 = r6.d     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x020a
            com.google.ads.interactivemedia.v3.internal.by r2 = r6.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r7 = r2.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r10 = r1.E     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r13 = r6.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r10 = r10 - r13
            long r7 = java.lang.Math.max(r7, r10)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r6.a(r12, r7, r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x020a:
            r1.e(r5)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r2 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r2 = r2.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == r9) goto L_0x09e7
            r36.j()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r36.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.uj r2 = r1.g     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r2.a(r3)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x0220:
            java.lang.Object r2 = r2.obj     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.ll r2 = (com.google.ads.interactivemedia.v3.internal.ll) r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r6 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r2 = r6.a(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x09e7
            com.google.ads.interactivemedia.v3.internal.bz r2 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r6 = r1.E     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r2.a(r6)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r36.j()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x0238:
            java.lang.Object r2 = r2.obj     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.ll r2 = (com.google.ads.interactivemedia.v3.internal.ll) r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r6 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r2 = r6.a(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x09e7
            com.google.ads.interactivemedia.v3.internal.bz r2 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r2 = r2.b()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.au r6 = r1.n     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cc r6 = r6.e()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            float r6 = r6.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r8 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cq r8 = r8.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r2.a(r6, r8)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.mz r6 = r2.g()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.sb r2 = r2.h()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.a(r6, r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r2 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r2 = r2.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 != 0) goto L_0x027c
            com.google.ads.interactivemedia.v3.internal.bz r2 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r2 = r2.h()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.by r2 = r2.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r8 = r2.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.a(r8)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.a(r7)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x027c:
            r36.j()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x0281:
            java.lang.Object r2 = r2.obj     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bn r2 = (com.google.ads.interactivemedia.v3.internal.bn) r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.ln r6 = r2.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.ln r8 = r1.u     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r6 != r8) goto L_0x09e7
            com.google.ads.interactivemedia.v3.internal.cb r6 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cq r6 = r6.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cq r8 = r2.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Object r2 = r2.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r9 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r9.a(r8)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r9 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r15 = new com.google.ads.interactivemedia.v3.internal.cb     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.lo r14 = r9.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r10 = r9.d     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r12 = r9.e     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r3 = r9.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r7 = r9.g     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.mz r4 = r9.h     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.sb r5 = r9.i     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r37 = r6
            com.google.ads.interactivemedia.v3.internal.lo r6 = r9.j     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r25 = r5
            r26 = r6
            long r5 = r9.k     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r27 = r5
            long r5 = r9.l     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r29 = r5
            long r5 = r9.m     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r9 = r14
            r14 = r15
            r33 = r15
            r15 = r8
            r16 = r2
            r17 = r9
            r18 = r10
            r20 = r12
            r22 = r3
            r23 = r7
            r24 = r4
            r31 = r5
            r14.<init>(r15, r16, r17, r18, r20, r22, r23, r24, r25, r26, r27, r29, r31)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r2 = r33
            r1.t = r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.bo> r2 = r1.p     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r2 = r2.size()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3 = 1
            int r2 = r2 - r3
        L_0x02e0:
            if (r2 < 0) goto L_0x0306
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.bo> r3 = r1.p     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Object r3 = r3.get(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bo r3 = (com.google.ads.interactivemedia.v3.internal.bo) r3     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r3 = r1.a(r3)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r3 != 0) goto L_0x0303
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.bo> r3 = r1.p     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Object r3 = r3.get(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bo r3 = (com.google.ads.interactivemedia.v3.internal.bo) r3     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cg r3 = r3.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r4 = 0
            r3.a(r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.bo> r3 = r1.p     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3.remove(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x0303:
            int r2 = r2 + -1
            goto L_0x02e0
        L_0x0306:
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.bo> r2 = r1.p     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.util.Collections.sort(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r2 = r1.C     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 <= 0) goto L_0x039f
            com.google.ads.interactivemedia.v3.internal.bp r2 = r1.o     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r3 = r1.C     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r2.a(r3)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r2 = 0
            r1.C = r2     // Catch:{ aw -> 0x039b, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bq r2 = r1.D     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x0352
            com.google.ads.interactivemedia.v3.internal.bq r2 = r1.D     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3 = 1
            android.util.Pair r2 = r1.a(r2, r3)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3 = 0
            r1.D = r3     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 != 0) goto L_0x032e
            r36.i()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x032e:
            java.lang.Object r3 = r2.first     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Object r2 = r2.second     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r8 = r2.longValue()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r2 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.lo r5 = r2.a(r3, r8)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r4 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r2 = r5.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x0349
            r6 = 0
            goto L_0x034a
        L_0x0349:
            r6 = r8
        L_0x034a:
            com.google.ads.interactivemedia.v3.internal.cb r2 = r4.a(r5, r6, r8)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.t = r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x0352:
            com.google.ads.interactivemedia.v3.internal.cb r2 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r2 = r2.d     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x09e7
            boolean r2 = r8.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x036a
            r36.i()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x036a:
            int r2 = r8.c()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            android.util.Pair r2 = r1.b(r8, r2, r3)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Object r3 = r2.first     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Object r2 = r2.second     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r8 = r2.longValue()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r2 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.lo r5 = r2.a(r3, r8)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r4 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r2 = r5.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x0392
            r6 = 0
            goto L_0x0393
        L_0x0392:
            r6 = r8
        L_0x0393:
            com.google.ads.interactivemedia.v3.internal.cb r2 = r4.a(r5, r6, r8)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.t = r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x039b:
            r0 = move-exception
            r3 = r0
            goto L_0x09f0
        L_0x039f:
            boolean r2 = r37.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x03dc
            boolean r2 = r8.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 != 0) goto L_0x09e7
            int r2 = r8.c()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            android.util.Pair r2 = r1.b(r8, r2, r3)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Object r3 = r2.first     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Object r2 = r2.second     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r8 = r2.longValue()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r2 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.lo r5 = r2.a(r3, r8)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r4 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r2 = r5.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x03d3
            r6 = 0
            goto L_0x03d4
        L_0x03d3:
            r6 = r8
        L_0x03d4:
            com.google.ads.interactivemedia.v3.internal.cb r2 = r4.a(r5, r6, r8)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.t = r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x03dc:
            com.google.ads.interactivemedia.v3.internal.bz r2 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r2 = r2.e()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r3 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r13 = r3.e     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 != 0) goto L_0x03ef
            com.google.ads.interactivemedia.v3.internal.cb r3 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.lo r3 = r3.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Object r3 = r3.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x03f1
        L_0x03ef:
            java.lang.Object r3 = r2.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x03f1:
            int r4 = r8.a(r3)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r5 = -1
            if (r4 != r5) goto L_0x0463
            r4 = r37
            java.lang.Object r3 = r1.a(r3, r4, r8)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r3 != 0) goto L_0x0405
            r36.i()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x0405:
            com.google.ads.interactivemedia.v3.internal.cs r4 = r1.k     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cs r3 = r8.a(r3, r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r3 = r3.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            android.util.Pair r3 = r1.b(r8, r3, r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Object r4 = r3.first     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Object r3 = r3.second     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r9 = r3.longValue()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r3 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.lo r6 = r3.a(r4, r9)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x0447
        L_0x0428:
            com.google.ads.interactivemedia.v3.internal.bx r3 = r2.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r3 == 0) goto L_0x0447
            com.google.ads.interactivemedia.v3.internal.bx r2 = r2.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.by r3 = r2.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.lo r3 = r3.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r3 = r3.equals(r6)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r3 == 0) goto L_0x0428
            com.google.ads.interactivemedia.v3.internal.bz r3 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.by r4 = r2.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.by r3 = r3.a(r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r2.f = r3     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x0428
        L_0x0447:
            boolean r2 = r6.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x0450
            r2 = 0
            goto L_0x0451
        L_0x0450:
            r2 = r9
        L_0x0451:
            long r7 = r1.a(r6, r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r5 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r11 = r36.k()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r2 = r5.a(r6, r7, r9, r11)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.t = r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x0463:
            com.google.ads.interactivemedia.v3.internal.cb r2 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.lo r2 = r2.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r4 = r2.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x0495
            com.google.ads.interactivemedia.v3.internal.bz r4 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.lo r10 = r4.a(r3, r13)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r2 = r10.equals(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 != 0) goto L_0x0495
            boolean r2 = r10.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x0482
            r2 = 0
            goto L_0x0483
        L_0x0482:
            r2 = r13
        L_0x0483:
            long r11 = r1.a(r10, r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r9 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r15 = r36.k()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r2 = r9.a(r10, r11, r13, r15)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.t = r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x0495:
            com.google.ads.interactivemedia.v3.internal.bz r2 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r3 = r1.E     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r5 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r5 = r5.d()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r10 = -9223372036854775808
            if (r5 != 0) goto L_0x04a6
            r7 = 0
            goto L_0x04de
        L_0x04a6:
            long r6 = r5.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r7 = r6
            r6 = 0
        L_0x04ac:
            com.google.ads.interactivemedia.v3.internal.ci[] r9 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r9 = r9.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r6 >= r9) goto L_0x04de
            com.google.ads.interactivemedia.v3.internal.ci[] r9 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r9 = r9[r6]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r9 = r9.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r9 == 0) goto L_0x04db
            com.google.ads.interactivemedia.v3.internal.ci[] r9 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r9 = r9[r6]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.mt r9 = r9.h()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.mt[] r12 = r5.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r12 = r12[r6]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r9 != r12) goto L_0x04db
            com.google.ads.interactivemedia.v3.internal.ci[] r9 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r9 = r9[r6]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r12 = r9.j()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r9 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r9 != 0) goto L_0x04d7
            r7 = r10
            goto L_0x04de
        L_0x04d7:
            long r7 = java.lang.Math.max(r12, r7)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x04db:
            int r6 = r6 + 1
            goto L_0x04ac
        L_0x04de:
            boolean r2 = r2.a(r3, r7)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 != 0) goto L_0x04ea
            r2 = 0
            r1.d(r2)     // Catch:{ aw -> 0x039b, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r2 = 0
            goto L_0x04eb
        L_0x04ea:
            r2 = 0
        L_0x04eb:
            r1.e(r2)     // Catch:{ aw -> 0x039b, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x04f0:
            r2 = 1
            r1.a(r2, r2, r2, r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bw r3 = r1.e     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3.c()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.a(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            android.os.HandlerThread r3 = r1.h     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3.quit()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            monitor-enter(r36)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.w = r2     // Catch:{ all -> 0x0509 }
            r36.notifyAll()     // Catch:{ all -> 0x0509 }
            monitor-exit(r36)     // Catch:{ all -> 0x0509 }
            return r2
        L_0x0509:
            r0 = move-exception
            r2 = r0
            monitor-exit(r36)     // Catch:{ all -> 0x0509 }
            throw r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x050d:
            int r2 = r2.arg1     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x0513
            r2 = 1
            goto L_0x0514
        L_0x0513:
            r2 = 0
        L_0x0514:
            r3 = 1
            r4 = 0
            r1.a(r4, r2, r3)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x051b:
            java.lang.Object r2 = r2.obj     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cm r2 = (com.google.ads.interactivemedia.v3.internal.cm) r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.s = r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x0523:
            java.lang.Object r2 = r2.obj     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cc r2 = (com.google.ads.interactivemedia.v3.internal.cc) r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.au r3 = r1.n     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3.a(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x052e:
            java.lang.Object r2 = r2.obj     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bq r2 = (com.google.ads.interactivemedia.v3.internal.bq) r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bp r3 = r1.o     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r4 = 1
            r3.a(r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            android.util.Pair r3 = r1.a(r2, r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r3 != 0) goto L_0x0555
            com.google.ads.interactivemedia.v3.internal.cb r3 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r4 = r1.A     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.ct r5 = r1.j     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.lo r3 = r3.a(r4, r5)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r13 = r3
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5 = 1
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            goto L_0x058f
        L_0x0555:
            java.lang.Object r4 = r3.first     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Object r5 = r3.second     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r12 = r5.longValue()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r5 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.lo r4 = r5.a(r4, r12)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r5 = r4.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r5 == 0) goto L_0x0574
            r5 = 1
            r10 = 0
            r34 = r12
            r13 = r4
            r3 = r34
            goto L_0x058f
        L_0x0574:
            java.lang.Object r3 = r3.second     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r10 = r3.longValue()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r5 = r2.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x0589
            r5 = 1
            goto L_0x058a
        L_0x0589:
            r5 = 0
        L_0x058a:
            r34 = r12
            r13 = r4
            r3 = r34
        L_0x058f:
            com.google.ads.interactivemedia.v3.internal.ln r6 = r1.u     // Catch:{ all -> 0x0623 }
            if (r6 == 0) goto L_0x0608
            int r6 = r1.C     // Catch:{ all -> 0x0623 }
            if (r6 <= 0) goto L_0x0599
            goto L_0x0608
        L_0x0599:
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r2 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r2 != 0) goto L_0x05ab
            r1.a(r9)     // Catch:{ all -> 0x0623 }
            r2 = 1
            r6 = 0
            r1.a(r6, r6, r2, r6)     // Catch:{ all -> 0x0623 }
            goto L_0x060a
        L_0x05ab:
            com.google.ads.interactivemedia.v3.internal.cb r2 = r1.t     // Catch:{ all -> 0x0623 }
            com.google.ads.interactivemedia.v3.internal.lo r2 = r2.c     // Catch:{ all -> 0x0623 }
            boolean r2 = r13.equals(r2)     // Catch:{ all -> 0x0623 }
            if (r2 == 0) goto L_0x05f9
            com.google.ads.interactivemedia.v3.internal.bz r2 = r1.r     // Catch:{ all -> 0x0623 }
            com.google.ads.interactivemedia.v3.internal.bx r2 = r2.c()     // Catch:{ all -> 0x0623 }
            if (r2 == 0) goto L_0x05cc
            r6 = 0
            int r8 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x05cc
            com.google.ads.interactivemedia.v3.internal.ll r2 = r2.a     // Catch:{ all -> 0x0623 }
            com.google.ads.interactivemedia.v3.internal.cm r6 = r1.s     // Catch:{ all -> 0x0623 }
            long r6 = r2.a(r10, r6)     // Catch:{ all -> 0x0623 }
            goto L_0x05cd
        L_0x05cc:
            r6 = r10
        L_0x05cd:
            long r8 = com.google.ads.interactivemedia.v3.internal.at.a(r6)     // Catch:{ all -> 0x0623 }
            com.google.ads.interactivemedia.v3.internal.cb r2 = r1.t     // Catch:{ all -> 0x0623 }
            long r14 = r2.m     // Catch:{ all -> 0x0623 }
            long r14 = com.google.ads.interactivemedia.v3.internal.at.a(r14)     // Catch:{ all -> 0x0623 }
            int r2 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r2 != 0) goto L_0x05fa
            com.google.ads.interactivemedia.v3.internal.cb r2 = r1.t     // Catch:{ all -> 0x0623 }
            long r14 = r2.m     // Catch:{ all -> 0x0623 }
            com.google.ads.interactivemedia.v3.internal.cb r12 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r18 = r36.k()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r16 = r3
            com.google.ads.interactivemedia.v3.internal.cb r2 = r12.a(r13, r14, r16, r18)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.t = r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r5 == 0) goto L_0x09e7
            com.google.ads.interactivemedia.v3.internal.bp r2 = r1.o     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3 = 2
            r2.b(r3)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x05f9:
            r6 = r10
        L_0x05fa:
            long r6 = r1.a(r13, r6)     // Catch:{ all -> 0x0623 }
            int r2 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x0604
            r2 = 1
            goto L_0x0605
        L_0x0604:
            r2 = 0
        L_0x0605:
            r5 = r5 | r2
            r14 = r6
            goto L_0x060b
        L_0x0608:
            r1.D = r2     // Catch:{ all -> 0x0623 }
        L_0x060a:
            r14 = r10
        L_0x060b:
            com.google.ads.interactivemedia.v3.internal.cb r12 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r18 = r36.k()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r16 = r3
            com.google.ads.interactivemedia.v3.internal.cb r2 = r12.a(r13, r14, r16, r18)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.t = r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r5 == 0) goto L_0x09e7
            com.google.ads.interactivemedia.v3.internal.bp r2 = r1.o     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3 = 2
            r2.b(r3)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x0623:
            r0 = move-exception
            r2 = r0
            com.google.ads.interactivemedia.v3.internal.cb r14 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r20 = r36.k()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r15 = r13
            r16 = r10
            r18 = r3
            com.google.ads.interactivemedia.v3.internal.cb r3 = r14.a(r15, r16, r18, r20)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.t = r3     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r5 == 0) goto L_0x0644
            com.google.ads.interactivemedia.v3.internal.bp r3 = r1.o     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r4 = 2
            r3.b(r4)     // Catch:{ aw -> 0x063f, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x0644
        L_0x063f:
            r0 = move-exception
            r3 = r0
            r2 = 0
            goto L_0x0a46
        L_0x0644:
            throw r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x0645:
            com.google.ads.interactivemedia.v3.internal.ua r2 = r1.q     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r2 = r2.b()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.ln r4 = r1.u     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x0808
            int r4 = r1.C     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 <= 0) goto L_0x065a
            com.google.ads.interactivemedia.v3.internal.ln r4 = r1.u     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r4.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x0808
        L_0x065a:
            com.google.ads.interactivemedia.v3.internal.bz r4 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r5 = r1.E     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r4.a(r5)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r4 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r4 = r4.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x06b4
            com.google.ads.interactivemedia.v3.internal.bz r4 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r5 = r1.E     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r7 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.by r4 = r4.a(r5, r7)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 != 0) goto L_0x0694
            com.google.ads.interactivemedia.v3.internal.bz r4 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r4 = r4.b()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x068e
            com.google.ads.interactivemedia.v3.internal.ci[] r4 = r1.v     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r5 = r4.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r6 = 0
        L_0x0681:
            if (r6 >= r5) goto L_0x068e
            r7 = r4[r6]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r7 = r7.i()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r7 == 0) goto L_0x06b4
            int r6 = r6 + 1
            goto L_0x0681
        L_0x068e:
            com.google.ads.interactivemedia.v3.internal.ln r4 = r1.u     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r4.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x06b4
        L_0x0694:
            com.google.ads.interactivemedia.v3.internal.bz r10 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cj[] r11 = r1.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.rz r12 = r1.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bw r5 = r1.e     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.sf r13 = r5.d()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.ln r14 = r1.u     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r15 = r4
            com.google.ads.interactivemedia.v3.internal.ll r5 = r10.a(r11, r12, r13, r14, r15)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r6 = r4.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r5.a(r1, r6)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r4 = 1
            r1.c(r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r4 = 0
            r1.e(r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x06b4:
            com.google.ads.interactivemedia.v3.internal.bz r4 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r4 = r4.b()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x06cd
            boolean r4 = r4.c()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x06c3
            goto L_0x06cd
        L_0x06c3:
            com.google.ads.interactivemedia.v3.internal.cb r4 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r4 = r4.g     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 != 0) goto L_0x06d1
            r36.j()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x06d1
        L_0x06cd:
            r4 = 0
            r1.c(r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x06d1:
            com.google.ads.interactivemedia.v3.internal.bz r4 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r4 = r4.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x0808
            com.google.ads.interactivemedia.v3.internal.bz r4 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r4 = r4.c()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r5 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r5 = r5.d()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r6 = 0
        L_0x06e6:
            boolean r7 = r1.x     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r7 == 0) goto L_0x0734
            if (r4 == r5) goto L_0x0734
            long r10 = r1.E     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r7 = r4.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r12 = r7.b()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r7 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r7 < 0) goto L_0x0734
            if (r6 == 0) goto L_0x06ff
            r36.c()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x06ff:
            com.google.ads.interactivemedia.v3.internal.by r6 = r4.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r6 = r6.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r6 == 0) goto L_0x0707
            r6 = 0
            goto L_0x0708
        L_0x0707:
            r6 = 3
        L_0x0708:
            com.google.ads.interactivemedia.v3.internal.bz r7 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r7 = r7.h()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.a(r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r10 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.by r4 = r7.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.lo r11 = r4.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.by r4 = r7.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r12 = r4.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.by r4 = r7.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r14 = r4.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r16 = r36.k()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r4 = r10.a(r11, r12, r14, r16)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.t = r4     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bp r4 = r1.o     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r4.b(r6)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r36.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r4 = r7
            r6 = 1
            goto L_0x06e6
        L_0x0734:
            com.google.ads.interactivemedia.v3.internal.by r4 = r5.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r4 = r4.g     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x075c
            r4 = 0
        L_0x073b:
            com.google.ads.interactivemedia.v3.internal.ci[] r6 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r6 = r6.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 >= r6) goto L_0x0808
            com.google.ads.interactivemedia.v3.internal.ci[] r6 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r6 = r6[r4]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.mt[] r7 = r5.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r7 = r7[r4]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r7 == 0) goto L_0x0759
            com.google.ads.interactivemedia.v3.internal.mt r10 = r6.h()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r10 != r7) goto L_0x0759
            boolean r7 = r6.i()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r7 == 0) goto L_0x0759
            r6.k()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x0759:
            int r4 = r4 + 1
            goto L_0x073b
        L_0x075c:
            com.google.ads.interactivemedia.v3.internal.bx r4 = r5.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x0808
            r4 = 0
        L_0x0763:
            com.google.ads.interactivemedia.v3.internal.ci[] r6 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r6 = r6.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 >= r6) goto L_0x0783
            com.google.ads.interactivemedia.v3.internal.ci[] r6 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r6 = r6[r4]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.mt[] r7 = r5.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r7 = r7[r4]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.mt r10 = r6.h()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r10 != r7) goto L_0x0808
            if (r7 == 0) goto L_0x0780
            boolean r6 = r6.i()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r6 != 0) goto L_0x0780
            goto L_0x0808
        L_0x0780:
            int r4 = r4 + 1
            goto L_0x0763
        L_0x0783:
            com.google.ads.interactivemedia.v3.internal.bx r4 = r5.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r4 = r4.d     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 != 0) goto L_0x0790
            r36.h()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x0808
        L_0x0790:
            com.google.ads.interactivemedia.v3.internal.sb r4 = r5.h()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bz r5 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r5 = r5.g()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.sb r6 = r5.h()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.ll r7 = r5.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r10 = r7.c()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r7 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r7 == 0) goto L_0x07af
            r7 = 1
            goto L_0x07b0
        L_0x07af:
            r7 = 0
        L_0x07b0:
            r10 = 0
        L_0x07b1:
            com.google.ads.interactivemedia.v3.internal.ci[] r11 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r11 = r11.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r10 >= r11) goto L_0x0808
            com.google.ads.interactivemedia.v3.internal.ci[] r11 = r1.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r11 = r11[r10]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r12 = r4.a(r10)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r12 == 0) goto L_0x0805
            if (r7 != 0) goto L_0x0802
            boolean r12 = r11.l()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r12 != 0) goto L_0x0805
            com.google.ads.interactivemedia.v3.internal.rw r12 = r6.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.rt r12 = r12.a(r10)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r13 = r6.a(r10)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cj[] r14 = r1.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r14 = r14[r10]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r14 = r14.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r15 = 6
            if (r14 != r15) goto L_0x07df
            r14 = 1
            goto L_0x07e0
        L_0x07df:
            r14 = 0
        L_0x07e0:
            com.google.ads.interactivemedia.v3.internal.ck[] r15 = r4.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r15 = r15[r10]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.ck[] r8 = r6.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r8 = r8[r10]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r13 == 0) goto L_0x0802
            boolean r8 = r8.equals(r15)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r8 == 0) goto L_0x0802
            if (r14 != 0) goto L_0x0802
            com.google.ads.interactivemedia.v3.internal.bs[] r8 = a(r12)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.mt[] r12 = r5.c     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r12 = r12[r10]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r13 = r5.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r11.a(r8, r12, r13)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x0805
        L_0x0802:
            r11.k()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x0805:
            int r10 = r10 + 1
            goto L_0x07b1
        L_0x0808:
            com.google.ads.interactivemedia.v3.internal.bz r4 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r4 = r4.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r5 = 10
            if (r4 != 0) goto L_0x081a
            r36.h()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.a(r2, r5)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x081a:
            com.google.ads.interactivemedia.v3.internal.bz r4 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r4 = r4.c()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            java.lang.String r7 = "doSomeWork"
            com.google.ads.interactivemedia.v3.internal.qi.b(r7)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r36.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r7 = android.os.SystemClock.elapsedRealtime()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r10 = 1000(0x3e8, double:4.94E-321)
            long r7 = r7 * r10
            com.google.ads.interactivemedia.v3.internal.ll r12 = r4.a     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cb r13 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r13 = r13.m     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r10 = r1.l     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r13 = r13 - r10
            boolean r10 = r1.m     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r12.a(r13, r10)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.ci[] r10 = r1.v     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r11 = r10.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r12 = 0
            r13 = 1
            r14 = 1
        L_0x0844:
            if (r12 >= r11) goto L_0x0894
            r15 = r10[r12]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r5 = r1.E     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r15.a(r5, r7)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r14 == 0) goto L_0x0857
            boolean r5 = r15.o()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r5 == 0) goto L_0x0857
            r14 = 1
            goto L_0x0858
        L_0x0857:
            r14 = 0
        L_0x0858:
            boolean r5 = r15.n()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r5 != 0) goto L_0x0882
            boolean r5 = r15.o()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r5 != 0) goto L_0x0882
            com.google.ads.interactivemedia.v3.internal.bz r5 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r5 = r5.d()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r5 = r5.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r5 == 0) goto L_0x087c
            boolean r5 = r5.d     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r5 == 0) goto L_0x087c
            boolean r5 = r15.i()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r5 == 0) goto L_0x087c
            r5 = 1
            goto L_0x087d
        L_0x087c:
            r5 = 0
        L_0x087d:
            if (r5 == 0) goto L_0x0880
            goto L_0x0882
        L_0x0880:
            r5 = 0
            goto L_0x0883
        L_0x0882:
            r5 = 1
        L_0x0883:
            if (r5 != 0) goto L_0x0888
            r15.m()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x0888:
            if (r13 == 0) goto L_0x088e
            if (r5 == 0) goto L_0x088e
            r13 = 1
            goto L_0x088f
        L_0x088e:
            r13 = 0
        L_0x088f:
            int r12 = r12 + 1
            r5 = 10
            goto L_0x0844
        L_0x0894:
            if (r13 != 0) goto L_0x0899
            r36.h()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x0899:
            com.google.ads.interactivemedia.v3.internal.by r5 = r4.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r5 = r5.e     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r14 == 0) goto L_0x08be
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 == 0) goto L_0x08b0
            com.google.ads.interactivemedia.v3.internal.cb r7 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r7 = r7.m     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 > 0) goto L_0x08be
        L_0x08b0:
            com.google.ads.interactivemedia.v3.internal.by r4 = r4.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r4 = r4.g     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x08be
            r1.a(r9)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r36.e()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x0937
        L_0x08be:
            com.google.ads.interactivemedia.v3.internal.cb r4 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r4 = r4.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r5 = 2
            if (r4 != r5) goto L_0x0917
            com.google.ads.interactivemedia.v3.internal.ci[] r4 = r1.v     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r4 = r4.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 != 0) goto L_0x08cf
            boolean r4 = r36.g()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x0909
        L_0x08cf:
            if (r13 == 0) goto L_0x0908
            com.google.ads.interactivemedia.v3.internal.cb r4 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r4 = r4.g     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 != 0) goto L_0x08d9
            r4 = 1
            goto L_0x0909
        L_0x08d9:
            com.google.ads.interactivemedia.v3.internal.bz r4 = r1.r     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bx r4 = r4.b()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r5 = r4.c()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r5 == 0) goto L_0x08ed
            com.google.ads.interactivemedia.v3.internal.by r4 = r4.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r4 = r4.g     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x08ed
            r4 = 1
            goto L_0x08ee
        L_0x08ed:
            r4 = 0
        L_0x08ee:
            if (r4 != 0) goto L_0x0906
            com.google.ads.interactivemedia.v3.internal.bw r4 = r1.e     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            long r5 = r36.k()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.au r7 = r1.n     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.cc r7 = r7.e()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            float r7 = r7.b     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r8 = r1.y     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r4 = r4.a(r5, r7, r8)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x0908
        L_0x0906:
            r4 = 1
            goto L_0x0909
        L_0x0908:
            r4 = 0
        L_0x0909:
            if (r4 == 0) goto L_0x0917
            r4 = 3
            r1.a(r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            boolean r4 = r1.x     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x0937
            r36.d()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x0937
        L_0x0917:
            com.google.ads.interactivemedia.v3.internal.cb r4 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r4 = r4.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r5 = 3
            if (r4 != r5) goto L_0x0937
            com.google.ads.interactivemedia.v3.internal.ci[] r4 = r1.v     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r4 = r4.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 != 0) goto L_0x092a
            boolean r4 = r36.g()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x092c
            goto L_0x0937
        L_0x092a:
            if (r13 != 0) goto L_0x0937
        L_0x092c:
            boolean r4 = r1.x     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.y = r4     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r4 = 2
            r1.a(r4)     // Catch:{ aw -> 0x063f, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r36.e()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x0937:
            com.google.ads.interactivemedia.v3.internal.cb r4 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r4 = r4.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r5 = 2
            if (r4 != r5) goto L_0x094c
            com.google.ads.interactivemedia.v3.internal.ci[] r4 = r1.v     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r5 = r4.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r6 = 0
        L_0x0942:
            if (r6 >= r5) goto L_0x094c
            r7 = r4[r6]     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r7.m()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r6 = r6 + 1
            goto L_0x0942
        L_0x094c:
            boolean r4 = r1.x     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x0957
            com.google.ads.interactivemedia.v3.internal.cb r4 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r4 = r4.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r5 = 3
            if (r4 == r5) goto L_0x095e
        L_0x0957:
            com.google.ads.interactivemedia.v3.internal.cb r4 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r4 = r4.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r5 = 2
            if (r4 != r5) goto L_0x0964
        L_0x095e:
            r4 = 10
            r1.a(r2, r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x097b
        L_0x0964:
            com.google.ads.interactivemedia.v3.internal.ci[] r4 = r1.v     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r4 = r4.length     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x0975
            com.google.ads.interactivemedia.v3.internal.cb r4 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r4 = r4.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == r9) goto L_0x0975
            r4 = 1000(0x3e8, double:4.94E-321)
            r1.a(r2, r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x097b
        L_0x0975:
            com.google.ads.interactivemedia.v3.internal.uj r2 = r1.g     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3 = 2
            r2.b(r3)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x097b:
            com.google.ads.interactivemedia.v3.internal.qi.e()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x097f:
            int r2 = r2.arg1     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x0985
            r2 = 1
            goto L_0x0986
        L_0x0985:
            r2 = 0
        L_0x0986:
            r3 = 0
            r1.y = r3     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.x = r2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 != 0) goto L_0x0994
            r36.e()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r36.f()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x0994:
            com.google.ads.interactivemedia.v3.internal.cb r2 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r2 = r2.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3 = 3
            if (r2 != r3) goto L_0x09a5
            r36.d()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.uj r2 = r1.g     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3 = 2
            r2.a(r3)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x09a5:
            com.google.ads.interactivemedia.v3.internal.cb r2 = r1.t     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r2 = r2.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3 = 2
            if (r2 != r3) goto L_0x09e7
            com.google.ads.interactivemedia.v3.internal.uj r2 = r1.g     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r2.a(r3)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            goto L_0x09e7
        L_0x09b2:
            java.lang.Object r3 = r2.obj     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.ln r3 = (com.google.ads.interactivemedia.v3.internal.ln) r3     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            int r4 = r2.arg1     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r4 == 0) goto L_0x09bc
            r4 = 1
            goto L_0x09bd
        L_0x09bc:
            r4 = 0
        L_0x09bd:
            int r2 = r2.arg2     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            if (r2 == 0) goto L_0x09c3
            r2 = 1
            goto L_0x09c4
        L_0x09c3:
            r2 = 0
        L_0x09c4:
            int r5 = r1.C     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r6 = 1
            int r5 = r5 + r6
            r1.C = r5     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r5 = 0
            r1.a(r5, r6, r4, r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.bw r2 = r1.e     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r2.a()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r1.u = r3     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r2 = 2
            r1.a(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.sh r4 = r1.f     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.tz r4 = r4.b()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3.a(r1, r4)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            com.google.ads.interactivemedia.v3.internal.uj r3 = r1.g     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3.a(r2)     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
        L_0x09e7:
            r36.c()     // Catch:{ aw -> 0x09ed, IOException -> 0x0a22, RuntimeException -> 0x09f4, OutOfMemoryError -> 0x09f2 }
            r3 = 1
            goto L_0x0a5d
        L_0x09ed:
            r0 = move-exception
            r3 = r0
            r2 = 0
        L_0x09f0:
            r4 = 2
            goto L_0x0a46
        L_0x09f2:
            r0 = move-exception
            goto L_0x09f5
        L_0x09f4:
            r0 = move-exception
        L_0x09f5:
            r2 = r0
            java.lang.String r3 = "ExoPlayerImplInternal"
            java.lang.String r4 = "Internal runtime error."
            com.google.ads.interactivemedia.v3.internal.uk.b(r3, r4, r2)
            boolean r3 = r2 instanceof java.lang.OutOfMemoryError
            if (r3 == 0) goto L_0x0a08
            java.lang.OutOfMemoryError r2 = (java.lang.OutOfMemoryError) r2
            com.google.ads.interactivemedia.v3.internal.aw r2 = com.google.ads.interactivemedia.v3.internal.aw.a(r2)
            goto L_0x0a0e
        L_0x0a08:
            java.lang.RuntimeException r2 = (java.lang.RuntimeException) r2
            com.google.ads.interactivemedia.v3.internal.aw r2 = com.google.ads.interactivemedia.v3.internal.aw.a(r2)
        L_0x0a0e:
            android.os.Handler r3 = r1.i
            r4 = 2
            android.os.Message r2 = r3.obtainMessage(r4, r2)
            r2.sendToTarget()
            r2 = 1
            r3 = 0
            r1.a(r2, r3, r3)
            r36.c()
            r3 = 1
            goto L_0x0a5d
        L_0x0a22:
            r0 = move-exception
            r2 = r0
            java.lang.String r3 = "ExoPlayerImplInternal"
            java.lang.String r4 = "Source error."
            com.google.ads.interactivemedia.v3.internal.uk.b(r3, r4, r2)
            android.os.Handler r3 = r1.i
            com.google.ads.interactivemedia.v3.internal.aw r2 = com.google.ads.interactivemedia.v3.internal.aw.a(r2)
            r4 = 2
            android.os.Message r2 = r3.obtainMessage(r4, r2)
            r2.sendToTarget()
            r2 = 0
            r1.a(r2, r2, r2)
            r36.c()
            r3 = 1
            goto L_0x0a5d
        L_0x0a42:
            r0 = move-exception
            r2 = 0
            r4 = 2
            r3 = r0
        L_0x0a46:
            java.lang.String r5 = "ExoPlayerImplInternal"
            java.lang.String r6 = "Playback error."
            com.google.ads.interactivemedia.v3.internal.uk.b(r5, r6, r3)
            android.os.Handler r5 = r1.i
            android.os.Message r3 = r5.obtainMessage(r4, r3)
            r3.sendToTarget()
            r3 = 1
            r1.a(r3, r2, r2)
            r36.c()
        L_0x0a5d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.bk.handleMessage(android.os.Message):boolean");
    }

    private final void a(int i2) {
        if (this.t.f != i2) {
            cb cbVar = this.t;
            cb cbVar2 = r2;
            cq cqVar = cbVar.a;
            cb cbVar3 = new cb(cqVar, cbVar.b, cbVar.c, cbVar.d, cbVar.e, i2, cbVar.g, cbVar.h, cbVar.i, cbVar.j, cbVar.k, cbVar.l, cbVar.m);
            this.t = cbVar2;
        }
    }

    private final void c(boolean z2) {
        if (this.t.g != z2) {
            cb cbVar = this.t;
            cb cbVar2 = r2;
            cq cqVar = cbVar.a;
            cb cbVar3 = new cb(cqVar, cbVar.b, cbVar.c, cbVar.d, cbVar.e, cbVar.f, z2, cbVar.h, cbVar.i, cbVar.j, cbVar.k, cbVar.l, cbVar.m);
            this.t = cbVar2;
        }
    }

    private final void c() {
        if (this.o.a(this.t)) {
            this.i.obtainMessage(0, this.o.b, this.o.c ? this.o.d : -1, this.t).sendToTarget();
            this.o.b(this.t);
        }
    }

    private final void d(boolean z2) throws aw {
        lo loVar = this.r.c().f.a;
        long a2 = a(loVar, this.t.m, true);
        if (a2 != this.t.m) {
            cb cbVar = this.t;
            this.t = cbVar.a(loVar, a2, cbVar.e, k());
            if (z2) {
                this.o.b(4);
            }
        }
    }

    private final void d() throws aw {
        this.y = false;
        this.n.a();
        for (ci g2 : this.v) {
            g2.g();
        }
    }

    private final void e() throws aw {
        this.n.b();
        for (ci a2 : this.v) {
            a(a2);
        }
    }

    private final void f() throws aw {
        if (this.r.f()) {
            bx c2 = this.r.c();
            long c3 = c2.a.c();
            if (c3 != -9223372036854775807L) {
                a(c3);
                if (c3 != this.t.m) {
                    cb cbVar = this.t;
                    this.t = cbVar.a(cbVar.c, c3, this.t.e, k());
                    this.o.b(4);
                }
            } else {
                this.E = this.n.c();
                long a2 = this.E - c2.a();
                long j2 = this.t.m;
                if (!this.p.isEmpty() && !this.t.c.a()) {
                    if (this.t.d == j2) {
                        j2--;
                    }
                    int a3 = this.t.a.a(this.t.c.a);
                    int i2 = this.F;
                    bo boVar = i2 > 0 ? (bo) this.p.get(i2 - 1) : null;
                    while (boVar != null && (boVar.b > a3 || (boVar.b == a3 && boVar.c > j2))) {
                        this.F--;
                        int i3 = this.F;
                        boVar = i3 > 0 ? (bo) this.p.get(i3 - 1) : null;
                    }
                    bo boVar2 = this.F < this.p.size() ? (bo) this.p.get(this.F) : null;
                    while (boVar2 != null && boVar2.d != null && (boVar2.b < a3 || (boVar2.b == a3 && boVar2.c <= j2))) {
                        this.F++;
                        boVar2 = this.F < this.p.size() ? (bo) this.p.get(this.F) : null;
                    }
                    while (boVar2 != null && boVar2.d != null && boVar2.b == a3 && boVar2.c > j2 && boVar2.c <= a2) {
                        c(boVar2.a);
                        if (boVar2.a.h() || boVar2.a.j()) {
                            this.p.remove(this.F);
                        } else {
                            this.F++;
                        }
                        boVar2 = this.F < this.p.size() ? (bo) this.p.get(this.F) : null;
                    }
                }
                this.t.m = a2;
            }
            this.t.k = this.r.b().d();
            this.t.l = k();
        }
    }

    private final void a(long j2, long j3) {
        this.g.b(2);
        this.g.a(2, j2 + j3);
    }

    private final long a(lo loVar, long j2) throws aw {
        return a(loVar, j2, this.r.c() != this.r.d());
    }

    private final long a(lo loVar, long j2, boolean z2) throws aw {
        e();
        this.y = false;
        a(2);
        bx c2 = this.r.c();
        bx bxVar = c2;
        while (true) {
            if (bxVar != null) {
                if (loVar.equals(bxVar.f.a) && bxVar.d) {
                    this.r.a(bxVar);
                    break;
                }
                bxVar = this.r.h();
            } else {
                break;
            }
        }
        if (c2 != bxVar || z2) {
            for (ci b2 : this.v) {
                b(b2);
            }
            this.v = new ci[0];
            c2 = null;
        }
        if (bxVar != null) {
            a(c2);
            if (bxVar.e) {
                long b3 = bxVar.a.b(j2);
                bxVar.a.a(b3 - this.l, this.m);
                j2 = b3;
            }
            a(j2);
            j();
        } else {
            this.r.b(true);
            this.t = this.t.a(mz.a, this.d);
            a(j2);
        }
        e(false);
        this.g.a(2);
        return j2;
    }

    private final void a(long j2) throws aw {
        rt[] a2;
        if (this.r.f()) {
            j2 += this.r.c().a();
        }
        this.E = j2;
        this.n.a(this.E);
        for (ci a3 : this.v) {
            a3.a(this.E);
        }
        for (bx e2 = this.r.e(); e2 != null; e2 = e2.f()) {
            sb h2 = e2.h();
            if (h2 != null) {
                for (rt rtVar : h2.c.a()) {
                    if (rtVar != null) {
                        rtVar.j();
                    }
                }
            }
        }
    }

    private final void a(boolean z2, boolean z3, boolean z4) {
        a(z2 || !this.B, true, z3, z3);
        this.o.a(this.C + (z4 ? 1 : 0));
        this.C = 0;
        this.e.b();
        a(1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void a(boolean r25, boolean r26, boolean r27, boolean r28) {
        /*
            r24 = this;
            r1 = r24
            com.google.ads.interactivemedia.v3.internal.uj r0 = r1.g
            r2 = 2
            r0.b(r2)
            r2 = 0
            r1.y = r2
            com.google.ads.interactivemedia.v3.internal.au r0 = r1.n
            r0.b()
            r3 = 0
            r1.E = r3
            com.google.ads.interactivemedia.v3.internal.ci[] r3 = r1.v
            int r4 = r3.length
            r5 = 0
        L_0x0018:
            if (r5 >= r4) goto L_0x002d
            r0 = r3[r5]
            r1.b(r0)     // Catch:{ aw -> 0x0022, RuntimeException -> 0x0020 }
            goto L_0x002a
        L_0x0020:
            r0 = move-exception
            goto L_0x0023
        L_0x0022:
            r0 = move-exception
        L_0x0023:
            java.lang.String r6 = "ExoPlayerImplInternal"
            java.lang.String r7 = "Disable failed."
            com.google.ads.interactivemedia.v3.internal.uk.b(r6, r7, r0)
        L_0x002a:
            int r5 = r5 + 1
            goto L_0x0018
        L_0x002d:
            if (r25 == 0) goto L_0x0047
            com.google.ads.interactivemedia.v3.internal.ci[] r3 = r1.a
            int r4 = r3.length
            r5 = 0
        L_0x0033:
            if (r5 >= r4) goto L_0x0047
            r0 = r3[r5]
            r0.r()     // Catch:{ RuntimeException -> 0x003b }
            goto L_0x0044
        L_0x003b:
            r0 = move-exception
            r6 = r0
            java.lang.String r0 = "ExoPlayerImplInternal"
            java.lang.String r7 = "Reset failed."
            com.google.ads.interactivemedia.v3.internal.uk.b(r0, r7, r6)
        L_0x0044:
            int r5 = r5 + 1
            goto L_0x0033
        L_0x0047:
            com.google.ads.interactivemedia.v3.internal.ci[] r0 = new com.google.ads.interactivemedia.v3.internal.ci[r2]
            r1.v = r0
            r0 = 0
            r3 = 1
            if (r27 == 0) goto L_0x0052
            r1.D = r0
            goto L_0x008b
        L_0x0052:
            if (r28 == 0) goto L_0x008b
            com.google.ads.interactivemedia.v3.internal.bq r4 = r1.D
            if (r4 != 0) goto L_0x0089
            com.google.ads.interactivemedia.v3.internal.cb r4 = r1.t
            com.google.ads.interactivemedia.v3.internal.cq r4 = r4.a
            boolean r4 = r4.a()
            if (r4 != 0) goto L_0x0089
            com.google.ads.interactivemedia.v3.internal.cb r4 = r1.t
            com.google.ads.interactivemedia.v3.internal.cq r4 = r4.a
            com.google.ads.interactivemedia.v3.internal.cb r5 = r1.t
            com.google.ads.interactivemedia.v3.internal.lo r5 = r5.c
            java.lang.Object r5 = r5.a
            com.google.ads.interactivemedia.v3.internal.cs r6 = r1.k
            r4.a(r5, r6)
            com.google.ads.interactivemedia.v3.internal.cb r4 = r1.t
            long r4 = r4.m
            com.google.ads.interactivemedia.v3.internal.cs r6 = r1.k
            long r6 = r6.b()
            long r4 = r4 + r6
            com.google.ads.interactivemedia.v3.internal.bq r6 = new com.google.ads.interactivemedia.v3.internal.bq
            com.google.ads.interactivemedia.v3.internal.cq r7 = com.google.ads.interactivemedia.v3.internal.cq.a
            com.google.ads.interactivemedia.v3.internal.cs r8 = r1.k
            int r8 = r8.b
            r6.<init>(r7, r8, r4)
            r1.D = r6
        L_0x0089:
            r4 = 1
            goto L_0x008d
        L_0x008b:
            r4 = r27
        L_0x008d:
            com.google.ads.interactivemedia.v3.internal.bz r5 = r1.r
            if (r4 != 0) goto L_0x0092
            goto L_0x0093
        L_0x0092:
            r3 = 0
        L_0x0093:
            r5.b(r3)
            r1.c(r2)
            if (r28 == 0) goto L_0x00c2
            com.google.ads.interactivemedia.v3.internal.bz r3 = r1.r
            com.google.ads.interactivemedia.v3.internal.cq r5 = com.google.ads.interactivemedia.v3.internal.cq.a
            r3.a(r5)
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.bo> r3 = r1.p
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            int r5 = r3.size()
            r6 = 0
        L_0x00ab:
            if (r6 >= r5) goto L_0x00bb
            java.lang.Object r7 = r3.get(r6)
            int r6 = r6 + 1
            com.google.ads.interactivemedia.v3.internal.bo r7 = (com.google.ads.interactivemedia.v3.internal.bo) r7
            com.google.ads.interactivemedia.v3.internal.cg r7 = r7.a
            r7.a(r2)
            goto L_0x00ab
        L_0x00bb:
            java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.bo> r3 = r1.p
            r3.clear()
            r1.F = r2
        L_0x00c2:
            if (r4 == 0) goto L_0x00d1
            com.google.ads.interactivemedia.v3.internal.cb r2 = r1.t
            boolean r3 = r1.A
            com.google.ads.interactivemedia.v3.internal.ct r5 = r1.j
            com.google.ads.interactivemedia.v3.internal.lo r2 = r2.a(r3, r5)
            r17 = r2
            goto L_0x00d7
        L_0x00d1:
            com.google.ads.interactivemedia.v3.internal.cb r2 = r1.t
            com.google.ads.interactivemedia.v3.internal.lo r2 = r2.c
            r17 = r2
        L_0x00d7:
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 == 0) goto L_0x00e1
            r22 = r2
            goto L_0x00e7
        L_0x00e1:
            com.google.ads.interactivemedia.v3.internal.cb r5 = r1.t
            long r5 = r5.m
            r22 = r5
        L_0x00e7:
            if (r4 == 0) goto L_0x00ea
            goto L_0x00ee
        L_0x00ea:
            com.google.ads.interactivemedia.v3.internal.cb r2 = r1.t
            long r2 = r2.e
        L_0x00ee:
            r11 = r2
            com.google.ads.interactivemedia.v3.internal.cb r2 = new com.google.ads.interactivemedia.v3.internal.cb
            if (r28 == 0) goto L_0x00f6
            com.google.ads.interactivemedia.v3.internal.cq r3 = com.google.ads.interactivemedia.v3.internal.cq.a
            goto L_0x00fa
        L_0x00f6:
            com.google.ads.interactivemedia.v3.internal.cb r3 = r1.t
            com.google.ads.interactivemedia.v3.internal.cq r3 = r3.a
        L_0x00fa:
            r6 = r3
            if (r28 == 0) goto L_0x00ff
            r7 = r0
            goto L_0x0104
        L_0x00ff:
            com.google.ads.interactivemedia.v3.internal.cb r3 = r1.t
            java.lang.Object r3 = r3.b
            r7 = r3
        L_0x0104:
            com.google.ads.interactivemedia.v3.internal.cb r3 = r1.t
            int r13 = r3.f
            r14 = 0
            if (r28 == 0) goto L_0x010e
            com.google.ads.interactivemedia.v3.internal.mz r3 = com.google.ads.interactivemedia.v3.internal.mz.a
            goto L_0x0112
        L_0x010e:
            com.google.ads.interactivemedia.v3.internal.cb r3 = r1.t
            com.google.ads.interactivemedia.v3.internal.mz r3 = r3.h
        L_0x0112:
            r15 = r3
            if (r28 == 0) goto L_0x0118
            com.google.ads.interactivemedia.v3.internal.sb r3 = r1.d
            goto L_0x011c
        L_0x0118:
            com.google.ads.interactivemedia.v3.internal.cb r3 = r1.t
            com.google.ads.interactivemedia.v3.internal.sb r3 = r3.i
        L_0x011c:
            r16 = r3
            r20 = 0
            r5 = r2
            r8 = r17
            r9 = r22
            r18 = r22
            r5.<init>(r6, r7, r8, r9, r11, r13, r14, r15, r16, r17, r18, r20, r22)
            r1.t = r2
            if (r26 == 0) goto L_0x0137
            com.google.ads.interactivemedia.v3.internal.ln r2 = r1.u
            if (r2 == 0) goto L_0x0137
            r2.a(r1)
            r1.u = r0
        L_0x0137:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.bk.a(boolean, boolean, boolean, boolean):void");
    }

    private final void c(cg cgVar) throws aw {
        if (cgVar.e().getLooper() == this.g.a()) {
            d(cgVar);
            if (this.t.f == 3 || this.t.f == 2) {
                this.g.a(2);
            }
        } else {
            this.g.a(16, (Object) cgVar).sendToTarget();
        }
    }

    private static void d(cg cgVar) throws aw {
        if (!cgVar.j()) {
            try {
                cgVar.b().a(cgVar.c(), cgVar.d());
            } finally {
                cgVar.a(true);
            }
        }
    }

    private final boolean a(bo boVar) {
        if (boVar.d == null) {
            Pair a2 = a(new bq(boVar.a.a(), boVar.a.g(), at.b(boVar.a.f())), false);
            if (a2 == null) {
                return false;
            }
            int a3 = this.t.a.a(a2.first);
            long longValue = ((Long) a2.second).longValue();
            Object obj = a2.first;
            boVar.b = a3;
            boVar.c = longValue;
            boVar.d = obj;
        } else {
            int a4 = this.t.a.a(boVar.d);
            if (a4 == -1) {
                return false;
            }
            boVar.b = a4;
        }
        return true;
    }

    private static void a(ci ciVar) throws aw {
        if (ciVar.f() == 2) {
            ciVar.p();
        }
    }

    private final void b(ci ciVar) throws aw {
        this.n.b(ciVar);
        a(ciVar);
        ciVar.q();
    }

    private final boolean g() {
        bx c2 = this.r.c();
        bx f2 = c2.f();
        long j2 = c2.f.e;
        return j2 == -9223372036854775807L || this.t.m < j2 || (f2 != null && (f2.d || f2.f.a.a()));
    }

    private final void h() throws IOException {
        bx b2 = this.r.b();
        bx d2 = this.r.d();
        if (b2 != null && !b2.d && (d2 == null || d2.f() == b2)) {
            ci[] ciVarArr = this.v;
            int length = ciVarArr.length;
            int i2 = 0;
            while (i2 < length) {
                if (ciVarArr[i2].i()) {
                    i2++;
                } else {
                    return;
                }
            }
            b2.a.a_();
        }
    }

    private final void i() {
        a(4);
        a(false, false, true, false);
    }

    private final Object a(Object obj, cq cqVar, cq cqVar2) {
        int a2 = cqVar.a(obj);
        int d2 = cqVar.d();
        int i2 = a2;
        int i3 = -1;
        for (int i4 = 0; i4 < d2 && i3 == -1; i4++) {
            i2 = cqVar.a(i2, this.k, this.j, this.z, this.A);
            if (i2 == -1) {
                break;
            }
            i3 = cqVar2.a(cqVar.a(i2));
        }
        if (i3 == -1) {
            return null;
        }
        return cqVar2.a(i3);
    }

    private final Pair<Object, Long> a(bq bqVar, boolean z2) {
        cq cqVar = this.t.a;
        cq cqVar2 = bqVar.a;
        if (cqVar.a()) {
            return null;
        }
        if (cqVar2.a()) {
            cqVar2 = cqVar;
        }
        try {
            Pair<Object, Long> a2 = cqVar2.a(this.j, this.k, bqVar.b, bqVar.c);
            if (cqVar == cqVar2) {
                return a2;
            }
            int a3 = cqVar.a(a2.first);
            if (a3 != -1) {
                return a2;
            }
            if (!z2 || a(a2.first, cqVar2, cqVar) == null) {
                return null;
            }
            return b(cqVar, cqVar.a(a3, this.k, false).b, -9223372036854775807L);
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }

    private final Pair<Object, Long> b(cq cqVar, int i2, long j2) {
        return cqVar.a(this.j, this.k, i2, -9223372036854775807L);
    }

    private final void j() {
        bx b2 = this.r.b();
        long e2 = !b2.d ? 0 : b2.a.e();
        if (e2 == Long.MIN_VALUE) {
            c(false);
            return;
        }
        boolean a2 = this.e.a(b(e2), this.n.e().b);
        c(a2);
        if (a2) {
            b2.d(this.E);
        }
    }

    private final void a(bx bxVar) throws aw {
        bx c2 = this.r.c();
        if (c2 != null && bxVar != c2) {
            boolean[] zArr = new boolean[this.a.length];
            int i2 = 0;
            int i3 = 0;
            while (true) {
                ci[] ciVarArr = this.a;
                if (i2 < ciVarArr.length) {
                    ci ciVar = ciVarArr[i2];
                    zArr[i2] = ciVar.f() != 0;
                    if (c2.h().a(i2)) {
                        i3++;
                    }
                    if (zArr[i2] && (!c2.h().a(i2) || (ciVar.l() && ciVar.h() == bxVar.c[i2]))) {
                        b(ciVar);
                    }
                    i2++;
                } else {
                    this.t = this.t.a(c2.g(), c2.h());
                    a(zArr, i3);
                    return;
                }
            }
        }
    }

    private final void a(boolean[] zArr, int i2) throws aw {
        this.v = new ci[i2];
        sb h2 = this.r.c().h();
        for (int i3 = 0; i3 < this.a.length; i3++) {
            if (!h2.a(i3)) {
                this.a[i3].r();
            }
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.a.length; i5++) {
            if (h2.a(i5)) {
                boolean z2 = zArr[i5];
                int i6 = i4 + 1;
                bx c2 = this.r.c();
                ci ciVar = this.a[i5];
                this.v[i4] = ciVar;
                if (ciVar.f() == 0) {
                    sb h3 = c2.h();
                    ck ckVar = h3.b[i5];
                    bs[] a2 = a(h3.c.a(i5));
                    boolean z3 = this.x && this.t.f == 3;
                    ciVar.a(ckVar, a2, c2.c[i5], this.E, !z2 && z3, c2.a());
                    this.n.a(ciVar);
                    if (z3) {
                        ciVar.g();
                    }
                }
                i4 = i6;
            }
        }
    }

    private final void e(boolean z2) {
        bx bxVar;
        boolean z3;
        long j2;
        bk bkVar = this;
        bx b2 = bkVar.r.b();
        lo loVar = b2 == null ? bkVar.t.c : b2.f.a;
        boolean z4 = !bkVar.t.j.equals(loVar);
        if (z4) {
            cb cbVar = bkVar.t;
            z3 = z4;
            bxVar = b2;
            cb cbVar2 = cbVar;
            cb cbVar3 = r3;
            cb cbVar4 = new cb(cbVar.a, cbVar.b, cbVar.c, cbVar.d, cbVar.e, cbVar.f, cbVar.g, cbVar.h, cbVar.i, loVar, cbVar2.k, cbVar2.l, cbVar2.m);
            bkVar = this;
            bkVar.t = cbVar3;
        } else {
            bxVar = b2;
            z3 = z4;
        }
        cb cbVar5 = bkVar.t;
        if (bxVar == null) {
            j2 = cbVar5.m;
        } else {
            j2 = bxVar.d();
        }
        cbVar5.k = j2;
        bkVar.t.l = k();
        if ((z3 || z2) && bxVar != null) {
            bx bxVar2 = bxVar;
            if (bxVar2.d) {
                bkVar.a(bxVar2.g(), bxVar2.h());
            }
        }
    }

    private final long k() {
        return b(this.t.k);
    }

    private final long b(long j2) {
        bx b2 = this.r.b();
        if (b2 == null) {
            return 0;
        }
        return j2 - (this.E - b2.a());
    }

    private final void a(mz mzVar, sb sbVar) {
        this.e.a(this.a, sbVar.c);
    }

    private static bs[] a(rt rtVar) {
        int g2 = rtVar != null ? rtVar.g() : 0;
        bs[] bsVarArr = new bs[g2];
        for (int i2 = 0; i2 < g2; i2++) {
            bsVarArr[i2] = rtVar.a(i2);
        }
        return bsVarArr;
    }

    public final /* synthetic */ void a(mu muVar) {
        this.g.a(10, (Object) (ll) muVar).sendToTarget();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(cg cgVar) {
        try {
            d(cgVar);
        } catch (aw e2) {
            uk.b("ExoPlayerImplInternal", "Unexpected error delivering message on external thread.", e2);
            throw new RuntimeException(e2);
        }
    }
}

package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import java.io.IOException;

final class zzfn implements Callback, zzln, zzlp, zzoq {
    private final Handler handler;
    private int repeatMode = 0;
    private int state;
    private final zzfz[] zzwu;
    private final zzop zzwv;
    private final Handler zzwx;
    private final zzgf zzxa;
    private final zzge zzxb;
    private boolean zzxd;
    private boolean zzxh;
    private zzgc zzxi;
    private zzfy zzxm;
    private zzfp zzxn;
    private final zzga[] zzxs;
    private final zzfw zzxt;
    private final zzqa zzxu;
    private final HandlerThread zzxv;
    private final zzfg zzxw;
    private zzfz zzxx;
    private zzps zzxy;
    private zzlo zzxz;
    private zzfz[] zzya;
    private boolean zzyb;
    private boolean zzyc;
    private int zzyd;
    private int zzye;
    private long zzyf;
    private int zzyg;
    private zzfq zzyh;
    private long zzyi;
    private zzfo zzyj;
    private zzfo zzyk;
    private zzfo zzyl;

    public zzfn(zzfz[] zzfzArr, zzop zzop, zzfw zzfw, boolean z, int i, Handler handler2, zzfp zzfp, zzfg zzfg) {
        this.zzwu = zzfzArr;
        this.zzwv = zzop;
        this.zzxt = zzfw;
        this.zzxd = z;
        this.zzwx = handler2;
        this.state = 1;
        this.zzxn = zzfp;
        this.zzxw = zzfg;
        this.zzxs = new zzga[zzfzArr.length];
        for (int i2 = 0; i2 < zzfzArr.length; i2++) {
            zzfzArr[i2].setIndex(i2);
            this.zzxs[i2] = zzfzArr[i2].zzbe();
        }
        this.zzxu = new zzqa();
        this.zzya = new zzfz[0];
        this.zzxa = new zzgf();
        this.zzxb = new zzge();
        zzop.zza(this);
        this.zzxm = zzfy.zzaaf;
        this.zzxv = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.zzxv.start();
        this.handler = new Handler(this.zzxv.getLooper(), this);
    }

    public final void zza(zzlo zzlo, boolean z) {
        this.handler.obtainMessage(0, 1, 0, zzlo).sendToTarget();
    }

    public final void zzc(boolean z) {
        this.handler.obtainMessage(1, z ? 1 : 0, 0).sendToTarget();
    }

    public final void zza(zzgc zzgc, int i, long j) {
        this.handler.obtainMessage(3, new zzfq(zzgc, i, j)).sendToTarget();
    }

    public final void stop() {
        this.handler.sendEmptyMessage(5);
    }

    public final void zza(zzfj... zzfjArr) {
        if (this.zzyb) {
            Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
            return;
        }
        this.zzyd++;
        this.handler.obtainMessage(11, zzfjArr).sendToTarget();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:11|12|13|14|23|20|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x001f, code lost:
        continue;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0027 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzb(com.google.android.gms.internal.ads.zzfj... r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.zzyb     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x000e
            java.lang.String r4 = "ExoPlayerImplInternal"
            java.lang.String r0 = "Ignoring messages sent after release."
            android.util.Log.w(r4, r0)     // Catch:{ all -> 0x0031 }
            monitor-exit(r3)
            return
        L_0x000e:
            int r0 = r3.zzyd     // Catch:{ all -> 0x0031 }
            int r1 = r0 + 1
            r3.zzyd = r1     // Catch:{ all -> 0x0031 }
            android.os.Handler r1 = r3.handler     // Catch:{ all -> 0x0031 }
            r2 = 11
            android.os.Message r4 = r1.obtainMessage(r2, r4)     // Catch:{ all -> 0x0031 }
            r4.sendToTarget()     // Catch:{ all -> 0x0031 }
        L_0x001f:
            int r4 = r3.zzye     // Catch:{ all -> 0x0031 }
            if (r4 > r0) goto L_0x002f
            r3.wait()     // Catch:{ InterruptedException -> 0x0027 }
            goto L_0x001f
        L_0x0027:
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0031 }
            r4.interrupt()     // Catch:{ all -> 0x0031 }
            goto L_0x001f
        L_0x002f:
            monitor-exit(r3)
            return
        L_0x0031:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfn.zzb(com.google.android.gms.internal.ads.zzfj[]):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:10|11|12|13|23|20|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x000d, code lost:
        continue;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void release() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.zzyb     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            android.os.Handler r0 = r2.handler     // Catch:{ all -> 0x0024 }
            r1 = 6
            r0.sendEmptyMessage(r1)     // Catch:{ all -> 0x0024 }
        L_0x000d:
            boolean r0 = r2.zzyb     // Catch:{ all -> 0x0024 }
            if (r0 != 0) goto L_0x001d
            r2.wait()     // Catch:{ InterruptedException -> 0x0015 }
            goto L_0x000d
        L_0x0015:
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0024 }
            r0.interrupt()     // Catch:{ all -> 0x0024 }
            goto L_0x000d
        L_0x001d:
            android.os.HandlerThread r0 = r2.zzxv     // Catch:{ all -> 0x0024 }
            r0.quit()     // Catch:{ all -> 0x0024 }
            monitor-exit(r2)
            return
        L_0x0024:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfn.release():void");
    }

    public final void zzb(zzgc zzgc, Object obj) {
        this.handler.obtainMessage(7, Pair.create(zzgc, obj)).sendToTarget();
    }

    public final void zza(zzlm zzlm) {
        this.handler.obtainMessage(8, zzlm).sendToTarget();
    }

    public final void zzbu() {
        this.handler.sendEmptyMessage(10);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:216:0x038c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:?, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x0454, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x0455, code lost:
        r2 = r0;
        r8.zzxn = new com.google.android.gms.internal.ads.zzfp(r3, r4);
        r3 = r8.zzwx;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x045f, code lost:
        if (r1 != false) goto L_0x0461;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x0461, code lost:
        r1 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:0x0463, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x0464, code lost:
        r3.obtainMessage(4, r1, 0, r8.zzxn).sendToTarget();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:0x046d, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:500:0x08c3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:501:0x08c4, code lost:
        r1 = r0;
        r3 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:502:0x08c8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:503:0x08c9, code lost:
        r1 = r0;
        r3 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:504:0x08cd, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:505:0x08ce, code lost:
        r1 = r0;
        android.util.Log.e("ExoPlayerImplInternal", "Internal runtime error.", r1);
        r8.zzwx.obtainMessage(8, com.google.android.gms.internal.ads.zzff.zza(r1)).sendToTarget();
        zzby();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:506:0x08e8, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00c8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00cc, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00cd, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00ce, code lost:
        monitor-enter(r36);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        r8.zzye++;
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00d9, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x028f A[Catch:{ all -> 0x0454, all -> 0x038c, all -> 0x00cc, all -> 0x00d9, all -> 0x00c8, zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0292 A[Catch:{ all -> 0x0454, all -> 0x038c, all -> 0x00cc, all -> 0x00d9, all -> 0x00c8, zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0296 A[Catch:{ all -> 0x0454, all -> 0x038c, all -> 0x00cc, all -> 0x00d9, all -> 0x00c8, zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x0358 A[Catch:{ all -> 0x0454, all -> 0x038c, all -> 0x00cc, all -> 0x00d9, all -> 0x00c8, zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x036c A[Catch:{ all -> 0x0454, all -> 0x038c, all -> 0x00cc, all -> 0x00d9, all -> 0x00c8, zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x05bf A[Catch:{ all -> 0x0454, all -> 0x038c, all -> 0x00cc, all -> 0x00d9, all -> 0x00c8, zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x05db A[Catch:{ all -> 0x0454, all -> 0x038c, all -> 0x00cc, all -> 0x00d9, all -> 0x00c8, zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }, LOOP:8: B:331:0x05db->B:335:0x05ed, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:405:0x073d A[Catch:{ all -> 0x0454, all -> 0x038c, all -> 0x00cc, all -> 0x00d9, all -> 0x00c8, zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:444:0x07f6 A[Catch:{ all -> 0x0454, all -> 0x038c, all -> 0x00cc, all -> 0x00d9, all -> 0x00c8, zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }] */
    /* JADX WARNING: Removed duplicated region for block: B:504:0x08cd A[ExcHandler: RuntimeException (r0v2 'e' java.lang.RuntimeException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleMessage(android.os.Message r37) {
        /*
            r36 = this;
            r8 = r36
            r1 = r37
            r10 = 1
            int r2 = r1.what     // Catch:{ zzff -> 0x0905, IOException -> 0x08e9, RuntimeException -> 0x08cd }
            r11 = 7
            r3 = 0
            r14 = 3
            r5 = -1
            r6 = 0
            r15 = 4
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r7 = 2
            r9 = 0
            switch(r2) {
                case 0: goto L_0x088c;
                case 1: goto L_0x085f;
                case 2: goto L_0x046e;
                case 3: goto L_0x03b5;
                case 4: goto L_0x0394;
                case 5: goto L_0x0390;
                case 6: goto L_0x0379;
                case 7: goto L_0x021b;
                case 8: goto L_0x01e5;
                case 9: goto L_0x01d2;
                case 10: goto L_0x00dd;
                case 11: goto L_0x009f;
                case 12: goto L_0x0019;
                default: goto L_0x0018;
            }
        L_0x0018:
            return r9
        L_0x0019:
            int r1 = r1.arg1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.repeatMode = r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 == 0) goto L_0x0024
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x0026
        L_0x0024:
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0026:
            if (r2 == 0) goto L_0x009e
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != r3) goto L_0x002e
            r3 = 1
            goto L_0x002f
        L_0x002e:
            r3 = 0
        L_0x002f:
            com.google.android.gms.internal.ads.zzfo r4 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != r4) goto L_0x0037
            r4 = r3
            r3 = r2
            r2 = 1
            goto L_0x003a
        L_0x0037:
            r4 = r3
            r3 = r2
            r2 = 0
        L_0x003a:
            com.google.android.gms.internal.ads.zzgc r11 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r12 = r3.zzyr     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r13 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgf r14 = r8.zzxa     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r11 = r11.zza(r12, r13, r14, r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r12 = r3.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r12 == 0) goto L_0x0065
            if (r11 == r5) goto L_0x0065
            com.google.android.gms.internal.ads.zzfo r12 = r3.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r12 = r12.zzyr     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r12 != r11) goto L_0x0065
            com.google.android.gms.internal.ads.zzfo r3 = r3.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r11 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r3 != r11) goto L_0x005a
            r11 = 1
            goto L_0x005b
        L_0x005a:
            r11 = 0
        L_0x005b:
            r4 = r4 | r11
            com.google.android.gms.internal.ads.zzfo r11 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r3 != r11) goto L_0x0062
            r11 = 1
            goto L_0x0063
        L_0x0062:
            r11 = 0
        L_0x0063:
            r2 = r2 | r11
            goto L_0x003a
        L_0x0065:
            com.google.android.gms.internal.ads.zzfo r5 = r3.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r5 == 0) goto L_0x0070
            com.google.android.gms.internal.ads.zzfo r5 = r3.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            zza(r5)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r3.zzyw = r6     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0070:
            int r5 = r3.zzyr     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r5 = r8.zzi(r5)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r3.zzyt = r5     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != 0) goto L_0x007c
            r8.zzyj = r3     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x007c:
            if (r4 != 0) goto L_0x0095
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 == 0) goto L_0x0095
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r2.zzyr     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfp r3 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r3 = r3.zzyz     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r3 = r8.zza(r2, r3)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfp r5 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r5.<init>(r2, r3)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxn = r5     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0095:
            int r2 = r8.state     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != r15) goto L_0x009e
            if (r1 == 0) goto L_0x009e
            r8.setState(r7)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x009e:
            return r10
        L_0x009f:
            java.lang.Object r1 = r1.obj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfj[] r1 = (com.google.android.gms.internal.ads.zzfj[]) r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r1.length     // Catch:{ all -> 0x00cc }
        L_0x00a4:
            if (r9 >= r2) goto L_0x00b4
            r3 = r1[r9]     // Catch:{ all -> 0x00cc }
            com.google.android.gms.internal.ads.zzfi r4 = r3.zzwr     // Catch:{ all -> 0x00cc }
            int r5 = r3.zzws     // Catch:{ all -> 0x00cc }
            java.lang.Object r3 = r3.zzwt     // Catch:{ all -> 0x00cc }
            r4.zza(r5, r3)     // Catch:{ all -> 0x00cc }
            int r9 = r9 + 1
            goto L_0x00a4
        L_0x00b4:
            com.google.android.gms.internal.ads.zzlo r1 = r8.zzxz     // Catch:{ all -> 0x00cc }
            if (r1 == 0) goto L_0x00bd
            android.os.Handler r1 = r8.handler     // Catch:{ all -> 0x00cc }
            r1.sendEmptyMessage(r7)     // Catch:{ all -> 0x00cc }
        L_0x00bd:
            monitor-enter(r36)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r1 = r8.zzye     // Catch:{ all -> 0x00c8 }
            int r1 = r1 + r10
            r8.zzye = r1     // Catch:{ all -> 0x00c8 }
            r36.notifyAll()     // Catch:{ all -> 0x00c8 }
            monitor-exit(r36)     // Catch:{ all -> 0x00c8 }
            return r10
        L_0x00c8:
            r0 = move-exception
            r1 = r0
            monitor-exit(r36)     // Catch:{ all -> 0x00c8 }
            throw r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x00cc:
            r0 = move-exception
            r1 = r0
            monitor-enter(r36)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r8.zzye     // Catch:{ all -> 0x00d9 }
            int r2 = r2 + r10
            r8.zzye = r2     // Catch:{ all -> 0x00d9 }
            r36.notifyAll()     // Catch:{ all -> 0x00d9 }
            monitor-exit(r36)     // Catch:{ all -> 0x00d9 }
            throw r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x00d9:
            r0 = move-exception
            r1 = r0
            monitor-exit(r36)     // Catch:{ all -> 0x00d9 }
            throw r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x00dd:
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x01d1
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2 = 1
        L_0x00e4:
            if (r1 == 0) goto L_0x01d1
            boolean r3 = r1.zzyu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r3 != 0) goto L_0x00ec
            goto L_0x01d1
        L_0x00ec:
            boolean r3 = r1.zzcd()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r3 != 0) goto L_0x00fa
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 != r3) goto L_0x00f7
            r2 = 0
        L_0x00f7:
            com.google.android.gms.internal.ads.zzfo r1 = r1.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x00e4
        L_0x00fa:
            if (r2 == 0) goto L_0x0198
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 == r3) goto L_0x0104
            r2 = 1
            goto L_0x0105
        L_0x0104:
            r2 = 0
        L_0x0105:
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r3.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            zza(r3)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r3.zzyw = r6     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzyj = r3     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzyk = r3     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfz[] r3 = r8.zzwu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r3 = r3.length     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean[] r3 = new boolean[r3]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r4 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfp r5 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r11 = r5.zzyz     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r4 = r4.zza(r11, r2, r3)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfp r2 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r11 = r2.zzyz     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r2 == 0) goto L_0x0136
            com.google.android.gms.internal.ads.zzfp r2 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2.zzyz = r4     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzh(r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0136:
            com.google.android.gms.internal.ads.zzfz[] r2 = r8.zzwu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r2.length     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean[] r2 = new boolean[r2]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r4 = 0
            r5 = 0
        L_0x013d:
            com.google.android.gms.internal.ads.zzfz[] r11 = r8.zzwu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r11 = r11.length     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r4 >= r11) goto L_0x0189
            com.google.android.gms.internal.ads.zzfz[] r11 = r8.zzwu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r11 = r11[r4]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r12 = r11.getState()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r12 == 0) goto L_0x014e
            r12 = 1
            goto L_0x014f
        L_0x014e:
            r12 = 0
        L_0x014f:
            r2[r4] = r12     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r12 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlv[] r12 = r12.zzyo     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r12 = r12[r4]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r12 == 0) goto L_0x015b
            int r5 = r5 + 1
        L_0x015b:
            boolean r13 = r2[r4]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r13 == 0) goto L_0x0186
            com.google.android.gms.internal.ads.zzlv r13 = r11.zzbg()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r12 == r13) goto L_0x017d
            com.google.android.gms.internal.ads.zzfz r13 = r8.zzxx     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r11 != r13) goto L_0x0176
            if (r12 != 0) goto L_0x0172
            com.google.android.gms.internal.ads.zzqa r12 = r8.zzxu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzps r13 = r8.zzxy     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r12.zza(r13)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0172:
            r8.zzxy = r6     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxx = r6     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0176:
            zza(r11)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r11.disable()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x0186
        L_0x017d:
            boolean r12 = r3[r4]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r12 == 0) goto L_0x0186
            long r12 = r8.zzyi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r11.zzd(r12)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0186:
            int r4 = r4 + 1
            goto L_0x013d
        L_0x0189:
            android.os.Handler r3 = r8.zzwx     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzor r1 = r1.zzyx     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Message r1 = r3.obtainMessage(r14, r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.sendToTarget()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zza(r2, r5)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x01c6
        L_0x0198:
            r8.zzyj = r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r1 = r1.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x019e:
            if (r1 == 0) goto L_0x01a6
            r1.release()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r1 = r1.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x019e
        L_0x01a6:
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.zzyw = r6     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r1 = r1.zzyu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x01c6
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r1 = r1.zzys     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r4 = r8.zzyi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r11 = r3.zzcb()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r4 = r4 - r11
            long r1 = java.lang.Math.max(r1, r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r3.zzb(r1, r9)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x01c6:
            r36.zzca()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r36.zzbx()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Handler r1 = r8.handler     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.sendEmptyMessage(r7)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x01d1:
            return r10
        L_0x01d2:
            java.lang.Object r1 = r1.obj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlm r1 = (com.google.android.gms.internal.ads.zzlm) r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 == 0) goto L_0x01e4
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlm r2 = r2.zzym     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 == r1) goto L_0x01e1
            goto L_0x01e4
        L_0x01e1:
            r36.zzca()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x01e4:
            return r10
        L_0x01e5:
            java.lang.Object r1 = r1.obj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlm r1 = (com.google.android.gms.internal.ads.zzlm) r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 == 0) goto L_0x021a
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlm r2 = r2.zzym     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 == r1) goto L_0x01f4
            goto L_0x021a
        L_0x01f4:
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.zzyu = r10     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.zzcd()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r2 = r1.zzys     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r2 = r1.zzb(r2, r9)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.zzys = r2     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 != 0) goto L_0x0217
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzyk = r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r1 = r1.zzys     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzh(r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzb(r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0217:
            r36.zzca()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x021a:
            return r10
        L_0x021b:
            java.lang.Object r1 = r1.obj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.util.Pair r1 = (android.util.Pair) r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgc r2 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r3 = r1.first     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgc r3 = (com.google.android.gms.internal.ads.zzgc) r3     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxi = r3     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r1 = r1.second     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != 0) goto L_0x028a
            int r3 = r8.zzyg     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r3 <= 0) goto L_0x025a
            com.google.android.gms.internal.ads.zzfq r3 = r8.zzyh     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.util.Pair r3 = r8.zza(r3)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r4 = r8.zzyg     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzyg = r9     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzyh = r6     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r3 != 0) goto L_0x0242
            r8.zza(r1, r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x0378
        L_0x0242:
            com.google.android.gms.internal.ads.zzfp r7 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r11 = r3.first     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Integer r11 = (java.lang.Integer) r11     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r11 = r11.intValue()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r3 = r3.second     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r14 = r3.longValue()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r7.<init>(r11, r14)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxn = r7     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x028b
        L_0x025a:
            com.google.android.gms.internal.ads.zzfp r3 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r3 = r3.zzys     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r7 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r7 != 0) goto L_0x028a
            com.google.android.gms.internal.ads.zzgc r3 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r3 = r3.isEmpty()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r3 == 0) goto L_0x026f
            r8.zza(r1, r9)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x0378
        L_0x026f:
            android.util.Pair r3 = r8.zzb(r9, r12)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfp r4 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r7 = r3.first     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r7 = r7.intValue()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r3 = r3.second     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r14 = r3.longValue()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r4.<init>(r7, r14)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxn = r4     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x028a:
            r4 = 0
        L_0x028b:
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r3 == 0) goto L_0x0292
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x0294
        L_0x0292:
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0294:
            if (r3 == 0) goto L_0x0375
            com.google.android.gms.internal.ads.zzgc r7 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r11 = r3.zzyn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r7 = r7.zzc(r11)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r7 != r5) goto L_0x02f8
            int r6 = r3.zzyr     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgc r7 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r8.zza(r6, r2, r7)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != r5) goto L_0x02af
            r8.zza(r1, r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x0378
        L_0x02af:
            com.google.android.gms.internal.ads.zzgc r6 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r7 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r6.zza(r2, r7, r9)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.util.Pair r2 = r8.zzb(r9, r12)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r6 = r2.first     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r6 = r6.intValue()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r2 = r2.second     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r11 = r2.longValue()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgc r2 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r7 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2.zza(r6, r7, r10)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r2 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r2 = r2.zzyn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r3.zzyr = r5     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x02d7:
            com.google.android.gms.internal.ads.zzfo r7 = r3.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r7 == 0) goto L_0x02eb
            com.google.android.gms.internal.ads.zzfo r3 = r3.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r7 = r3.zzyn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r7 = r7.equals(r2)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r7 == 0) goto L_0x02e7
            r7 = r6
            goto L_0x02e8
        L_0x02e7:
            r7 = -1
        L_0x02e8:
            r3.zzyr = r7     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x02d7
        L_0x02eb:
            long r2 = r8.zza(r6, r11)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfp r5 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r5.<init>(r6, r2)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxn = r5     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x0375
        L_0x02f8:
            boolean r2 = r8.zzi(r7)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r3.zzc(r7, r2)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r3 != r2) goto L_0x0305
            r2 = 1
            goto L_0x0306
        L_0x0305:
            r2 = 0
        L_0x0306:
            com.google.android.gms.internal.ads.zzfp r11 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r11 = r11.zzyr     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r7 == r11) goto L_0x031f
            com.google.android.gms.internal.ads.zzfp r11 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfp r12 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r13 = r11.zzys     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r12.<init>(r7, r13)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r13 = r11.zzyz     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r12.zzyz = r13     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r13 = r11.zzza     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r12.zzza = r13     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxn = r12     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x031f:
            com.google.android.gms.internal.ads.zzfo r11 = r3.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r11 == 0) goto L_0x0375
            com.google.android.gms.internal.ads.zzfo r11 = r3.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgc r12 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r13 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgf r14 = r8.zzxa     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r15 = r8.repeatMode     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r7 = r12.zza(r7, r13, r14, r15)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r7 == r5) goto L_0x0356
            java.lang.Object r12 = r11.zzyn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgc r13 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r14 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r13 = r13.zza(r7, r14, r10)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r13 = r13.zzyn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r12 = r12.equals(r13)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r12 == 0) goto L_0x0356
            boolean r3 = r8.zzi(r7)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r11.zzc(r7, r3)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r11 != r3) goto L_0x0352
            r3 = 1
            goto L_0x0353
        L_0x0352:
            r3 = 0
        L_0x0353:
            r2 = r2 | r3
            r3 = r11
            goto L_0x031f
        L_0x0356:
            if (r2 != 0) goto L_0x036c
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r2.zzyr     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfp r3 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r5 = r3.zzyz     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r5 = r8.zza(r2, r5)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfp r3 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r3.<init>(r2, r5)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxn = r3     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x0375
        L_0x036c:
            r8.zzyj = r3     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2.zzyw = r6     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            zza(r11)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0375:
            r8.zzb(r1, r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0378:
            return r10
        L_0x0379:
            r8.zzf(r10)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfw r1 = r8.zzxt     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.zzch()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.setState(r10)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            monitor-enter(r36)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzyb = r10     // Catch:{ all -> 0x038c }
            r36.notifyAll()     // Catch:{ all -> 0x038c }
            monitor-exit(r36)     // Catch:{ all -> 0x038c }
            return r10
        L_0x038c:
            r0 = move-exception
            r1 = r0
            monitor-exit(r36)     // Catch:{ all -> 0x038c }
            throw r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0390:
            r36.zzby()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            return r10
        L_0x0394:
            java.lang.Object r1 = r1.obj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfy r1 = (com.google.android.gms.internal.ads.zzfy) r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzps r2 = r8.zzxy     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 == 0) goto L_0x03a3
            com.google.android.gms.internal.ads.zzps r2 = r8.zzxy     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfy r1 = r2.zzb(r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x03a9
        L_0x03a3:
            com.google.android.gms.internal.ads.zzqa r2 = r8.zzxu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfy r1 = r2.zzb(r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x03a9:
            r8.zzxm = r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Handler r2 = r8.zzwx     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Message r1 = r2.obtainMessage(r11, r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.sendToTarget()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            return r10
        L_0x03b5:
            java.lang.Object r1 = r1.obj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfq r1 = (com.google.android.gms.internal.ads.zzfq) r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgc r2 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != 0) goto L_0x03c6
            int r2 = r8.zzyg     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r2 + r10
            r8.zzyg = r2     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzyh = r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x0453
        L_0x03c6:
            android.util.Pair r2 = r8.zza(r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != 0) goto L_0x03ec
            com.google.android.gms.internal.ads.zzfp r1 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.<init>(r9, r3)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxn = r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Handler r1 = r8.zzwx     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfp r2 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Message r1 = r1.obtainMessage(r15, r10, r9, r2)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.sendToTarget()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfp r1 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.<init>(r9, r12)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxn = r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.setState(r15)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzf(r9)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x0453
        L_0x03ec:
            long r3 = r1.zzzc     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r1 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r1 != 0) goto L_0x03f4
            r1 = 1
            goto L_0x03f5
        L_0x03f4:
            r1 = 0
        L_0x03f5:
            java.lang.Object r3 = r2.first     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r3 = r3.intValue()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r2 = r2.second     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r4 = r2.longValue()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfp r2 = r8.zzxn     // Catch:{ all -> 0x0454 }
            int r2 = r2.zzyr     // Catch:{ all -> 0x0454 }
            if (r3 != r2) goto L_0x0430
            r6 = 1000(0x3e8, double:4.94E-321)
            long r11 = r4 / r6
            com.google.android.gms.internal.ads.zzfp r2 = r8.zzxn     // Catch:{ all -> 0x0454 }
            long r13 = r2.zzyz     // Catch:{ all -> 0x0454 }
            long r13 = r13 / r6
            int r2 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r2 != 0) goto L_0x0430
            com.google.android.gms.internal.ads.zzfp r2 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2.<init>(r3, r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxn = r2     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Handler r2 = r8.zzwx     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x0425
            r1 = 1
            goto L_0x0426
        L_0x0425:
            r1 = 0
        L_0x0426:
            com.google.android.gms.internal.ads.zzfp r3 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Message r1 = r2.obtainMessage(r15, r1, r9, r3)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.sendToTarget()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x0453
        L_0x0430:
            long r6 = r8.zza(r3, r4)     // Catch:{ all -> 0x0454 }
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x043a
            r2 = 1
            goto L_0x043b
        L_0x043a:
            r2 = 0
        L_0x043b:
            r1 = r1 | r2
            com.google.android.gms.internal.ads.zzfp r2 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2.<init>(r3, r6)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxn = r2     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Handler r2 = r8.zzwx     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x0449
            r1 = 1
            goto L_0x044a
        L_0x0449:
            r1 = 0
        L_0x044a:
            com.google.android.gms.internal.ads.zzfp r3 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Message r1 = r2.obtainMessage(r15, r1, r9, r3)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.sendToTarget()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0453:
            return r10
        L_0x0454:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.internal.ads.zzfp r6 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r6.<init>(r3, r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxn = r6     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Handler r3 = r8.zzwx     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x0463
            r1 = 1
            goto L_0x0464
        L_0x0463:
            r1 = 0
        L_0x0464:
            com.google.android.gms.internal.ads.zzfp r4 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Message r1 = r3.obtainMessage(r15, r1, r9, r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.sendToTarget()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            throw r2     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x046e:
            long r5 = android.os.SystemClock.elapsedRealtime()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgc r1 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 != 0) goto L_0x047e
            com.google.android.gms.internal.ads.zzlo r1 = r8.zzxz     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.zzfg()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r14 = r5
            goto L_0x06ee
        L_0x047e:
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 != 0) goto L_0x0487
            com.google.android.gms.internal.ads.zzfp r1 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r1 = r1.zzyr     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x04cb
        L_0x0487:
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r1 = r1.zzyr     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r2 = r2.zzyt     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != 0) goto L_0x05ba
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r2 = r2.zzcc()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 == 0) goto L_0x05ba
            com.google.android.gms.internal.ads.zzgc r2 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r7 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r2 = r2.zza(r1, r7, r9)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r14 = r2.zzaan     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r2 != 0) goto L_0x04aa
            r14 = r5
            goto L_0x05bb
        L_0x04aa:
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 == 0) goto L_0x04bf
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r2.index     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r7 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r7 = r7.index     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r2 - r7
            r7 = 100
            if (r2 == r7) goto L_0x04bc
            goto L_0x04bf
        L_0x04bc:
            r14 = r5
            goto L_0x05bb
        L_0x04bf:
            com.google.android.gms.internal.ads.zzgc r2 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r7 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgf r14 = r8.zzxa     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r15 = r8.repeatMode     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r1 = r2.zza(r1, r7, r14, r15)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x04cb:
            com.google.android.gms.internal.ads.zzgc r2 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r2.zzcl()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 < r2) goto L_0x04db
            com.google.android.gms.internal.ads.zzlo r1 = r8.zzxz     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.zzfg()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r14 = r5
            goto L_0x05bb
        L_0x04db:
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != 0) goto L_0x04e5
            com.google.android.gms.internal.ads.zzfp r2 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r3 = r2.zzyz     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r14 = r5
            goto L_0x0545
        L_0x04e5:
            com.google.android.gms.internal.ads.zzgc r2 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r7 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2.zza(r1, r7, r9)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgc r2 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgf r7 = r8.zzxa     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r17 = 0
            r19 = 0
            r20 = 0
            r16 = r2
            r18 = r7
            r16.zza(r17, r18, r19, r20)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x0501
            r14 = r5
            goto L_0x0545
        L_0x0501:
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r1 = r1.zzcb()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgc r7 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r14 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r14 = r14.zzyr     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r15 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r7 = r7.zza(r14, r15, r9)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r14 = r7.zzaan     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r1 = r1 + r14
            long r14 = r8.zzyi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r1 = r1 - r14
            com.google.android.gms.internal.ads.zzgc r7 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r14 = 0
            r16 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r18 = java.lang.Math.max(r3, r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1 = r36
            r2 = r7
            r3 = r14
            r14 = r5
            r4 = r16
            r6 = r18
            android.util.Pair r1 = r1.zza(r2, r3, r4, r6)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x05bb
            java.lang.Object r2 = r1.first     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r2.intValue()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r1 = r1.second     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r3 = r1.longValue()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1 = r2
        L_0x0545:
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != 0) goto L_0x0550
            r5 = 60000000(0x3938700, double:2.96439388E-316)
            long r5 = r5 + r3
            r25 = r5
            goto L_0x0567
        L_0x0550:
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r5 = r2.zzcb()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgc r2 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r7 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r7 = r7.zzyr     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r11 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r2 = r2.zza(r7, r11, r9)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r12 = r2.zzaan     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r5 = r5 + r12
            r25 = r5
        L_0x0567:
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != 0) goto L_0x056e
            r31 = 0
            goto L_0x0575
        L_0x056e:
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r2.index     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r2 + r10
            r31 = r2
        L_0x0575:
            boolean r33 = r8.zzi(r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgc r2 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r5 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2.zza(r1, r5, r10)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r2 = new com.google.android.gms.internal.ads.zzfo     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfz[] r5 = r8.zzwu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzga[] r6 = r8.zzxs     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzop r7 = r8.zzwv     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfw r11 = r8.zzxt     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlo r12 = r8.zzxz     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r13 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            java.lang.Object r13 = r13.zzyn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r22 = r2
            r23 = r5
            r24 = r6
            r27 = r7
            r28 = r11
            r29 = r12
            r30 = r13
            r32 = r1
            r34 = r3
            r22.<init>(r23, r24, r25, r27, r28, r29, r30, r31, r32, r33, r34)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x05ad
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.zzyw = r2     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x05ad:
            r8.zzyj = r2     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlm r1 = r1.zzym     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.zza(r8, r3)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zze(r10)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x05bb
        L_0x05ba:
            r14 = r5
        L_0x05bb:
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x05d4
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r1 = r1.zzcc()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x05c8
            goto L_0x05d4
        L_0x05c8:
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x05d7
            boolean r1 = r8.zzxh     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 != 0) goto L_0x05d7
            r36.zzca()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x05d7
        L_0x05d4:
            r8.zze(r9)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x05d7:
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x06ee
        L_0x05db:
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == r2) goto L_0x0618
            long r1 = r8.zzyi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r3.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r3 = r3.zzyq     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x0618
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.release()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r1 = r1.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzb(r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfp r1 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r2.zzyr     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r3 = r3.zzys     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.<init>(r2, r3)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxn = r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r36.zzbx()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Handler r1 = r8.zzwx     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2 = 5
            com.google.android.gms.internal.ads.zzfp r3 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Message r1 = r1.obtainMessage(r2, r3)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.sendToTarget()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x05db
        L_0x0618:
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r1 = r1.zzyt     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x0642
            r1 = 0
        L_0x061f:
            com.google.android.gms.internal.ads.zzfz[] r2 = r8.zzwu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r2.length     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 >= r2) goto L_0x06ee
            com.google.android.gms.internal.ads.zzfz[] r2 = r8.zzwu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2 = r2[r1]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlv[] r3 = r3.zzyo     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r3 = r3[r1]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r3 == 0) goto L_0x063f
            com.google.android.gms.internal.ads.zzlv r4 = r2.zzbg()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r4 != r3) goto L_0x063f
            boolean r3 = r2.zzbh()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r3 == 0) goto L_0x063f
            r2.zzbi()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x063f:
            int r1 = r1 + 1
            goto L_0x061f
        L_0x0642:
            r1 = 0
        L_0x0643:
            com.google.android.gms.internal.ads.zzfz[] r2 = r8.zzwu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r2.length     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 >= r2) goto L_0x0665
            com.google.android.gms.internal.ads.zzfz[] r2 = r8.zzwu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2 = r2[r1]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlv[] r3 = r3.zzyo     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r3 = r3[r1]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlv r4 = r2.zzbg()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r4 != r3) goto L_0x06ee
            if (r3 == 0) goto L_0x0662
            boolean r2 = r2.zzbh()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != 0) goto L_0x0662
            goto L_0x06ee
        L_0x0662:
            int r1 = r1 + 1
            goto L_0x0643
        L_0x0665:
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r1 = r1.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x06ee
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r1 = r1.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r1 = r1.zzyu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x06ee
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzor r1 = r1.zzyx     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r2 = r2.zzyw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzyk = r2     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzor r2 = r2.zzyx     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlm r3 = r3.zzym     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r3 = r3.zzey()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0694
            r3 = 1
            goto L_0x0695
        L_0x0694:
            r3 = 0
        L_0x0695:
            r4 = 0
        L_0x0696:
            com.google.android.gms.internal.ads.zzfz[] r5 = r8.zzwu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r5 = r5.length     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r4 >= r5) goto L_0x06ee
            com.google.android.gms.internal.ads.zzfz[] r5 = r8.zzwu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r5 = r5[r4]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzoo r6 = r1.zzbfl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzom r6 = r6.zzbe(r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r6 == 0) goto L_0x06eb
            if (r3 != 0) goto L_0x06e8
            boolean r6 = r5.zzbj()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r6 != 0) goto L_0x06eb
            com.google.android.gms.internal.ads.zzoo r6 = r2.zzbfl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzom r6 = r6.zzbe(r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgb[] r7 = r1.zzbfn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r7 = r7[r4]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzgb[] r11 = r2.zzbfn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r11 = r11[r4]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r6 == 0) goto L_0x06e8
            boolean r7 = r11.equals(r7)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r7 == 0) goto L_0x06e8
            int r7 = r6.length()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfs[] r7 = new com.google.android.gms.internal.ads.zzfs[r7]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r11 = 0
        L_0x06cc:
            int r12 = r7.length     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r11 >= r12) goto L_0x06d8
            com.google.android.gms.internal.ads.zzfs r12 = r6.zzat(r11)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r7[r11] = r12     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r11 = r11 + 1
            goto L_0x06cc
        L_0x06d8:
            com.google.android.gms.internal.ads.zzfo r6 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlv[] r6 = r6.zzyo     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r6 = r6[r4]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r11 = r8.zzyk     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r11 = r11.zzcb()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r5.zza(r7, r6, r11)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x06eb
        L_0x06e8:
            r5.zzbi()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x06eb:
            int r4 = r4 + 1
            goto L_0x0696
        L_0x06ee:
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2 = 10
            if (r1 != 0) goto L_0x06fc
            r36.zzbz()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zza(r14, r2)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x085e
        L_0x06fc:
            java.lang.String r1 = "doSomeWork"
            com.google.android.gms.internal.ads.zzqc.beginSection(r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r36.zzbx()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r1 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlm r1 = r1.zzym     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfp r4 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r4 = r4.zzyz     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.zzaa(r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfz[] r1 = r8.zzya     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r4 = r1.length     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r5 = 0
            r6 = 1
            r7 = 1
        L_0x0715:
            if (r5 >= r4) goto L_0x074c
            r11 = r1[r5]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r12 = r8.zzyi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r2 = r8.zzyf     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r11.zzb(r12, r2)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r7 == 0) goto L_0x072a
            boolean r2 = r11.zzcj()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 == 0) goto L_0x072a
            r7 = 1
            goto L_0x072b
        L_0x072a:
            r7 = 0
        L_0x072b:
            boolean r2 = r11.isReady()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != 0) goto L_0x073a
            boolean r2 = r11.zzcj()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 == 0) goto L_0x0738
            goto L_0x073a
        L_0x0738:
            r2 = 0
            goto L_0x073b
        L_0x073a:
            r2 = 1
        L_0x073b:
            if (r2 != 0) goto L_0x0740
            r11.zzbk()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0740:
            if (r6 == 0) goto L_0x0746
            if (r2 == 0) goto L_0x0746
            r6 = 1
            goto L_0x0747
        L_0x0746:
            r6 = 0
        L_0x0747:
            int r5 = r5 + 1
            r2 = 10
            goto L_0x0715
        L_0x074c:
            if (r6 != 0) goto L_0x0751
            r36.zzbz()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0751:
            com.google.android.gms.internal.ads.zzps r1 = r8.zzxy     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x0776
            com.google.android.gms.internal.ads.zzps r1 = r8.zzxy     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfy r1 = r1.zzcx()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfy r2 = r8.zzxm     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r2 = r1.equals(r2)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != 0) goto L_0x0776
            r8.zzxm = r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzqa r2 = r8.zzxu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzps r3 = r8.zzxy     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2.zza(r3)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Handler r2 = r8.zzwx     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r3 = 7
            android.os.Message r1 = r2.obtainMessage(r3, r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.sendToTarget()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0776:
            com.google.android.gms.internal.ads.zzgc r1 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r2.zzyr     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r3 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r1 = r1.zza(r2, r3, r9)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r1 = r1.zzaan     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r7 == 0) goto L_0x07a7
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0797
            com.google.android.gms.internal.ads.zzfp r3 = r8.zzxn     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r3 = r3.zzyz     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x07a7
        L_0x0797:
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyl     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r3 = r3.zzyt     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r3 == 0) goto L_0x07a7
            r3 = 4
            r8.setState(r3)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r36.zzbw()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r4 = 2
            goto L_0x0827
        L_0x07a7:
            int r3 = r8.state     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r4 = 2
            if (r3 != r4) goto L_0x080c
            com.google.android.gms.internal.ads.zzfz[] r3 = r8.zzya     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r3 = r3.length     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r3 <= 0) goto L_0x07fa
            if (r6 == 0) goto L_0x07f8
            boolean r1 = r8.zzyc     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r2 = r2.zzyu     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 != 0) goto L_0x07c0
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r2 = r2.zzys     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x07c8
        L_0x07c0:
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlm r2 = r2.zzym     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r2 = r2.zzez()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x07c8:
            r5 = -9223372036854775808
            int r7 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x07e4
            com.google.android.gms.internal.ads.zzfo r2 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r2 = r2.zzyt     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r2 == 0) goto L_0x07d6
            r1 = 1
            goto L_0x07f4
        L_0x07d6:
            com.google.android.gms.internal.ads.zzgc r2 = r8.zzxi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r3 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r3 = r3.zzyr     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r5 = r8.zzxb     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzge r2 = r2.zza(r3, r5, r9)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r2 = r2.zzaan     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x07e4:
            com.google.android.gms.internal.ads.zzfw r5 = r8.zzxt     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfo r6 = r8.zzyj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r11 = r8.zzyi     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r6 = r6.zzcb()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            long r11 = r11 - r6
            long r2 = r2 - r11
            boolean r1 = r5.zzc(r2, r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x07f4:
            if (r1 == 0) goto L_0x07f8
            r1 = 1
            goto L_0x07fe
        L_0x07f8:
            r1 = 0
            goto L_0x07fe
        L_0x07fa:
            boolean r1 = r8.zzi(r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x07fe:
            if (r1 == 0) goto L_0x0827
            r1 = 3
            r8.setState(r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            boolean r1 = r8.zzxd     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x0827
            r36.zzbv()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x0827
        L_0x080c:
            int r3 = r8.state     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r5 = 3
            if (r3 != r5) goto L_0x0827
            com.google.android.gms.internal.ads.zzfz[] r3 = r8.zzya     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r3 = r3.length     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r3 <= 0) goto L_0x0817
            goto L_0x081b
        L_0x0817:
            boolean r6 = r8.zzi(r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x081b:
            if (r6 != 0) goto L_0x0827
            boolean r1 = r8.zzxd     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzyc = r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.setState(r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r36.zzbw()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x0827:
            int r1 = r8.state     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 != r4) goto L_0x0838
            com.google.android.gms.internal.ads.zzfz[] r1 = r8.zzya     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r2 = r1.length     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x082e:
            if (r9 >= r2) goto L_0x0838
            r3 = r1[r9]     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r3.zzbk()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r9 = r9 + 1
            goto L_0x082e
        L_0x0838:
            boolean r1 = r8.zzxd     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x0841
            int r1 = r8.state     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2 = 3
            if (r1 == r2) goto L_0x0845
        L_0x0841:
            int r1 = r8.state     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 != r4) goto L_0x084b
        L_0x0845:
            r1 = 10
            r8.zza(r14, r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x085b
        L_0x084b:
            com.google.android.gms.internal.ads.zzfz[] r1 = r8.zzya     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r1 = r1.length     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x0856
            r1 = 1000(0x3e8, double:4.94E-321)
            r8.zza(r14, r1)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x085b
        L_0x0856:
            android.os.Handler r1 = r8.handler     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.removeMessages(r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x085b:
            com.google.android.gms.internal.ads.zzqc.endSection()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x085e:
            return r10
        L_0x085f:
            r4 = 2
            int r1 = r1.arg1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x0866
            r1 = 1
            goto L_0x0867
        L_0x0866:
            r1 = 0
        L_0x0867:
            r8.zzyc = r9     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxd = r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 != 0) goto L_0x0874
            r36.zzbw()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r36.zzbx()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x088b
        L_0x0874:
            int r1 = r8.state     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2 = 3
            if (r1 != r2) goto L_0x0882
            r36.zzbv()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Handler r1 = r8.handler     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.sendEmptyMessage(r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            goto L_0x088b
        L_0x0882:
            int r1 = r8.state     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 != r4) goto L_0x088b
            android.os.Handler r1 = r8.handler     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.sendEmptyMessage(r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x088b:
            return r10
        L_0x088c:
            r4 = 2
            java.lang.Object r2 = r1.obj     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzlo r2 = (com.google.android.gms.internal.ads.zzlo) r2     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            int r1 = r1.arg1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x0897
            r1 = 1
            goto L_0x0898
        L_0x0897:
            r1 = 0
        L_0x0898:
            android.os.Handler r3 = r8.zzwx     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r3.sendEmptyMessage(r9)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzf(r10)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfw r3 = r8.zzxt     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r3.zzcg()     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            if (r1 == 0) goto L_0x08b3
            com.google.android.gms.internal.ads.zzfp r1 = new com.google.android.gms.internal.ads.zzfp     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r1.<init>(r9, r5)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.zzxn = r1     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
        L_0x08b3:
            r8.zzxz = r2     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            com.google.android.gms.internal.ads.zzfg r1 = r8.zzxw     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r2.zza(r1, r10, r8)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r8.setState(r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            android.os.Handler r1 = r8.handler     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            r1.sendEmptyMessage(r4)     // Catch:{ zzff -> 0x08c8, IOException -> 0x08c3, RuntimeException -> 0x08cd }
            return r10
        L_0x08c3:
            r0 = move-exception
            r1 = r0
            r3 = 8
            goto L_0x08ed
        L_0x08c8:
            r0 = move-exception
            r1 = r0
            r3 = 8
            goto L_0x0909
        L_0x08cd:
            r0 = move-exception
            r1 = r0
            java.lang.String r2 = "ExoPlayerImplInternal"
            java.lang.String r3 = "Internal runtime error."
            android.util.Log.e(r2, r3, r1)
            android.os.Handler r2 = r8.zzwx
            com.google.android.gms.internal.ads.zzff r1 = com.google.android.gms.internal.ads.zzff.zza(r1)
            r3 = 8
            android.os.Message r1 = r2.obtainMessage(r3, r1)
            r1.sendToTarget()
            r36.zzby()
            return r10
        L_0x08e9:
            r0 = move-exception
            r3 = 8
            r1 = r0
        L_0x08ed:
            java.lang.String r2 = "ExoPlayerImplInternal"
            java.lang.String r4 = "Source error."
            android.util.Log.e(r2, r4, r1)
            android.os.Handler r2 = r8.zzwx
            com.google.android.gms.internal.ads.zzff r1 = com.google.android.gms.internal.ads.zzff.zza(r1)
            android.os.Message r1 = r2.obtainMessage(r3, r1)
            r1.sendToTarget()
            r36.zzby()
            return r10
        L_0x0905:
            r0 = move-exception
            r3 = 8
            r1 = r0
        L_0x0909:
            java.lang.String r2 = "ExoPlayerImplInternal"
            java.lang.String r4 = "Renderer error."
            android.util.Log.e(r2, r4, r1)
            android.os.Handler r2 = r8.zzwx
            android.os.Message r1 = r2.obtainMessage(r3, r1)
            r1.sendToTarget()
            r36.zzby()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfn.handleMessage(android.os.Message):boolean");
    }

    private final void setState(int i) {
        if (this.state != i) {
            this.state = i;
            this.zzwx.obtainMessage(1, i, 0).sendToTarget();
        }
    }

    private final void zze(boolean z) {
        if (this.zzxh != z) {
            this.zzxh = z;
            this.zzwx.obtainMessage(2, z ? 1 : 0, 0).sendToTarget();
        }
    }

    private final void zzbv() throws zzff {
        this.zzyc = false;
        this.zzxu.start();
        for (zzfz start : this.zzya) {
            start.start();
        }
    }

    private final void zzbw() throws zzff {
        this.zzxu.stop();
        for (zzfz zza : this.zzya) {
            zza(zza);
        }
    }

    private final void zzbx() throws zzff {
        long j;
        zzfo zzfo = this.zzyl;
        if (zzfo != null) {
            long zzey = zzfo.zzym.zzey();
            if (zzey != -9223372036854775807L) {
                zzh(zzey);
            } else {
                zzfz zzfz = this.zzxx;
                if (zzfz == null || zzfz.zzcj()) {
                    this.zzyi = this.zzxu.zzde();
                } else {
                    this.zzyi = this.zzxy.zzde();
                    this.zzxu.zzam(this.zzyi);
                }
                zzey = this.zzyi - this.zzyl.zzcb();
            }
            this.zzxn.zzyz = zzey;
            this.zzyf = SystemClock.elapsedRealtime() * 1000;
            if (this.zzya.length == 0) {
                j = Long.MIN_VALUE;
            } else {
                j = this.zzyl.zzym.zzez();
            }
            zzfp zzfp = this.zzxn;
            if (j == Long.MIN_VALUE) {
                j = this.zzxi.zza(this.zzyl.zzyr, this.zzxb, false).zzaan;
            }
            zzfp.zzza = j;
        }
    }

    private final void zza(long j, long j2) {
        this.handler.removeMessages(2);
        long elapsedRealtime = (j + j2) - SystemClock.elapsedRealtime();
        if (elapsedRealtime <= 0) {
            this.handler.sendEmptyMessage(2);
        } else {
            this.handler.sendEmptyMessageDelayed(2, elapsedRealtime);
        }
    }

    private final long zza(int i, long j) throws zzff {
        zzfo zzfo;
        zzbw();
        this.zzyc = false;
        setState(2);
        zzfo zzfo2 = this.zzyl;
        if (zzfo2 == null) {
            zzfo zzfo3 = this.zzyj;
            if (zzfo3 != null) {
                zzfo3.release();
            }
            zzfo = null;
        } else {
            zzfo = null;
            while (zzfo2 != null) {
                if (zzfo2.zzyr != i || !zzfo2.zzyu) {
                    zzfo2.release();
                } else {
                    zzfo = zzfo2;
                }
                zzfo2 = zzfo2.zzyw;
            }
        }
        zzfo zzfo4 = this.zzyl;
        if (!(zzfo4 == zzfo && zzfo4 == this.zzyk)) {
            for (zzfz disable : this.zzya) {
                disable.disable();
            }
            this.zzya = new zzfz[0];
            this.zzxy = null;
            this.zzxx = null;
            this.zzyl = null;
        }
        if (zzfo != null) {
            zzfo.zzyw = null;
            this.zzyj = zzfo;
            this.zzyk = zzfo;
            zzb(zzfo);
            if (this.zzyl.zzyv) {
                j = this.zzyl.zzym.zzab(j);
            }
            zzh(j);
            zzca();
        } else {
            this.zzyj = null;
            this.zzyk = null;
            this.zzyl = null;
            zzh(j);
        }
        this.handler.sendEmptyMessage(2);
        return j;
    }

    private final void zzh(long j) throws zzff {
        long j2;
        zzfo zzfo = this.zzyl;
        if (zzfo == null) {
            j2 = j + 60000000;
        } else {
            j2 = j + zzfo.zzcb();
        }
        this.zzyi = j2;
        this.zzxu.zzam(this.zzyi);
        for (zzfz zzd : this.zzya) {
            zzd.zzd(this.zzyi);
        }
    }

    private final void zzby() {
        zzf(true);
        this.zzxt.onStopped();
        setState(1);
    }

    private final void zzf(boolean z) {
        zzfz[] zzfzArr;
        this.handler.removeMessages(2);
        this.zzyc = false;
        this.zzxu.stop();
        this.zzxy = null;
        this.zzxx = null;
        this.zzyi = 60000000;
        for (zzfz zzfz : this.zzya) {
            try {
                zza(zzfz);
                zzfz.disable();
            } catch (zzff | RuntimeException e) {
                Log.e("ExoPlayerImplInternal", "Stop failed.", e);
            }
        }
        this.zzya = new zzfz[0];
        zzfo zzfo = this.zzyl;
        if (zzfo == null) {
            zzfo = this.zzyj;
        }
        zza(zzfo);
        this.zzyj = null;
        this.zzyk = null;
        this.zzyl = null;
        zze(false);
        if (z) {
            zzlo zzlo = this.zzxz;
            if (zzlo != null) {
                zzlo.zzfh();
                this.zzxz = null;
            }
            this.zzxi = null;
        }
    }

    private static void zza(zzfz zzfz) throws zzff {
        if (zzfz.getState() == 2) {
            zzfz.stop();
        }
    }

    private final boolean zzi(long j) {
        return j == -9223372036854775807L || this.zzxn.zzyz < j || (this.zzyl.zzyw != null && this.zzyl.zzyw.zzyu);
    }

    private final void zzbz() throws IOException {
        zzfo zzfo = this.zzyj;
        if (zzfo != null && !zzfo.zzyu) {
            zzfo zzfo2 = this.zzyk;
            if (zzfo2 == null || zzfo2.zzyw == this.zzyj) {
                zzfz[] zzfzArr = this.zzya;
                int length = zzfzArr.length;
                int i = 0;
                while (i < length) {
                    if (zzfzArr[i].zzbh()) {
                        i++;
                    } else {
                        return;
                    }
                }
                this.zzyj.zzym.zzew();
            }
        }
    }

    private final void zza(Object obj, int i) {
        this.zzxn = new zzfp(0, 0);
        zzb(obj, i);
        this.zzxn = new zzfp(0, -9223372036854775807L);
        setState(4);
        zzf(false);
    }

    private final void zzb(Object obj, int i) {
        this.zzwx.obtainMessage(6, new zzfr(this.zzxi, obj, this.zzxn, i)).sendToTarget();
    }

    private final int zza(int i, zzgc zzgc, zzgc zzgc2) {
        int zzcl = zzgc.zzcl();
        int i2 = i;
        int i3 = -1;
        for (int i4 = 0; i4 < zzcl && i3 == -1; i4++) {
            i2 = zzgc.zza(i2, this.zzxb, this.zzxa, this.repeatMode);
            i3 = zzgc2.zzc(zzgc.zza(i2, this.zzxb, true).zzyn);
        }
        return i3;
    }

    private final boolean zzi(int i) {
        this.zzxi.zza(i, this.zzxb, false);
        if (this.zzxi.zza(0, this.zzxa, false, 0).zzaat || this.zzxi.zza(i, this.zzxb, this.zzxa, this.repeatMode) != -1) {
            return false;
        }
        return true;
    }

    private final Pair<Integer, Long> zza(zzfq zzfq) {
        zzgc zzgc = zzfq.zzxi;
        if (zzgc.isEmpty()) {
            zzgc = this.zzxi;
        }
        try {
            Pair<Integer, Long> zzb = zzb(zzgc, zzfq.zzzb, zzfq.zzzc);
            zzgc zzgc2 = this.zzxi;
            if (zzgc2 == zzgc) {
                return zzb;
            }
            int zzc = zzgc2.zzc(zzgc.zza(((Integer) zzb.first).intValue(), this.zzxb, true).zzyn);
            if (zzc != -1) {
                return Pair.create(Integer.valueOf(zzc), (Long) zzb.second);
            }
            int zza = zza(((Integer) zzb.first).intValue(), zzgc, this.zzxi);
            if (zza == -1) {
                return null;
            }
            this.zzxi.zza(zza, this.zzxb, false);
            return zzb(0, -9223372036854775807L);
        } catch (IndexOutOfBoundsException unused) {
            throw new zzfv(this.zzxi, zzfq.zzzb, zzfq.zzzc);
        }
    }

    private final Pair<Integer, Long> zzb(int i, long j) {
        return zzb(this.zzxi, i, -9223372036854775807L);
    }

    private final Pair<Integer, Long> zzb(zzgc zzgc, int i, long j) {
        return zza(zzgc, i, j, 0);
    }

    private final Pair<Integer, Long> zza(zzgc zzgc, int i, long j, long j2) {
        zzpo.zzc(i, 0, zzgc.zzck());
        zzgc.zza(i, this.zzxa, false, j2);
        if (j == -9223372036854775807L) {
            j = this.zzxa.zzaaw;
            if (j == -9223372036854775807L) {
                return null;
            }
        }
        long j3 = this.zzxa.zzaax + j;
        long j4 = zzgc.zza(0, this.zzxb, false).zzaan;
        int i2 = 0;
        while (j4 != -9223372036854775807L && j3 >= j4 && i2 < this.zzxa.zzaav) {
            j3 -= j4;
            i2++;
            j4 = zzgc.zza(i2, this.zzxb, false).zzaan;
        }
        return Pair.create(Integer.valueOf(i2), Long.valueOf(j3));
    }

    private final void zzca() {
        long j;
        if (!this.zzyj.zzyu) {
            j = 0;
        } else {
            j = this.zzyj.zzym.zzeu();
        }
        if (j == Long.MIN_VALUE) {
            zze(false);
            return;
        }
        long zzcb = this.zzyi - this.zzyj.zzcb();
        boolean zzk = this.zzxt.zzk(j - zzcb);
        zze(zzk);
        if (zzk) {
            this.zzyj.zzym.zzy(zzcb);
        }
    }

    private static void zza(zzfo zzfo) {
        while (zzfo != null) {
            zzfo.release();
            zzfo = zzfo.zzyw;
        }
    }

    private final void zzb(zzfo zzfo) throws zzff {
        if (this.zzyl != zzfo) {
            boolean[] zArr = new boolean[this.zzwu.length];
            int i = 0;
            int i2 = 0;
            while (true) {
                zzfz[] zzfzArr = this.zzwu;
                if (i < zzfzArr.length) {
                    zzfz zzfz = zzfzArr[i];
                    zArr[i] = zzfz.getState() != 0;
                    zzom zzbe = zzfo.zzyx.zzbfl.zzbe(i);
                    if (zzbe != null) {
                        i2++;
                    }
                    if (zArr[i] && (zzbe == null || (zzfz.zzbj() && zzfz.zzbg() == this.zzyl.zzyo[i]))) {
                        if (zzfz == this.zzxx) {
                            this.zzxu.zza(this.zzxy);
                            this.zzxy = null;
                            this.zzxx = null;
                        }
                        zza(zzfz);
                        zzfz.disable();
                    }
                    i++;
                } else {
                    this.zzyl = zzfo;
                    this.zzwx.obtainMessage(3, zzfo.zzyx).sendToTarget();
                    zza(zArr, i2);
                    return;
                }
            }
        }
    }

    private final void zza(boolean[] zArr, int i) throws zzff {
        this.zzya = new zzfz[i];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            zzfz[] zzfzArr = this.zzwu;
            if (i2 < zzfzArr.length) {
                zzfz zzfz = zzfzArr[i2];
                zzom zzbe = this.zzyl.zzyx.zzbfl.zzbe(i2);
                if (zzbe != null) {
                    int i4 = i3 + 1;
                    this.zzya[i3] = zzfz;
                    if (zzfz.getState() == 0) {
                        zzgb zzgb = this.zzyl.zzyx.zzbfn[i2];
                        boolean z = this.zzxd && this.state == 3;
                        boolean z2 = !zArr[i2] && z;
                        zzfs[] zzfsArr = new zzfs[zzbe.length()];
                        for (int i5 = 0; i5 < zzfsArr.length; i5++) {
                            zzfsArr[i5] = zzbe.zzat(i5);
                        }
                        zzfz.zza(zzgb, zzfsArr, this.zzyl.zzyo[i2], this.zzyi, z2, this.zzyl.zzcb());
                        zzps zzbf = zzfz.zzbf();
                        if (zzbf != null) {
                            if (this.zzxy == null) {
                                this.zzxy = zzbf;
                                this.zzxx = zzfz;
                                this.zzxy.zzb(this.zzxm);
                            } else {
                                throw zzff.zza((RuntimeException) new IllegalStateException("Multiple renderer media clocks enabled."));
                            }
                        }
                        if (z) {
                            zzfz.start();
                        }
                    }
                    i3 = i4;
                }
                i2++;
            } else {
                return;
            }
        }
    }

    public final /* synthetic */ void zza(zzlw zzlw) {
        this.handler.obtainMessage(9, (zzlm) zzlw).sendToTarget();
    }
}

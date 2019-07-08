package com.google.android.gms.internal.ads;

import android.content.Context;
import javax.annotation.concurrent.GuardedBy;

@zzark
public abstract class zzaph extends zzaxv {
    protected final Context mContext;
    protected final Object mLock = new Object();
    protected final zzapm zzdsj;
    protected final zzaxg zzdsk;
    @GuardedBy("mLock")
    protected zzasm zzdsl;
    protected final Object zzdsn = new Object();

    protected zzaph(Context context, zzaxg zzaxg, zzapm zzapm) {
        super(true);
        this.mContext = context;
        this.zzdsk = zzaxg;
        this.zzdsl = zzaxg.zzehy;
        this.zzdsj = zzapm;
    }

    public void onStop() {
    }

    /* access modifiers changed from: protected */
    public abstract void zzap(long j) throws zzapk;

    /* access modifiers changed from: protected */
    public abstract zzaxf zzcr(int i);

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0033 A[Catch:{ zzapk -> 0x0014 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003b A[Catch:{ zzapk -> 0x0014 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzki() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            java.lang.String r1 = "AdRendererBackgroundTask started."
            com.google.android.gms.internal.ads.zzaxz.zzdn(r1)     // Catch:{ all -> 0x0061 }
            com.google.android.gms.internal.ads.zzaxg r1 = r5.zzdsk     // Catch:{ all -> 0x0061 }
            int r1 = r1.errorCode     // Catch:{ all -> 0x0061 }
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ zzapk -> 0x0014 }
            r5.zzap(r2)     // Catch:{ zzapk -> 0x0014 }
            goto L_0x0051
        L_0x0014:
            r1 = move-exception
            int r2 = r1.getErrorCode()     // Catch:{ all -> 0x0061 }
            r3 = 3
            if (r2 == r3) goto L_0x0028
            r3 = -1
            if (r2 != r3) goto L_0x0020
            goto L_0x0028
        L_0x0020:
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0061 }
            com.google.android.gms.internal.ads.zzaxz.zzeo(r1)     // Catch:{ all -> 0x0061 }
            goto L_0x002f
        L_0x0028:
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0061 }
            com.google.android.gms.internal.ads.zzaxz.zzen(r1)     // Catch:{ all -> 0x0061 }
        L_0x002f:
            com.google.android.gms.internal.ads.zzasm r1 = r5.zzdsl     // Catch:{ all -> 0x0061 }
            if (r1 != 0) goto L_0x003b
            com.google.android.gms.internal.ads.zzasm r1 = new com.google.android.gms.internal.ads.zzasm     // Catch:{ all -> 0x0061 }
            r1.<init>(r2)     // Catch:{ all -> 0x0061 }
            r5.zzdsl = r1     // Catch:{ all -> 0x0061 }
            goto L_0x0046
        L_0x003b:
            com.google.android.gms.internal.ads.zzasm r1 = new com.google.android.gms.internal.ads.zzasm     // Catch:{ all -> 0x0061 }
            com.google.android.gms.internal.ads.zzasm r3 = r5.zzdsl     // Catch:{ all -> 0x0061 }
            long r3 = r3.zzdlx     // Catch:{ all -> 0x0061 }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x0061 }
            r5.zzdsl = r1     // Catch:{ all -> 0x0061 }
        L_0x0046:
            android.os.Handler r1 = com.google.android.gms.internal.ads.zzayh.zzelc     // Catch:{ all -> 0x0061 }
            com.google.android.gms.internal.ads.zzapi r3 = new com.google.android.gms.internal.ads.zzapi     // Catch:{ all -> 0x0061 }
            r3.<init>(r5)     // Catch:{ all -> 0x0061 }
            r1.post(r3)     // Catch:{ all -> 0x0061 }
            r1 = r2
        L_0x0051:
            com.google.android.gms.internal.ads.zzaxf r1 = r5.zzcr(r1)     // Catch:{ all -> 0x0061 }
            android.os.Handler r2 = com.google.android.gms.internal.ads.zzayh.zzelc     // Catch:{ all -> 0x0061 }
            com.google.android.gms.internal.ads.zzapj r3 = new com.google.android.gms.internal.ads.zzapj     // Catch:{ all -> 0x0061 }
            r3.<init>(r5, r1)     // Catch:{ all -> 0x0061 }
            r2.post(r3)     // Catch:{ all -> 0x0061 }
            monitor-exit(r0)     // Catch:{ all -> 0x0061 }
            return
        L_0x0061:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0061 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaph.zzki():void");
    }
}

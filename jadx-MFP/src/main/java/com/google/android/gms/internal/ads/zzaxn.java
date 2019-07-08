package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

final class zzaxn {
    private final Object mLock;
    private volatile int zzejg;
    private volatile long zzejh;

    private zzaxn() {
        this.mLock = new Object();
        this.zzejg = zzaxo.zzeji;
        this.zzejh = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzk(int r5, int r6) {
        /*
            r4 = this;
            r4.zzyu()
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzbv.zzlm()
            long r0 = r0.currentTimeMillis()
            java.lang.Object r2 = r4.mLock
            monitor-enter(r2)
            int r3 = r4.zzejg     // Catch:{ all -> 0x0020 }
            if (r3 == r5) goto L_0x0014
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            return
        L_0x0014:
            r4.zzejg = r6     // Catch:{ all -> 0x0020 }
            int r5 = r4.zzejg     // Catch:{ all -> 0x0020 }
            int r6 = com.google.android.gms.internal.ads.zzaxo.zzejk     // Catch:{ all -> 0x0020 }
            if (r5 != r6) goto L_0x001e
            r4.zzejh = r0     // Catch:{ all -> 0x0020 }
        L_0x001e:
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            return
        L_0x0020:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaxn.zzk(int, int):void");
    }

    private final void zzyu() {
        long currentTimeMillis = zzbv.zzlm().currentTimeMillis();
        synchronized (this.mLock) {
            if (this.zzejg == zzaxo.zzejk) {
                if (this.zzejh + ((Long) zzwu.zzpz().zzd(zzaan.zzcye)).longValue() <= currentTimeMillis) {
                    this.zzejg = zzaxo.zzeji;
                }
            }
        }
    }

    public final void zzal(boolean z) {
        if (z) {
            zzk(zzaxo.zzeji, zzaxo.zzejj);
        } else {
            zzk(zzaxo.zzejj, zzaxo.zzeji);
        }
    }

    public final boolean zzyj() {
        zzyu();
        return this.zzejg == zzaxo.zzejj;
    }

    public final boolean zzyk() {
        zzyu();
        return this.zzejg == zzaxo.zzejk;
    }

    public final void zzyl() {
        zzk(zzaxo.zzejj, zzaxo.zzejk);
    }

    /* synthetic */ zzaxn(zzaxm zzaxm) {
        this();
    }
}

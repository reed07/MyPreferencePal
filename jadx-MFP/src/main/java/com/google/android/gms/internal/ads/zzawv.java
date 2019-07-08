package com.google.android.gms.internal.ads;

import android.content.Context;

@zzark
public final class zzawv implements zzsg {
    private final Object mLock;
    private String zzboa;
    private final Context zzdvi;
    private boolean zzegs;

    public zzawv(Context context, String str) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.zzdvi = context;
        this.zzboa = str;
        this.zzegs = false;
        this.mLock = new Object();
    }

    public final void setAdUnitId(String str) {
        this.zzboa = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzai(boolean r4) {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.zzaww r0 = com.google.android.gms.ads.internal.zzbv.zzmf()
            android.content.Context r1 = r3.zzdvi
            boolean r0 = r0.zzv(r1)
            if (r0 != 0) goto L_0x000d
            return
        L_0x000d:
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            boolean r1 = r3.zzegs     // Catch:{ all -> 0x003f }
            if (r1 != r4) goto L_0x0016
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x0016:
            r3.zzegs = r4     // Catch:{ all -> 0x003f }
            java.lang.String r4 = r3.zzboa     // Catch:{ all -> 0x003f }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x0022
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x0022:
            boolean r4 = r3.zzegs     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x0032
            com.google.android.gms.internal.ads.zzaww r4 = com.google.android.gms.ads.internal.zzbv.zzmf()     // Catch:{ all -> 0x003f }
            android.content.Context r1 = r3.zzdvi     // Catch:{ all -> 0x003f }
            java.lang.String r2 = r3.zzboa     // Catch:{ all -> 0x003f }
            r4.zzc(r1, r2)     // Catch:{ all -> 0x003f }
            goto L_0x003d
        L_0x0032:
            com.google.android.gms.internal.ads.zzaww r4 = com.google.android.gms.ads.internal.zzbv.zzmf()     // Catch:{ all -> 0x003f }
            android.content.Context r1 = r3.zzdvi     // Catch:{ all -> 0x003f }
            java.lang.String r2 = r3.zzboa     // Catch:{ all -> 0x003f }
            r4.zzd(r1, r2)     // Catch:{ all -> 0x003f }
        L_0x003d:
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x003f:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzawv.zzai(boolean):void");
    }

    public final void zza(zzsf zzsf) {
        zzai(zzsf.zzuc);
    }
}

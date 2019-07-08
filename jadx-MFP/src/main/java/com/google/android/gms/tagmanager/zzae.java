package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.measurement.zzo;

final class zzae implements zzdh<zzo> {
    private final /* synthetic */ zzy zzbau;

    private zzae(zzy zzy) {
        this.zzbau = zzy;
    }

    public final void zznx() {
    }

    public final void zzu(int i) {
        if (i == zzcz.zzbdj) {
            this.zzbau.zzbal.zzob();
        }
        synchronized (this.zzbau) {
            if (!this.zzbau.isReady()) {
                if (this.zzbau.zzbao != null) {
                    this.zzbau.setResult(this.zzbau.zzbao);
                } else {
                    this.zzbau.setResult(this.zzbau.createFailedResult(Status.RESULT_TIMEOUT));
                }
            }
        }
        this.zzbau.zzao(this.zzbau.zzbal.zzoa());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0076, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void onSuccess(java.lang.Object r6) {
        /*
            r5 = this;
            com.google.android.gms.internal.measurement.zzo r6 = (com.google.android.gms.internal.measurement.zzo) r6
            com.google.android.gms.tagmanager.zzy r0 = r5.zzbau
            com.google.android.gms.tagmanager.zzai r0 = r0.zzbal
            r0.zzoc()
            com.google.android.gms.tagmanager.zzy r0 = r5.zzbau
            monitor-enter(r0)
            com.google.android.gms.internal.measurement.zzl r1 = r6.zzqg     // Catch:{ all -> 0x0077 }
            if (r1 != 0) goto L_0x003c
            com.google.android.gms.tagmanager.zzy r1 = r5.zzbau     // Catch:{ all -> 0x0077 }
            com.google.android.gms.internal.measurement.zzo r1 = r1.zzbaq     // Catch:{ all -> 0x0077 }
            com.google.android.gms.internal.measurement.zzl r1 = r1.zzqg     // Catch:{ all -> 0x0077 }
            if (r1 != 0) goto L_0x0032
            java.lang.String r6 = "Current resource is null; network resource is also null"
            com.google.android.gms.tagmanager.zzdi.e(r6)     // Catch:{ all -> 0x0077 }
            com.google.android.gms.tagmanager.zzy r6 = r5.zzbau     // Catch:{ all -> 0x0077 }
            com.google.android.gms.tagmanager.zzai r6 = r6.zzbal     // Catch:{ all -> 0x0077 }
            long r1 = r6.zzoa()     // Catch:{ all -> 0x0077 }
            com.google.android.gms.tagmanager.zzy r6 = r5.zzbau     // Catch:{ all -> 0x0077 }
            r6.zzao(r1)     // Catch:{ all -> 0x0077 }
            monitor-exit(r0)     // Catch:{ all -> 0x0077 }
            return
        L_0x0032:
            com.google.android.gms.tagmanager.zzy r1 = r5.zzbau     // Catch:{ all -> 0x0077 }
            com.google.android.gms.internal.measurement.zzo r1 = r1.zzbaq     // Catch:{ all -> 0x0077 }
            com.google.android.gms.internal.measurement.zzl r1 = r1.zzqg     // Catch:{ all -> 0x0077 }
            r6.zzqg = r1     // Catch:{ all -> 0x0077 }
        L_0x003c:
            com.google.android.gms.tagmanager.zzy r1 = r5.zzbau     // Catch:{ all -> 0x0077 }
            com.google.android.gms.tagmanager.zzy r2 = r5.zzbau     // Catch:{ all -> 0x0077 }
            com.google.android.gms.common.util.Clock r2 = r2.zzrz     // Catch:{ all -> 0x0077 }
            long r2 = r2.currentTimeMillis()     // Catch:{ all -> 0x0077 }
            r4 = 0
            r1.zza(r6, r2, r4)     // Catch:{ all -> 0x0077 }
            com.google.android.gms.tagmanager.zzy r1 = r5.zzbau     // Catch:{ all -> 0x0077 }
            long r1 = r1.zzazv     // Catch:{ all -> 0x0077 }
            r3 = 58
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0077 }
            r4.<init>(r3)     // Catch:{ all -> 0x0077 }
            java.lang.String r3 = "setting refresh time to current time: "
            r4.append(r3)     // Catch:{ all -> 0x0077 }
            r4.append(r1)     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x0077 }
            com.google.android.gms.tagmanager.zzdi.v(r1)     // Catch:{ all -> 0x0077 }
            com.google.android.gms.tagmanager.zzy r1 = r5.zzbau     // Catch:{ all -> 0x0077 }
            boolean r1 = r1.zznw()     // Catch:{ all -> 0x0077 }
            if (r1 != 0) goto L_0x0075
            com.google.android.gms.tagmanager.zzy r1 = r5.zzbau     // Catch:{ all -> 0x0077 }
            r1.zza(r6)     // Catch:{ all -> 0x0077 }
        L_0x0075:
            monitor-exit(r0)     // Catch:{ all -> 0x0077 }
            return
        L_0x0077:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0077 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzae.onSuccess(java.lang.Object):void");
    }

    /* synthetic */ zzae(zzy zzy, zzz zzz) {
        this(zzy);
    }
}

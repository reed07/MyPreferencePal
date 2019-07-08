package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class zzf implements zzt {
    private final Map<String, List<zzr<?>>> zzp = new HashMap();
    private final zzd zzq;

    zzf(zzd zzd) {
        this.zzq = zzd;
    }

    public final void zza(zzr<?> zzr, zzx<?> zzx) {
        List<zzr> list;
        if (zzx.zzbg == null || zzx.zzbg.zzb()) {
            zza(zzr);
            return;
        }
        String zzf = zzr.zzf();
        synchronized (this) {
            list = (List) this.zzp.remove(zzf);
        }
        if (list != null) {
            if (zzaf.DEBUG) {
                zzaf.v("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(list.size()), zzf);
            }
            for (zzr zzb : list) {
                this.zzq.zzk.zzb(zzb, zzx);
            }
        }
    }

    public final synchronized void zza(zzr<?> zzr) {
        String zzf = zzr.zzf();
        List list = (List) this.zzp.remove(zzf);
        if (list != null && !list.isEmpty()) {
            if (zzaf.DEBUG) {
                zzaf.v("%d waiting requests for cacheKey=%s; resend to network", Integer.valueOf(list.size()), zzf);
            }
            zzr zzr2 = (zzr) list.remove(0);
            this.zzp.put(zzf, list);
            zzr2.zza((zzt) this);
            try {
                this.zzq.zzi.put(zzr2);
            } catch (InterruptedException e) {
                zzaf.e("Couldn't add request to queue. %s", e.toString());
                Thread.currentThread().interrupt();
                this.zzq.quit();
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003a, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean zzb(com.google.android.gms.internal.ads.zzr<?> r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = r6.zzf()     // Catch:{ all -> 0x0053 }
            java.util.Map<java.lang.String, java.util.List<com.google.android.gms.internal.ads.zzr<?>>> r1 = r5.zzp     // Catch:{ all -> 0x0053 }
            boolean r1 = r1.containsKey(r0)     // Catch:{ all -> 0x0053 }
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x003b
            java.util.Map<java.lang.String, java.util.List<com.google.android.gms.internal.ads.zzr<?>>> r1 = r5.zzp     // Catch:{ all -> 0x0053 }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x0053 }
            java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x0053 }
            if (r1 != 0) goto L_0x001e
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0053 }
            r1.<init>()     // Catch:{ all -> 0x0053 }
        L_0x001e:
            java.lang.String r4 = "waiting-for-response"
            r6.zzb(r4)     // Catch:{ all -> 0x0053 }
            r1.add(r6)     // Catch:{ all -> 0x0053 }
            java.util.Map<java.lang.String, java.util.List<com.google.android.gms.internal.ads.zzr<?>>> r6 = r5.zzp     // Catch:{ all -> 0x0053 }
            r6.put(r0, r1)     // Catch:{ all -> 0x0053 }
            boolean r6 = com.google.android.gms.internal.ads.zzaf.DEBUG     // Catch:{ all -> 0x0053 }
            if (r6 == 0) goto L_0x0039
            java.lang.String r6 = "Request for cacheKey=%s is in flight, putting on hold."
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0053 }
            r1[r3] = r0     // Catch:{ all -> 0x0053 }
            com.google.android.gms.internal.ads.zzaf.d(r6, r1)     // Catch:{ all -> 0x0053 }
        L_0x0039:
            monitor-exit(r5)
            return r2
        L_0x003b:
            java.util.Map<java.lang.String, java.util.List<com.google.android.gms.internal.ads.zzr<?>>> r1 = r5.zzp     // Catch:{ all -> 0x0053 }
            r4 = 0
            r1.put(r0, r4)     // Catch:{ all -> 0x0053 }
            r6.zza(r5)     // Catch:{ all -> 0x0053 }
            boolean r6 = com.google.android.gms.internal.ads.zzaf.DEBUG     // Catch:{ all -> 0x0053 }
            if (r6 == 0) goto L_0x0051
            java.lang.String r6 = "new request, sending to network %s"
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0053 }
            r1[r3] = r0     // Catch:{ all -> 0x0053 }
            com.google.android.gms.internal.ads.zzaf.d(r6, r1)     // Catch:{ all -> 0x0053 }
        L_0x0051:
            monitor-exit(r5)
            return r3
        L_0x0053:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzf.zzb(com.google.android.gms.internal.ads.zzr):boolean");
    }
}

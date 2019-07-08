package com.google.android.gms.internal.ads;

final /* synthetic */ class zzbbs implements Runnable {
    private final zzbcl zzcak;
    private final zzbbm zzepb;
    private final zzbcb zzepc;

    zzbbs(zzbcl zzbcl, zzbbm zzbbm, zzbcb zzbcb) {
        this.zzcak = zzbcl;
        this.zzepb = zzbbm;
        this.zzepc = zzbcb;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [java.lang.Throwable] */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.lang.Throwable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.zzbcl r0 = r3.zzcak
            com.google.android.gms.internal.ads.zzbbm r1 = r3.zzepb
            com.google.android.gms.internal.ads.zzbcb r2 = r3.zzepc
            java.lang.Object r2 = r2.get()     // Catch:{ CancellationException -> 0x0030, ExecutionException -> 0x0023, InterruptedException -> 0x0017, Exception -> 0x0012 }
            java.lang.Object r1 = r1.apply(r2)     // Catch:{ CancellationException -> 0x0030, ExecutionException -> 0x0023, InterruptedException -> 0x0017, Exception -> 0x0012 }
            r0.set(r1)     // Catch:{ CancellationException -> 0x0030, ExecutionException -> 0x0023, InterruptedException -> 0x0017, Exception -> 0x0012 }
            return
        L_0x0012:
            r1 = move-exception
            r0.setException(r1)
            return
        L_0x0017:
            r1 = move-exception
            java.lang.Thread r2 = java.lang.Thread.currentThread()
            r2.interrupt()
            r0.setException(r1)
            return
        L_0x0023:
            r1 = move-exception
            java.lang.Throwable r2 = r1.getCause()
            if (r2 != 0) goto L_0x002b
            goto L_0x002c
        L_0x002b:
            r1 = r2
        L_0x002c:
            r0.setException(r1)
            return
        L_0x0030:
            r1 = 1
            r0.cancel(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbbs.run():void");
    }
}

package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.os.Bundle;

final class zzbo implements Runnable {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzbw zzaoh;
    private final /* synthetic */ zzas zzaoi;
    private final /* synthetic */ long zzaoj;
    private final /* synthetic */ Bundle zzaok;
    private final /* synthetic */ PendingResult zzrf;

    zzbo(zzbm zzbm, zzbw zzbw, long j, Bundle bundle, Context context, zzas zzas, PendingResult pendingResult) {
        this.zzaoh = zzbw;
        this.zzaoj = j;
        this.zzaok = bundle;
        this.val$context = context;
        this.zzaoi = zzas;
        this.zzrf = pendingResult;
    }

    public final void run() {
        long j = this.zzaoh.zzgu().zzanh.get();
        long j2 = this.zzaoj;
        if (j > 0 && (j2 >= j || j2 <= 0)) {
            j2 = j - 1;
        }
        if (j2 > 0) {
            this.zzaok.putLong("click_timestamp", j2);
        }
        this.zzaok.putString("_cis", "referrer broadcast");
        zzbw.zza(this.val$context, (zzan) null).zzgj().logEvent("auto", "_cmp", this.zzaok);
        this.zzaoi.zzjo().zzby("Install campaign recorded");
        PendingResult pendingResult = this.zzrf;
        if (pendingResult != null) {
            pendingResult.finish();
        }
    }
}

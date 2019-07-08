package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.fitness.result.DailyTotalResult;

final class zzdr extends zzbf {
    private final /* synthetic */ zzdq zzfo;

    zzdr(zzdq zzdq) {
        this.zzfo = zzdq;
    }

    public final void zza(DailyTotalResult dailyTotalResult) throws RemoteException {
        this.zzfo.setResult(dailyTotalResult);
    }
}

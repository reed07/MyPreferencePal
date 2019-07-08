package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.fitness.result.GoalsResult;

final class zzdi extends zzbr {
    private final /* synthetic */ zzdh zzfe;

    zzdi(zzdh zzdh) {
        this.zzfe = zzdh;
    }

    public final void zza(GoalsResult goalsResult) throws RemoteException {
        this.zzfe.setResult(goalsResult);
    }
}

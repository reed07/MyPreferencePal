package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.fitness.result.SessionReadResult;

final class zzel extends zzcl {
    private final ResultHolder<SessionReadResult> zzev;

    private zzel(ResultHolder<SessionReadResult> resultHolder) {
        this.zzev = resultHolder;
    }

    public final void zza(SessionReadResult sessionReadResult) throws RemoteException {
        this.zzev.setResult(sessionReadResult);
    }

    /* synthetic */ zzel(ResultHolder resultHolder, zzef zzef) {
        this(resultHolder);
    }
}

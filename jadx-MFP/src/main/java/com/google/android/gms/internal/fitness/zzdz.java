package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

final class zzdz extends zzci {
    private final ResultHolder<ListSubscriptionsResult> zzev;

    private zzdz(ResultHolder<ListSubscriptionsResult> resultHolder) {
        this.zzev = resultHolder;
    }

    public final void zza(ListSubscriptionsResult listSubscriptionsResult) {
        this.zzev.setResult(listSubscriptionsResult);
    }

    /* synthetic */ zzdz(ResultHolder resultHolder, zzdu zzdu) {
        this(resultHolder);
    }
}

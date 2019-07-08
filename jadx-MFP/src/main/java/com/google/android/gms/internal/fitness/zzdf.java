package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.fitness.result.DataTypeResult;

final class zzdf extends zzbo {
    private final ResultHolder<DataTypeResult> zzev;

    private zzdf(ResultHolder<DataTypeResult> resultHolder) {
        this.zzev = resultHolder;
    }

    public final void zza(DataTypeResult dataTypeResult) {
        this.zzev.setResult(dataTypeResult);
    }

    /* synthetic */ zzdf(ResultHolder resultHolder, zzdc zzdc) {
        this(resultHolder);
    }
}

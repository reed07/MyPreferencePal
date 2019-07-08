package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.fitness.result.DataSourcesResult;

public final class zzo extends zzbl {
    private final ResultHolder<DataSourcesResult> zzev;

    public zzo(ResultHolder<DataSourcesResult> resultHolder) {
        this.zzev = resultHolder;
    }

    public final void zza(DataSourcesResult dataSourcesResult) {
        this.zzev.setResult(dataSourcesResult);
    }
}

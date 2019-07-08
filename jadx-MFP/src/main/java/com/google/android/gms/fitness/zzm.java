package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.fitness.result.DataSourcesResult;

final /* synthetic */ class zzm implements ResultConverter {
    static final ResultConverter zzf = new zzm();

    private zzm() {
    }

    public final Object convert(Result result) {
        return ((DataSourcesResult) result).getDataSources();
    }
}

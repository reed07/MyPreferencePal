package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.fitness.result.DataTypeResult;

final /* synthetic */ class zze implements ResultConverter {
    static final ResultConverter zzf = new zze();

    private zze() {
    }

    public final Object convert(Result result) {
        return ((DataTypeResult) result).getDataType();
    }
}
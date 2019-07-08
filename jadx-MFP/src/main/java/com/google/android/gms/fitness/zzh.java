package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.fitness.result.GoalsResult;

final /* synthetic */ class zzh implements ResultConverter {
    static final ResultConverter zzf = new zzh();

    private zzh() {
    }

    public final Object convert(Result result) {
        return ((GoalsResult) result).getGoals();
    }
}

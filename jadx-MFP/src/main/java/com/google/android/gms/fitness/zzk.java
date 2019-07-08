package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

final /* synthetic */ class zzk implements ResultConverter {
    static final ResultConverter zzf = new zzk();

    private zzk() {
    }

    public final Object convert(Result result) {
        return ((ListSubscriptionsResult) result).getSubscriptions();
    }
}

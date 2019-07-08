package com.google.android.gms.fitness;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

final /* synthetic */ class zzl implements ResultConverter {
    static final ResultConverter zzf = new zzl();

    private zzl() {
    }

    public final Object convert(Result result) {
        return ((ListSubscriptionsResult) result).getSubscriptions();
    }
}

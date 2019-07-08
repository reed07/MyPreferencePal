package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.fitness.result.SessionStopResult;

final class zzem extends zzco {
    private final ResultHolder<SessionStopResult> zzev;

    private zzem(ResultHolder<SessionStopResult> resultHolder) {
        this.zzev = resultHolder;
    }

    public final void zza(SessionStopResult sessionStopResult) {
        this.zzev.setResult(sessionStopResult);
    }

    /* synthetic */ zzem(ResultHolder resultHolder, zzef zzef) {
        this(resultHolder);
    }
}

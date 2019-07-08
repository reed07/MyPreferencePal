package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

abstract class zzax extends zzav<Status> {
    zzax(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    /* renamed from: zza */
    public Status createFailedResult(Status status) {
        Preconditions.checkArgument(!status.isSuccess());
        return status;
    }
}

package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;

abstract class zzap<R extends Result> extends ApiMethodImpl<R, zzam> {
    public zzap(GoogleApiClient googleApiClient) {
        super(zzam.API, googleApiClient);
    }
}

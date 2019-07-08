package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;

abstract class zzbb<R extends Result> extends ApiMethodImpl<R, zzay> {
    public zzbb(GoogleApiClient googleApiClient) {
        super(zzay.API, googleApiClient);
    }
}

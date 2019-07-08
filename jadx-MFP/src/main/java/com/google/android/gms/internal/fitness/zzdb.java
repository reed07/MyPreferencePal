package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.ConfigApi;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.result.DataTypeResult;

public final class zzdb implements ConfigApi {
    public final PendingResult<DataTypeResult> createCustomDataType(GoogleApiClient googleApiClient, DataTypeCreateRequest dataTypeCreateRequest) {
        return googleApiClient.execute(new zzdc(this, googleApiClient, dataTypeCreateRequest));
    }

    public final PendingResult<DataTypeResult> readDataType(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.enqueue(new zzdd(this, googleApiClient, str));
    }

    public final PendingResult<Status> disableFit(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zzde(this, googleApiClient));
    }
}

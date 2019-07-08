package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.result.DataTypeResult;

final class zzdc extends zzy<DataTypeResult> {
    private final /* synthetic */ DataTypeCreateRequest zzfb;

    zzdc(zzdb zzdb, GoogleApiClient googleApiClient, DataTypeCreateRequest dataTypeCreateRequest) {
        this.zzfb = dataTypeCreateRequest;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzbv) ((zzv) anyClient).getService()).zza(new DataTypeCreateRequest(this.zzfb, (zzbn) new zzdf(this, null)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return DataTypeResult.zzc(status);
    }
}

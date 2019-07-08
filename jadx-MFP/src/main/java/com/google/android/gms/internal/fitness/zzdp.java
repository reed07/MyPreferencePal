package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;

final class zzdp extends zzaj<DataReadResult> {
    private final /* synthetic */ DataReadRequest zzfl;

    zzdp(zzdj zzdj, GoogleApiClient googleApiClient, DataReadRequest dataReadRequest) {
        this.zzfl = dataReadRequest;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzbz) ((zzag) anyClient).getService()).zza(new DataReadRequest(this.zzfl, (zzbh) new zzds(this, null)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return DataReadResult.zza(status, this.zzfl.getDataTypes(), this.zzfl.getDataSources());
    }
}

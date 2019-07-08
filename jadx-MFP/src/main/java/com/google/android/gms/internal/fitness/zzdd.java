package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.zzs;
import com.google.android.gms.fitness.result.DataTypeResult;

final class zzdd extends zzy<DataTypeResult> {
    private final /* synthetic */ String zzfc;

    zzdd(zzdb zzdb, GoogleApiClient googleApiClient, String str) {
        this.zzfc = str;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzbv) ((zzv) anyClient).getService()).zza(new zzs(this.zzfc, (zzbn) new zzdf(this, null)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return DataTypeResult.zzc(status);
    }
}

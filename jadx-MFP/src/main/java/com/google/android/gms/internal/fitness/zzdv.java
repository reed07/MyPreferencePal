package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzaj;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

final class zzdv extends zzap<ListSubscriptionsResult> {
    private final /* synthetic */ DataType zzfm;

    zzdv(zzdt zzdt, GoogleApiClient googleApiClient, DataType dataType) {
        this.zzfm = dataType;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzcb) ((zzam) anyClient).getService()).zza(new zzaj(this.zzfm, (zzch) new zzdz(this, null)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return ListSubscriptionsResult.zzd(status);
    }
}

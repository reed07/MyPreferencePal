package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataDeleteRequest;

final class zzdl extends zzal {
    private final /* synthetic */ DataDeleteRequest zzfh;

    zzdl(zzdj zzdj, GoogleApiClient googleApiClient, DataDeleteRequest dataDeleteRequest) {
        this.zzfh = dataDeleteRequest;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzbz) ((zzag) anyClient).getService()).zza(new DataDeleteRequest(this.zzfh, (zzcq) new zzen(this)));
    }
}

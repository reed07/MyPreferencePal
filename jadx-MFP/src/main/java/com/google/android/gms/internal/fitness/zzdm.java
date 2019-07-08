package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataUpdateRequest;

final class zzdm extends zzal {
    private final /* synthetic */ DataUpdateRequest zzfi;

    zzdm(zzdj zzdj, GoogleApiClient googleApiClient, DataUpdateRequest dataUpdateRequest) {
        this.zzfi = dataUpdateRequest;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzbz) ((zzag) anyClient).getService()).zza(new DataUpdateRequest(this.zzfi, (IBinder) new zzen(this)));
    }
}

package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;

final class zzdn extends zzal {
    private final /* synthetic */ DataUpdateListenerRegistrationRequest zzfj;

    zzdn(zzdj zzdj, GoogleApiClient googleApiClient, DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) {
        this.zzfj = dataUpdateListenerRegistrationRequest;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzbz) ((zzag) anyClient).getService()).zza(new DataUpdateListenerRegistrationRequest(this.zzfj, (IBinder) new zzen(this)));
    }
}

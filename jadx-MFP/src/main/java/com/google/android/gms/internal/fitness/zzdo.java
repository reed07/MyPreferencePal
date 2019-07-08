package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.zzw;

final class zzdo extends zzal {
    private final /* synthetic */ PendingIntent zzfk;

    zzdo(zzdj zzdj, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        this.zzfk = pendingIntent;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzbz) ((zzag) anyClient).getService()).zza(new zzw(this.zzfk, new zzen(this)));
    }
}

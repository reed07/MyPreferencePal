package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.zzbd;

final class zzek extends zzbd {
    private final /* synthetic */ PendingIntent zzfv;

    zzek(zzee zzee, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        this.zzfv = pendingIntent;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzcf) ((zzay) anyClient).getService()).zza(new zzbd(this.zzfv, (zzcq) new zzen(this)));
    }
}
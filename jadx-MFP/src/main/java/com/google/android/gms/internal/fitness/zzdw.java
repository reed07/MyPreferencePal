package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.request.zzbj;

final class zzdw extends zzar {
    private final /* synthetic */ Subscription zzfr;

    zzdw(zzdt zzdt, GoogleApiClient googleApiClient, Subscription subscription) {
        this.zzfr = subscription;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzcb) ((zzam) anyClient).getService()).zza(new zzbj(this.zzfr, false, (zzcq) new zzen(this)));
    }
}

package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.zzae;
import com.google.android.gms.fitness.request.zzbh;

final class zzcv extends zzu {
    private final /* synthetic */ zzae zzey;

    zzcv(zzct zzct, GoogleApiClient googleApiClient, zzae zzae) {
        this.zzey = zzae;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzbt) ((zzp) anyClient).getService()).zza(new zzbh(this.zzey, (zzcq) new zzen(this)));
    }
}

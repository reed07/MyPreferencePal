package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.request.zzk;

final class zzdk extends zzal {
    private final /* synthetic */ DataSet zzff;
    private final /* synthetic */ boolean zzfg = false;

    zzdk(zzdj zzdj, GoogleApiClient googleApiClient, DataSet dataSet, boolean z) {
        this.zzff = dataSet;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzbz) ((zzag) anyClient).getService()).zza(new zzk(this.zzff, (zzcq) new zzen(this), this.zzfg));
    }
}

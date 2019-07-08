package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzbn;

final class zzdx extends zzar {
    private final /* synthetic */ DataType zzfm;

    zzdx(zzdt zzdt, GoogleApiClient googleApiClient, DataType dataType) {
        this.zzfm = dataType;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzcb) ((zzam) anyClient).getService()).zza(new zzbn(this.zzfm, (DataSource) null, (zzcq) new zzen(this)));
    }
}

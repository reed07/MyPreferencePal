package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzbn;

final class zzdy extends zzar {
    private final /* synthetic */ DataSource zzfs;

    zzdy(zzdt zzdt, GoogleApiClient googleApiClient, DataSource dataSource) {
        this.zzfs = dataSource;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzcb) ((zzam) anyClient).getService()).zza(new zzbn((DataType) null, this.zzfs, (zzcq) new zzen(this)));
    }
}

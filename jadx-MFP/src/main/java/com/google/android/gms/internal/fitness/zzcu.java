package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.zzae;

final class zzcu extends zzu {
    private final /* synthetic */ StartBleScanRequest zzex;
    private final /* synthetic */ zzae zzey;

    zzcu(zzct zzct, GoogleApiClient googleApiClient, StartBleScanRequest startBleScanRequest, zzae zzae) {
        this.zzex = startBleScanRequest;
        this.zzey = zzae;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzbt) ((zzp) anyClient).getService()).zza(new StartBleScanRequest(this.zzex.getDataTypes(), this.zzey, this.zzex.getTimeoutSecs(), (zzcq) new zzen(this)));
    }
}

package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.SessionInsertRequest;

final class zzeh extends zzbd {
    private final /* synthetic */ SessionInsertRequest zzfz;

    zzeh(zzee zzee, GoogleApiClient googleApiClient, SessionInsertRequest sessionInsertRequest) {
        this.zzfz = sessionInsertRequest;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzcf) ((zzay) anyClient).getService()).zza(new SessionInsertRequest(this.zzfz, (zzcq) new zzen(this)));
    }
}

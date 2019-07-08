package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;

final class zzei extends zzbb<SessionReadResult> {
    private final /* synthetic */ SessionReadRequest zzga;

    zzei(zzee zzee, GoogleApiClient googleApiClient, SessionReadRequest sessionReadRequest) {
        this.zzga = sessionReadRequest;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzcf) ((zzay) anyClient).getService()).zza(new SessionReadRequest(this.zzga, (zzck) new zzel(this, null)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return SessionReadResult.zze(status);
    }
}

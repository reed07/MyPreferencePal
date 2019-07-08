package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.fitness.result.GoalsResult;
import java.util.Collections;

final class zzdh extends zzae<GoalsResult> {
    private final /* synthetic */ GoalsReadRequest zzfd;

    zzdh(zzdg zzdg, GoogleApiClient googleApiClient, GoalsReadRequest goalsReadRequest) {
        this.zzfd = goalsReadRequest;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzbx) ((zzab) anyClient).getService()).zza(new GoalsReadRequest(this.zzfd, (zzbq) new zzdi(this)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new GoalsResult(status, Collections.emptyList());
    }
}

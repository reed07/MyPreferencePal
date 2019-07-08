package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.zzbb;
import com.google.android.gms.fitness.result.SessionStopResult;
import java.util.Collections;

final class zzeg extends zzbb<SessionStopResult> {
    private final /* synthetic */ String val$name = null;
    private final /* synthetic */ String zzfy;

    zzeg(zzee zzee, GoogleApiClient googleApiClient, String str, String str2) {
        this.zzfy = str2;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzcf) ((zzay) anyClient).getService()).zza(new zzbb(this.val$name, this.zzfy, (zzcn) new zzem(this, null)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new SessionStopResult(status, Collections.emptyList());
    }
}

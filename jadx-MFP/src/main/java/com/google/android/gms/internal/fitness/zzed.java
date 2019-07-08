package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.request.zzar;

final class zzed extends zzax {
    private final /* synthetic */ PendingIntent zzfk;
    private final /* synthetic */ zzt zzfw;

    zzed(zzea zzea, GoogleApiClient googleApiClient, zzt zzt, PendingIntent pendingIntent) {
        this.zzfw = zzt;
        this.zzfk = pendingIntent;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final Status zza(Status status) {
        return status;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzcd) ((zzas) anyClient).getService()).zza(new zzar(this.zzfw, this.zzfk, (zzcq) new zzen(this)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return createFailedResult(status);
    }
}

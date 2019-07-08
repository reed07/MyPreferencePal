package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzao;

final class zzec extends zzax {
    private final /* synthetic */ zzt zzfu;
    private final /* synthetic */ PendingIntent zzfv;
    private final /* synthetic */ SensorRequest zzy;

    zzec(zzea zzea, GoogleApiClient googleApiClient, SensorRequest sensorRequest, zzt zzt, PendingIntent pendingIntent) {
        this.zzy = sensorRequest;
        this.zzfu = zzt;
        this.zzfv = pendingIntent;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final Status zza(Status status) {
        return status;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzcd) ((zzas) anyClient).getService()).zza(new zzao(this.zzy, this.zzfu, this.zzfv, new zzen(this)));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return createFailedResult(status);
    }
}

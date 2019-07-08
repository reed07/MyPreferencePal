package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzg;
import com.google.android.gms.fitness.result.DailyTotalResult;

final class zzdq extends zzaj<DailyTotalResult> {
    private final /* synthetic */ DataType zzfm;
    private final /* synthetic */ boolean zzfn;

    zzdq(zzdj zzdj, GoogleApiClient googleApiClient, DataType dataType, boolean z) {
        this.zzfm = dataType;
        this.zzfn = z;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzag zzag = (zzag) anyClient;
        ((zzbz) zzag.getService()).zza(new zzg((zzbe) new zzdr(this), this.zzfm, this.zzfn));
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return DailyTotalResult.zza(status, this.zzfm);
    }
}

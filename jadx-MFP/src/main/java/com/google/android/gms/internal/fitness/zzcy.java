package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.zzbl;

final class zzcy extends zzu {
    private final /* synthetic */ String a_;

    zzcy(zzct zzct, GoogleApiClient googleApiClient, String str) {
        this.a_ = str;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzbt) ((zzp) anyClient).getService()).zza(new zzbl(this.a_, (zzcq) new zzen(this)));
    }
}

package com.google.android.gms.internal.icing;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.icing.zzai.zzc;
import com.google.android.gms.internal.icing.zzai.zzd;

final class zzaj extends zzc<Status> {
    private final /* synthetic */ zzx[] zzat;

    zzaj(zzai zzai, GoogleApiClient googleApiClient, zzx[] zzxArr) {
        this.zzat = zzxArr;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzab zzab) throws RemoteException {
        zzab.zza(new zzd(this), null, this.zzat);
    }
}

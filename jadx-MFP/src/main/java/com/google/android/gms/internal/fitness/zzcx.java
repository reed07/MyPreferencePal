package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.zze;

final class zzcx extends zzu {
    private final /* synthetic */ BleDevice zzfa;

    zzcx(zzct zzct, GoogleApiClient googleApiClient, BleDevice bleDevice) {
        this.zzfa = bleDevice;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzbt) ((zzp) anyClient).getService()).zza(new zze(this.zzfa.getAddress(), this.zzfa, (zzcq) new zzen(this)));
    }
}

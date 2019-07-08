package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.zzaz;

final class zzef extends zzbd {
    private final /* synthetic */ Session zzfx;

    zzef(zzee zzee, GoogleApiClient googleApiClient, Session session) {
        this.zzfx = session;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        ((zzcf) ((zzay) anyClient).getService()).zza(new zzaz(this.zzfx, (zzcq) new zzen(this)));
    }
}

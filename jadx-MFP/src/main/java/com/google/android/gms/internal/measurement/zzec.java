package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

final class zzec extends zzb {
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ String zzadw;
    private final /* synthetic */ String zzadx;
    private final /* synthetic */ Bundle zzady;

    zzec(zzea zzea, String str, String str2, Bundle bundle) {
        this.zzadv = zzea;
        this.zzadw = str;
        this.zzadx = str2;
        this.zzady = bundle;
        super(zzea);
    }

    /* access modifiers changed from: 0000 */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.clearConditionalUserProperty(this.zzadw, this.zzadx, this.zzady);
    }
}

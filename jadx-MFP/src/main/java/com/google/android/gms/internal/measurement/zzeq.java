package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

final class zzeq extends zzb {
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ zza zzaea;
    private final /* synthetic */ Bundle zzaem;

    zzeq(zzea zzea, Bundle bundle, zza zza) {
        this.zzadv = zzea;
        this.zzaem = bundle;
        this.zzaea = zza;
        super(zzea);
    }

    /* access modifiers changed from: 0000 */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.performAction(this.zzaem, this.zzaea, this.timestamp);
    }

    /* access modifiers changed from: protected */
    public final void zzge() {
        this.zzaea.zzb(null);
    }
}

package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzel extends zzb {
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ zza zzaea;

    zzel(zzea zzea, zza zza) {
        this.zzadv = zzea;
        this.zzaea = zza;
        super(zzea);
    }

    /* access modifiers changed from: 0000 */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.generateEventId(this.zzaea);
    }

    /* access modifiers changed from: protected */
    public final void zzge() {
        this.zzaea.zzb(null);
    }
}

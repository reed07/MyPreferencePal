package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzer extends zzb {
    private final /* synthetic */ String zzads;
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ zza zzaea;

    zzer(zzea zzea, String str, zza zza) {
        this.zzadv = zzea;
        this.zzads = str;
        this.zzaea = zza;
        super(zzea);
    }

    /* access modifiers changed from: 0000 */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.getMaxUserProperties(this.zzads, this.zzaea);
    }

    /* access modifiers changed from: protected */
    public final void zzge() {
        this.zzaea.zzb(null);
    }
}

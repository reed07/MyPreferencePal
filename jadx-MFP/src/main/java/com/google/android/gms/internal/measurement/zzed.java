package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzed extends zzb {
    private final /* synthetic */ String zzads;
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ String zzadz;
    private final /* synthetic */ zza zzaea;

    zzed(zzea zzea, String str, String str2, zza zza) {
        this.zzadv = zzea;
        this.zzads = str;
        this.zzadz = str2;
        this.zzaea = zza;
        super(zzea);
    }

    /* access modifiers changed from: 0000 */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.getConditionalUserProperties(this.zzads, this.zzadz, this.zzaea);
    }

    /* access modifiers changed from: protected */
    public final void zzge() {
        this.zzaea.zzb(null);
    }
}
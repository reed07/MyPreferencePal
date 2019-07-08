package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class zzeh extends zzb {
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ String zzaee;

    zzeh(zzea zzea, String str) {
        this.zzadv = zzea;
        this.zzaee = str;
        super(zzea);
    }

    /* access modifiers changed from: 0000 */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.endAdUnitExposure(this.zzaee, this.zzaev);
    }
}

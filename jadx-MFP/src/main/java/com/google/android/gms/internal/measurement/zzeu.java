package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;

final class zzeu extends zzb {
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzads;
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ Long zzaeo;
    private final /* synthetic */ Bundle zzaep;
    private final /* synthetic */ boolean zzaeq = true;
    private final /* synthetic */ boolean zzaer;

    zzeu(zzea zzea, Long l, String str, String str2, Bundle bundle, boolean z, boolean z2) {
        this.zzadv = zzea;
        this.zzaeo = l;
        this.zzads = str;
        this.val$name = str2;
        this.zzaep = bundle;
        this.zzaer = z2;
        super(zzea);
    }

    /* access modifiers changed from: 0000 */
    public final void zzgd() throws RemoteException {
        Long l = this.zzaeo;
        this.zzadv.zzadr.logEvent(this.zzads, this.val$name, this.zzaep, this.zzaeq, this.zzaer, l == null ? this.timestamp : l.longValue());
    }
}

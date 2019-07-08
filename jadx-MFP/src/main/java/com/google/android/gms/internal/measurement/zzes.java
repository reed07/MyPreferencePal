package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.measurement.internal.zzcy;

final class zzes extends zzb {
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ zzcy zzaen;

    zzes(zzea zzea, zzcy zzcy) {
        this.zzadv = zzea;
        this.zzaen = zzcy;
        super(zzea);
    }

    /* access modifiers changed from: 0000 */
    public final void zzgd() throws RemoteException {
        if (this.zzadv.zzadn.containsKey(this.zzaen)) {
            Log.w(this.zzadv.zzadh, "OnEventListener already registered.");
            return;
        }
        zzd zzd = new zzd(this.zzaen);
        this.zzadv.zzadn.put(this.zzaen, zzd);
        this.zzadv.zzadr.registerOnMeasurementEventListener(zzd);
    }
}

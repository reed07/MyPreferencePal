package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.measurement.internal.zzcy;

final class zzet extends zzb {
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ zzcy zzaen;

    zzet(zzea zzea, zzcy zzcy) {
        this.zzadv = zzea;
        this.zzaen = zzcy;
        super(zzea);
    }

    /* access modifiers changed from: 0000 */
    public final void zzgd() throws RemoteException {
        zzd zzd = (zzd) this.zzadv.zzadn.get(this.zzaen);
        if (zzd == null) {
            Log.w(this.zzadv.zzadh, "OnEventListener had not been registered.");
            return;
        }
        this.zzadv.zzadr.unregisterOnMeasurementEventListener(zzd);
        this.zzadv.zzadn.remove(this.zzaen);
    }
}

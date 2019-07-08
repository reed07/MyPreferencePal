package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbv;

final class zzats implements Runnable {
    private final /* synthetic */ zzasi zzeax;
    private final /* synthetic */ zzast zzeay;
    private final /* synthetic */ zzatq zzeaz;

    zzats(zzatq zzatq, zzasi zzasi, zzast zzast) {
        this.zzeaz = zzatq;
        this.zzeax = zzasi;
        this.zzeay = zzast;
    }

    public final void run() {
        zzasm zzasm;
        try {
            zzasm = this.zzeaz.zzb(this.zzeax);
        } catch (Exception e) {
            zzbv.zzlj().zza(e, "AdRequestServiceImpl.loadAdAsync");
            zzaxz.zzc("Could not fetch ad response due to an Exception.", e);
            zzasm = null;
        }
        if (zzasm == null) {
            zzasm = new zzasm(0);
        }
        try {
            this.zzeay.zza(zzasm);
        } catch (RemoteException e2) {
            zzaxz.zzc("Fail to forward ad response.", e2);
        }
    }
}

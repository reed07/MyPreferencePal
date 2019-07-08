package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzabw;
import com.google.android.gms.internal.ads.zzaek;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxz;

final class zzbj implements Runnable {
    private final /* synthetic */ zzbb zzbqi;
    private final /* synthetic */ String zzbql;
    private final /* synthetic */ zzaxf zzbqm;

    zzbj(zzbb zzbb, String str, zzaxf zzaxf) {
        this.zzbqi = zzbb;
        this.zzbql = str;
        this.zzbqm = zzaxf;
    }

    public final void run() {
        try {
            ((zzaek) this.zzbqi.zzbls.zzbth.get(this.zzbql)).zzb((zzabw) this.zzbqm.zzehp);
        } catch (RemoteException e) {
            zzaxz.zzd("#007 Could not call remote method.", e);
        }
    }
}

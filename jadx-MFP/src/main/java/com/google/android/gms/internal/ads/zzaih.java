package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzu;
import java.util.Map;

final /* synthetic */ class zzaih implements Runnable {
    private final zzaig zzdik;
    private final zzu zzdil;
    private final Map zzdim;

    zzaih(zzaig zzaig, zzu zzu, Map map) {
        this.zzdik = zzaig;
        this.zzdil = zzu;
        this.zzdim = map;
    }

    public final void run() {
        this.zzdik.zza(this.zzdil, this.zzdim);
    }
}

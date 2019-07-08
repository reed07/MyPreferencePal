package com.google.android.gms.internal.ads;

import java.util.Map;

final /* synthetic */ class zzbgx implements Runnable {
    private final Map zzdcq;
    private final zzbgw zzfap;

    zzbgx(zzbgw zzbgw, Map map) {
        this.zzfap = zzbgw;
        this.zzdcq = map;
    }

    public final void run() {
        this.zzfap.zzo(this.zzdcq);
    }
}

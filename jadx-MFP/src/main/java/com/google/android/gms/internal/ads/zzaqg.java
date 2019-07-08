package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final /* synthetic */ class zzaqg implements Runnable {
    private final zzbcl zzcaj;
    private final zzaqf zzdui;
    private final boolean zzduj;
    private final JSONObject zzduk;

    zzaqg(zzaqf zzaqf, boolean z, JSONObject jSONObject, zzbcl zzbcl) {
        this.zzdui = zzaqf;
        this.zzduj = z;
        this.zzduk = jSONObject;
        this.zzcaj = zzbcl;
    }

    public final void run() {
        this.zzdui.zza(this.zzduj, this.zzduk, this.zzcaj);
    }
}

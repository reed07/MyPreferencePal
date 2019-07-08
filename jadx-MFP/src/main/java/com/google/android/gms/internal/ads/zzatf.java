package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final class zzatf implements Runnable {
    private final /* synthetic */ zzatd zzdzy;
    final /* synthetic */ JSONObject zzdzz;
    final /* synthetic */ String zzeaa;

    zzatf(zzatd zzatd, JSONObject jSONObject, String str) {
        this.zzdzy = zzatd;
        this.zzdzz = jSONObject;
        this.zzeaa = str;
    }

    public final void run() {
        this.zzdzy.zzdzx = zzatd.zzdzt.zzb((zzcu) null);
        this.zzdzy.zzdzx.zza(new zzatg(this), new zzath(this));
    }
}

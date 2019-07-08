package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzaxz;
import org.json.JSONObject;

final class zzw implements Runnable {
    private final /* synthetic */ JSONObject zzdfp;
    private final /* synthetic */ zzv zzdfq;

    zzw(zzv zzv, JSONObject jSONObject) {
        this.zzdfq = zzv;
        this.zzdfp = jSONObject;
    }

    public final void run() {
        this.zzdfq.zzdfn.zza("fetchHttpRequestCompleted", this.zzdfp);
        zzaxz.zzdn("Dispatched http response.");
    }
}

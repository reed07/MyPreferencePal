package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final /* synthetic */ class zzaqj implements zzbhp {
    private final zzbgg zzdup;
    private final JSONObject zzduq;

    zzaqj(zzbgg zzbgg, JSONObject jSONObject) {
        this.zzdup = zzbgg;
        this.zzduq = jSONObject;
    }

    public final void zzuc() {
        this.zzdup.zzb("google.afma.nativeAds.renderVideo", this.zzduq);
    }
}
